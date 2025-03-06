//package market.agriculture.service;
//
//import market.agriculture.entity.Item;
//import market.agriculture.entity.Member;
//import market.agriculture.entity.Post;
//import market.agriculture.entity.embedded.Address;
//import market.agriculture.entity.enumerate.Role;
//import market.agriculture.repository.ItemRepository;
//import market.agriculture.repository.MemberRepository;
//import market.agriculture.repository.PostRepository;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.*;
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@Transactional
//class PostServiceTest {
//
//    @Autowired ItemRepository itemRepository;
//    @Autowired MemberRepository memberRepository;
//    @Autowired PostRepository postRepository;
//    @Autowired PostService postService;
//
//
//
//    @Test
//    @DisplayName(value = "게시판 등록 시, 상품 자동 저장")
//    void createPostWithItems_Success() {
//
//        // 이거 원래 다 넣어줘야 하는데, 테스트니께 필요한것만 넣음
//        Member member = new Member();
//        member.setUsername("판매자");
//        member.setRole(Role.Seller);
//        memberRepository.save(member);
//
//
//        // 상품 아이템 추가
//        Item item1 = Item.createItem("귤", 1L, 100L, 25000);
//        Item item2 = Item.createItem("귤", 2L, 50L, 40000);
//
//        List<Item> items = Arrays.asList(item1, item2);
//
//
//        // 게시글 작성
//        String postTitle = "귤 판매 합니다.";
//        String postDescription = "우리 귤은 말이지요. 아주 Chill 합니다.";
//        Address directSaleAddress = new Address("수원시", "권선동", "12345");
//        Long totalQuantity = 150L; // 총 재고량 (100 + 50)
//
//        // 게시글 등록
//        Long postId = postService.createPostWithItems(member.getId(), postTitle, postDescription, directSaleAddress, totalQuantity, items);
//
//        // 등록한 게시글 찾기
//        Optional<Post> savedPost = postRepository.findById(postId)
//                                    .orElseThrow(() -> throw new IllegalStateException("없음"));
//
//        // 게시글 정보 검증 부ㅜ분
//        assertThat(postTitle).isEqualTo(savedPost.getPostTitle());
//        assertThat(postDescription).isEqualTo(savedPost.getPostDescription());
//        assertThat(directSaleAddress).isEqualTo(savedPost.getDirectSaleAddress());
//        assertThat(totalQuantity).isEqualTo(savedPost.getTotalQuantity());
//
//        // 아이템 정보 검증 부분
//        List<Item> savedItems = savedPost.getItems();
//        assertThat(2).isEqualTo(savedItems.size());
//
//
//        Item savedItem1 = savedItems.get(0);
//        assertThat("귤").isEqualTo(savedItem1.getItemName());
//        assertThat(1L).isEqualTo(savedItem1.getWeight());
//        assertThat(100L).isEqualTo(savedItem1.getStockQuantity());
//
//        Item savedItem2 = savedItems.get(1);
//        assertThat("귤").isEqualTo(savedItem2.getItemName());
//        assertThat(2L).isEqualTo(savedItem2.getWeight());
//        assertThat(50L).isEqualTo(savedItem2.getStockQuantity());
//
//    }
//
//}