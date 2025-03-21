package market.agriculture.service;

<<<<<<< HEAD
import market.agriculture.dto.item.ItemCreateRequest;
import market.agriculture.dto.item.ItemTotalResponse;
import market.agriculture.dto.post.PostDetailsResponse;
import market.agriculture.dto.post.PostUploadRequest;
import market.agriculture.dto.post.ReviewTotalResponse;
import market.agriculture.dto.post.ReviewUploadReqeust;
=======
import market.agriculture.dto.post.CreateItemDto;
import market.agriculture.dto.post.CreatePostRequest;
>>>>>>> main
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
<<<<<<< HEAD
import java.util.stream.IntStream;
=======
import java.util.stream.Collectors;
>>>>>>> main

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

    /**
     * Long memberId, String postTitle, String postDescription,
     *                                     Address directSaleAddress, Long totalQuantity, List<Item> items
     */
    @Transactional
<<<<<<< HEAD
    public Long createPostWithItems(String username, PostUploadRequest postUploadRequest) {
=======
    public Long createPostWithItems(CreatePostRequest request, Long memberId) {
>>>>>>> main

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

<<<<<<< HEAD
        List<Item> items = new ArrayList<>();
        for (ItemCreateRequest itemDto : postUploadRequest.getItemCreateRequests()) {
            items.add(Item.createItem(
                    itemDto.getItemName(),
                    itemDto.getItemWeight(),
                    itemDto.getItemQuantity(),
                    itemDto.getItemPrice()
            ));
        }
        Post post = Post.createPost(member,postUploadRequest.getTitle(),postUploadRequest.getPostDescription(),
                postUploadRequest.getDirectSaleAddress(),postUploadRequest.getTotalQuantity(), items);
=======
        List<CreateItemDto> itemsDto = request.getItems();
        List<Item> items = itemsDto.stream()
                .map(itemDto -> Item.createItem(itemDto.getItemName(), itemDto.getWeight(), itemDto.getStockQuantity(), itemDto.getPrice()))
                .collect(Collectors.toList());

        Post post = Post.createPost(member, request.getPostTitle(), request.getPostDescription(), request.getDirectSaleAddress(), request.getTotalQuantity(), items);
>>>>>>> main

        postRepository.save(post);

        return post.getId();
<<<<<<< HEAD

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
        }

        for(int i=0; i<newlyAddedItemList.size(); i++){

            if(newlyAddedItemList.get(i)){
                ItemCreateRequest itemDto = postUploadRequest.getItemCreateRequests().get(i);
                Item item = Item.createItem(itemDto.getItemName(),itemDto.getItemWeight(),itemDto.getItemQuantity(),itemDto.getItemPrice());
                post.addItem(item);
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

        post.addReview(review);

        postRepository.save(post);
    }

    public PostDetailsResponse getOneDetailPostWithReview(Long postId) {

        Post post = postRepository.findById(postId)
                .stream().findFirst().orElseThrow(()-> new IllegalStateException("존재하지 않는 게시글입니다."));

        Member seller = post.getMember();


        List<ItemTotalResponse> itemTotalResponseList = new ArrayList<>();
        post.getItems().stream().forEach(item -> {
            itemTotalResponseList.add(ItemTotalResponse.createByReview(item));
        });

        List<ReviewTotalResponse> reviewTotalResponseList = new ArrayList<>();

        post.getReviews().stream().forEach(review -> {
            reviewTotalResponseList.add(ReviewTotalResponse.createByReview(review));
        });

        PostDetailsResponse response = PostDetailsResponse.createPostDetailResponse(seller.getNickname(),seller.getPhoneNumber().toString(),
                post.getPostTitle(),post.getPostDescription(),post.getCreatedAt(),post.getTotalQuantity(),
                post.getDirectSaleAddress().toString(),itemTotalResponseList,reviewTotalResponseList
        );


        return response;

=======
>>>>>>> main
    }
}