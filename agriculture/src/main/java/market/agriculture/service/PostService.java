package market.agriculture.service;

import lombok.extern.slf4j.Slf4j;
import market.agriculture.dto.post.*;
import market.agriculture.entity.Item;
import market.agriculture.entity.Member;
import market.agriculture.entity.Post;
import market.agriculture.repository.ItemRepository;
import market.agriculture.repository.MemberRepository;
import market.agriculture.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@Slf4j
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

    /**
     * Long memberId, String postTitle, String postDescription,
     *                                     Address directSaleAddress, Long totalQuantity, List<Item> items
     */
    @Transactional
    public Long createPostWithItems(CreatePostRequest request, Long memberId) {

        Member member = memberRepository.findById(memberId);

        if (!member.isSeller()) {
            throw new IllegalStateException("판매자만 게시글을 작성할 수 있습니다.");
        }

        List<CreateItemInnerDto> itemsDto = request.getItems();
        List<Item> items = itemsDto.stream()
                .map(itemDto -> Item.createItem(itemDto.getItemName(), itemDto.getWeight(), itemDto.getStockQuantity(), itemDto.getPrice()))
                .collect(Collectors.toList());

        Post post = Post.createPost(member, request.getPostTitle(), request.getPostDescription(), request.getDirectSaleAddress(), request.getTotalQuantity(), items);

        postRepository.save(post);

        return post.getId();
    }

    /**
     *
     */
    @Transactional
    public void updatePostWithItems(Long postId, UpdatePostRequest request) {

        Post post = postRepository.findById(postId);
        if (post == null) {
            throw new IllegalStateException("해당 게시글이 존재하지 않습니다.");
        }

        post.updatePostDetails(request.getPostTitle(), request.getPostDescription(), request.getDirectSaleAddress());

        List<UpdateItemInnerDto> items = request.getItems();
        items.forEach(item -> {
            log.warn("item id = {}", item.getId());
            if (item.getId() != null) {
                Item findItem = itemRepository.findById(item.getId());
                if (findItem == null) {
                    throw new IllegalArgumentException("상품을 찾을 수 없습니다.");
                }
                findItem.updateItem(item.getItemName(), item.getWeight(), item.getStockQuantity(), item.getPrice());
            } else {
                post.addNewItem(item.getItemName(), item.getWeight(), item.getStockQuantity(), item.getPrice());
            }
        });
    }

    public List<CheckPostResponse> findMyPosts(Long memberId) {

        Member member = memberRepository.findById(memberId);

        if (!member.isSeller()) {
            throw new IllegalStateException("판매자만 판매 게시글을 조회할 수 있습니다.");
        }

        List<Post> myPosts = postRepository.findPostsByMemberId(memberId);

        return myPosts.stream()
                .map(CheckPostResponse::new)
                .collect(Collectors.toList());
    }

    public CheckPostDetailsResponse findMyPostDetails(Long postId) {
        Post myPostDetail = postRepository.findPostDetailsByPostId(postId);
        return new CheckPostDetailsResponse(myPostDetail);
    }



}