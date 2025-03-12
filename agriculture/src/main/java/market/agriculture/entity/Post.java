package market.agriculture.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import market.agriculture.entity.embedded.Address;
import market.agriculture.entity.enumerate.OrderStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter @Getter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "post")
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Item> items = new ArrayList<>();

    private String postTitle;

    @Column(name = "post_description")
    private String postDescription;

    @Column(name = "post_date")
    private LocalDateTime createdAt;

    @Column(name = "total_quantity")
    private Long totalQuantity;

    @Embedded
    private Address directSaleAddress;

    public Post() {
    }

    //==연관관계 편의 메서드==//
    public void addItem(Item item) {
        items.add(item);
        item.setPost(this);
    }

    //==생성 메서드==//
    public static Post createPost(Member member, String postTitle, String postDescription, Address directSaleAddress, long totalQuantity, List<Item> items) {
        Post post = new Post();
        post.setMember(member);
        post.setPostTitle(postTitle);
        post.setPostDescription(postDescription);
        post.setDirectSaleAddress(directSaleAddress);
        post.setCreatedAt(LocalDateTime.now());
        post.setTotalQuantity(totalQuantity);

        // 아이템 추가
        for (Item item : items) {
            post.addItem(item);
        }
        return post;
    }

    //==업데이트 메서드==//
    public void updatePostDetails(String postTitle, String postDescription, Address directSaleAddress){
        this.postTitle = postTitle;
        this.postDescription = postDescription;
        this.directSaleAddress = directSaleAddress;
    }

    //==아이템 추가 메서드==//
    public void addNewItem(String itemName, Long weight, Long stockQuantity, int price) {
        Item newItem = Item.createItem(itemName, weight, stockQuantity, price);
        addItem(newItem);
    }

}
