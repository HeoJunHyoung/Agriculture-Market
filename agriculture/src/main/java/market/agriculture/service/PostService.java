package market.agriculture.service;

import market.agriculture.dto.post.PostUploadDto;
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

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
}