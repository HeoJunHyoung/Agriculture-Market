package market.agriculture.entity;

import market.agriculture.entity.embedded.Address;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class PostTest {

    @DisplayName("판매 게시글 생성 테스트")
    @Test
    public void testCreatePost() {
        // given
        Member member = new Member();
        String postTitle = "Title";
        String postDescription = "Description";
        Address address = new Address("수원시", "권선동", "12345");
        long totalQuantity = 10L;
        List<Item> items = List.of(
                Item.createItem("귤", 1L, 10L, 10000),
                Item.createItem("귤", 2L, 20L, 18000));

        // when
        Post post = Post.createPost(member, postTitle, postDescription, address, totalQuantity, items);

        // then
        assertThat(post.getMember()).isEqualTo(member);
        assertThat(post.getPostTitle()).isEqualTo(postTitle);
        assertThat(post.getPostDescription()).isEqualTo(postDescription);
        assertThat(post.getDirectSaleAddress()).isEqualTo(address);
        assertThat(post.getTotalQuantity()).isEqualTo(totalQuantity);
        assertThat(post.getItems()).containsAll(items);
    }

    @DisplayName("판매 게시글 세부사항 변경 테스트")
    @Test
    public void testUpdatePostDetails() {
        //given
        Post post = new Post();
        String newPostTitle = "newPostTitle";
        String newPostDescription = "newDescription";
        Address newAddress = new Address("새로운", "주소가", "왔어요");

        //when
        post.updatePostDetails(newPostTitle, newPostDescription, newAddress);

        //then
        assertThat(post.getPostTitle()).isEqualTo(newPostTitle);
        assertThat(post.getPostDescription()).isEqualTo(newPostDescription);
        assertThat(post.getDirectSaleAddress()).isEqualTo(newAddress);

    }

    @DisplayName("기존 판매 게시글에 새로운 물품 등록 테스트")
    @Test
    public void testAddNewItem() {
        // given
        Member member = new Member();
        String postTitle = "Title";
        String postDescription = "Description";
        Address address = new Address("수원시", "권선동", "12345");
        long totalQuantity = 10L;
        List<Item> items = List.of(
                Item.createItem("귤", 1L, 10L, 10000),
                Item.createItem("귤", 2L, 20L, 18000));
        Post post = Post.createPost(member, postTitle, postDescription, address, totalQuantity, items);

        //when
        String newItemName = "귤";
        Long newItemWeight = 3L;
        Long newItemStockQuantity = 30L;
        int newItemPrice = 26000;

        post.addNewItem(newItemName, newItemWeight, newItemStockQuantity, newItemPrice);

        //then
        // 1. 기존 2개에서 3개로 변경되었는지 확인
        assertThat(post.getItems()).hasSize(3);

        // 2. 추가한 물품이 현재 판매 게시판에 등록되었는지 확인
        Item newItem = post.getItems().get(2); // 마지막에 추가한 물품 조회
        assertThat(newItem.getPost()).isEqualTo(post);

        // 3. 상품의 세부 내용 일치 여부 확인
        assertThat(newItem.getItemName()).isEqualTo(newItemName);
        assertThat(newItem.getWeight()).isEqualTo(newItemWeight);
        assertThat(newItem.getStockQuantity()).isEqualTo(newItemStockQuantity);
        assertThat(newItem.getPrice()).isEqualTo(newItemPrice);
    }

}