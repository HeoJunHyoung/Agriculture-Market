package market.agriculture.service;

import market.agriculture.dto.post.PostModifyDto;
import market.agriculture.dto.post.PostUploadDto;
import market.agriculture.entity.Item;
import market.agriculture.entity.Member;
import market.agriculture.entity.Post;
import market.agriculture.repository.ItemRepository;
import market.agriculture.repository.MemberRepository;
import market.agriculture.repository.PostRepository;
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

    @Autowired
    public PostService(PostRepository postRepository, ItemRepository itemRepository, MemberRepository memberRepository) {
        this.postRepository = postRepository;
        this.itemRepository = itemRepository;
        this.memberRepository = memberRepository;
    }

    @Transactional
    public Long createPostWithItems(String username, PostUploadDto postUploadDto) {

        if (!postUploadDto.isLengthEqual()){
            throw new IllegalStateException("모든 Item은 단위와 가격, 양이 지정되어야 합니다.");
        }

        List<Member> members = memberRepository.findByUsername(username);

        if(members.isEmpty()){
            throw new IllegalStateException("존재하지 않는 유저입니다.");
        }
        Member member = members.get(0);

        if(postUploadDto.getDirectSaleAddress().isEmpty()){
            postUploadDto.setDirectSaleAddress(member.getAddress());
        }

        if (!member.isSeller()) {
            throw new IllegalStateException("판매자만 게시글을 작성할 수 있습니다.");
        }

        Post post = postUploadDto.createPost(member);

        if (!postUploadDto.isLengthEqual()){
            throw new IllegalStateException("모든 Item은 단위와 가격, 양이 지정되어야 합니다.");
        }

        List<Item> items = postUploadDto.createItems();
        for(Item item : items){
            itemRepository.save(item);
            post.addItem(item);
        }

        postRepository.save(post);
        return post.getId();

    }

    // 진짜 너무 그지같이 짰는데 머리가 안굴러감;;
    @Transactional
    public Long updatePostWithItems(String username, PostModifyDto postModifyDto) {

        // 존재하는 post 불러옴.
        Post post = postRepository.findById(postModifyDto.getPostId())
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 게시글입니다."));

        Member member = memberRepository.findByUsername(username)
                .stream().findFirst()
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 유저입니다."));

        if (!post.getMember().equals(member)) {
            throw new IllegalStateException("작성자만 게시글을 수정할 수 있습니다.");
        }

        // 기본 주소 입력
        if (postModifyDto.getDirectSaleAddress() != null) {
            post.setDirectSaleAddress(postModifyDto.getDirectSaleAddress());
        }

        if (!postModifyDto.isLengthEqual()){
            throw new IllegalStateException("모든 Item은 단위와 가격, 양이 지정되어야 합니다.");
        }

        // 게시글이 가지고 있는 item에 같은 값이 있는지, 새로운 값이 있는지 확인 후 생성 및 isPublish 관리
        List<Boolean> newlyAddedItemList = IntStream.range(0, postModifyDto.getItemQuantities().size())
                .mapToObj(i -> true)
                .toList();

        for (Item item: post.getItems()){

            int index = item.modifyCheckItem(postModifyDto.getItemName(), postModifyDto.getItemPrices(),postModifyDto.getItemWeights(),postModifyDto.getItemQuantities());
            if (index > -1){
                newlyAddedItemList.set(index,false);
            }
            itemRepository.save(item);
        }

        for(int i=0; i<newlyAddedItemList.size(); i++){

            if(newlyAddedItemList.get(i)){
                Item item = Item.createItem(postModifyDto.getItemName(),postModifyDto.getItemWeights().get(i),postModifyDto.getItemQuantities().get(i), postModifyDto.getItemPrices().get(i));
                itemRepository.save(item);
            }

        }

        post.modifyPost(postModifyDto.getTitle(),postModifyDto.getPostDescription(),postModifyDto.getDirectSaleAddress(),postModifyDto.getTotalQuantity());
        postRepository.save(post);
        return post.getId();
    }

    public Long deletePost(Long postId){

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 게시글입니다."));

        post.unpublish();
        postRepository.save(post);

        return post.getId();
    }

}