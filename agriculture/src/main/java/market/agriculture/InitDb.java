//package market.agriculture;
//
//import jakarta.annotation.PostConstruct;
//import jakarta.persistence.EntityManager;
//import lombok.RequiredArgsConstructor;
//import market.agriculture.entity.Item;
//import market.agriculture.entity.Member;
//import market.agriculture.entity.Post;
//import market.agriculture.entity.embedded.Address;
//import market.agriculture.entity.embedded.Phone;
//import market.agriculture.entity.enumerate.Role;
//import market.agriculture.repository.ItemRepository;
//import market.agriculture.repository.MemberRepository;
//import market.agriculture.repository.PostRepository;
//import market.agriculture.service.PostService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.Arrays;
//import java.util.List;
//
//
//@Component
//@RequiredArgsConstructor
//public class InitDb {
//
//    private final InitService initService;
//
//    @PostConstruct
//    public void init() {
//        initService.initSellerDbInit();
//        initService.initCustomerDbInit();
//    }
//
//
//    @Component
//    @Transactional
//    @RequiredArgsConstructor
//    static class InitService {
//
//        private final EntityManager em;
//        private final ItemRepository itemRepository;
//        private final MemberRepository memberRepository;
//        private final PostRepository postRepository;
//        private final PostService postService;
//
//        public void initSellerDbInit() {
//            Address address = new Address("경기도", "수원대로1255", "행복빌라 105동 303호");
//            Phone phone = new Phone("010", "22222222");
//            Member member = Member.createMember("userB", "1234", "B", address, phone, Role.Seller);
//            em.persist(member);
//
//            // 상품 아이템 추가
//            Item item1 = Item.createItem("귤", 1L, 100L, 25000);
//            em.persist(item1);
//
//            Item item2 = Item.createItem("귤", 2L, 50L, 40000);
//            em.persist(item2);
//
//            List<Item> items = Arrays.asList(item1, item2);
//
//            // 게시글 작성
//            String postTitle = "귤 판매 합니다.";
//            String postDescription = "우리 귤 아주 달아요 !!!";
//            Address directSaleAddress = new Address("수원시", "권선동", "12345");
//            Long totalQuantity = 150L; // 총 재고량 (100 + 50)
//
//            // 게시글 등록
//            Long postId = postService.createPostWithItems(member.getId(), postTitle, postDescription, directSaleAddress, totalQuantity, items);
//
//            // 등록한 게시글 찾기
//            Post savedPost = postRepository.findById(postId);
//
//
//        }
//
//        public void initCustomerDbInit() {
//            Address address = new Address("경기도", "경기대로1166", "우미아파트1차 106동 505호");
//            Phone phone = new Phone("010", "11111111");
//            Member member = Member.createMember("userA", "1234", "A", address, phone, Role.Customer);
//            em.persist(member);
//
//
//        }
//
//    }
//}
