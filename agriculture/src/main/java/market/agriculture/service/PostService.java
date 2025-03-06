package market.agriculture.service;

import market.agriculture.dto.post.CreateItemDto;
import market.agriculture.dto.post.CreatePostRequest;
import market.agriculture.entity.Item;
import market.agriculture.entity.Member;
import market.agriculture.entity.Post;
import market.agriculture.entity.embedded.Address;
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

        List<CreateItemDto> itemsDto = request.getItems();
        List<Item> items = itemsDto.stream()
                .map(itemDto -> Item.createItem(itemDto.getItemName(), itemDto.getWeight(), itemDto.getStockQuantity(), itemDto.getPrice()))
                .collect(Collectors.toList());

        Post post = Post.createPost(member, request.getPostTitle(), request.getPostDescription(), request.getDirectSaleAddress(), request.getTotalQuantity(), items);

        postRepository.save(post);

        return post.getId();
    }
}