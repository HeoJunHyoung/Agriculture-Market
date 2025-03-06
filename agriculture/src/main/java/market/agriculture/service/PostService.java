package market.agriculture.service;

import market.agriculture.dto.item.ItemCreateRequest;
import market.agriculture.dto.post.PostUploadRequest;
import market.agriculture.dto.post.ReviewUploadReqeust;
import market.agriculture.entity.Item;
import market.agriculture.entity.Member;
import market.agriculture.entity.Post;
import market.agriculture.entity.Review;
import market.agriculture.repository.ItemRepository;
import market.agriculture.repository.MemberRepository;
import market.agriculture.repository.PostRepository;
import market.agriculture.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Service
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;

    private final ReviewRepository reviewRepository;

    public PostService(PostRepository postRepository, ItemRepository itemRepository, MemberRepository memberRepository, ReviewRepository reviewRepository) {
        this.postRepository = postRepository;
        this.itemRepository = itemRepository;
        this.memberRepository = memberRepository;
        this.reviewRepository = reviewRepository;
    }

    @Transactional
    public Long createPostWithItems(String username, PostUploadRequest postUploadRequest) {

        List<Member> members = memberRepository.findByUsername(username);

        if(members.isEmpty()){
            throw new IllegalStateException("존재하지 않는 유저입니다.");
        }
        Member member = members.get(0);

        if(postUploadRequest.getDirectSaleAddress().isEmpty()){
            postUploadRequest.setDirectSaleAddress(member.getAddress());
        }

        if (!member.isSeller()) {
            throw new IllegalStateException("판매자만 게시글을 작성할 수 있습니다.");
        }

        Post post = postUploadRequest.createPost(member);

        List<Item> items = postUploadRequest.createItems();
        for(Item item : items){
            itemRepository.save(item);
            post.addItem(item);
        }

        postRepository.save(post);
        return post.getId();

    }

    @Transactional
    public Long updatePostWithItems(String username, Long postId, PostUploadRequest postUploadRequest) {

        // 존재하는 post 불러옴.
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 게시글입니다."));

        Member member = memberRepository.findByUsername(username)
                .stream().findFirst()
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 유저입니다."));

        if (!post.getMember().equals(member)) {
            throw new IllegalStateException("작성자만 게시글을 수정할 수 있습니다.");
        }

        // 기본 주소 입력
        if (postUploadRequest.getDirectSaleAddress() != null) {
            post.setDirectSaleAddress(postUploadRequest.getDirectSaleAddress());
        }

        // 게시글이 가지고 있는 item에 같은 값이 있는지, 새로운 값이 있는지 확인 후 생성 및 isPublish 관리
        List<Boolean> newlyAddedItemList = new ArrayList<>(IntStream.range(0, postUploadRequest.returnItemSize())
                .mapToObj(i -> true)
                .toList());

        for (Item item: post.getItems()){

            int index = item.modifyCheckItem(postUploadRequest.getItemCreateRequests());
            if (index > -1){
                newlyAddedItemList.set(index,false);
            }
            itemRepository.save(item);
        }

        for(int i=0; i<newlyAddedItemList.size(); i++){

            if(newlyAddedItemList.get(i)){
                ItemCreateRequest itemDto = postUploadRequest.getItemCreateRequests().get(i);
                Item item = Item.createItem(itemDto.getItemName(),itemDto.getItemWeight(),itemDto.getItemQuantity(),itemDto.getItemPrice());
                post.addItem(item);
                itemRepository.save(item);
            }

        }

        post.modifyPost(postUploadRequest.getTitle(),postUploadRequest.getPostDescription(),postUploadRequest.getDirectSaleAddress(),postUploadRequest.getTotalQuantity());
        postRepository.save(post);
        return post.getId();
    }

    @Transactional
    public Long deletePostWithItems(String username, Long postId){

        Member member = memberRepository.findByUsername(username)
                .stream().findFirst()
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 유저입니다."));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 게시글입니다."));

        if(!post.getMember().getUsername().equals(member.getUsername())){
            throw new IllegalStateException("잘못된 유저의 접근입니다.");
        }

        post.unpublish();

        List<Item> items = post.getItems();
        items.stream().forEach(item -> {
            item.unpublish();
            itemRepository.save(item);
        });

        postRepository.save(post);

        return post.getId();
    }

    @Transactional
    public void createReview(String username, ReviewUploadReqeust reviewUploadReqeust, Long postId) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 게시글입니다."));

        if(!post.getIsPublished()){
            throw new IllegalStateException("삭제처리된 게시글입니다.");
        }

        Member member = memberRepository.findByUsername(username)
                .stream().findFirst().orElseThrow(()->new IllegalStateException("존재하지 않는 유저입니다."));

        Review review = Review.createReview(post, member, reviewUploadReqeust.getReviewTitle(), reviewUploadReqeust.getReviewDescription());

        reviewRepository.save(review);

        post.addReview(review);

        postRepository.save(post);
    }
}