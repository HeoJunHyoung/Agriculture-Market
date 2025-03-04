package market.agriculture.service;

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
    public Long createPostWithItems(Long memberId, String postTitle, String postDescription,
                                    Address directSaleAddress, Long totalQuantity, List<Item> items) {

        Member member = memberRepository.findById(memberId);

        if (!member.isSeller()) {
            throw new IllegalStateException("판매자만 게시글을 작성할 수 있습니다.");
        }

        Post post = Post.createPost(member, postTitle, postDescription, directSaleAddress, totalQuantity);

        for (Item item : items) {
            post.addItem(item);
        }

        postRepository.save(post);
        return post.getId();


    }
}