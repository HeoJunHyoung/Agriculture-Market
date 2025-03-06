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

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
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

    @Column(name = "direct_sale_address")
    @Embedded
    private Address directSaleAddress;

    @Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean isPublished;

    public Post() {
    }

    //==연관관계 편의 메서드==//
    public void addReview(Review review) {
        reviews.add(review);
        review.setPost(this);
    }

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
        post.setIsPublished(true);

        // 아이템 추가
        for (Item item : items) {
            post.addItem(item);
        }

        return post;
    }


    // 비즈니스 로직
    public void unpublish() {
//        if (!this.isPublished) {
//            throw new IllegalStateException("이미 비공개된 게시글입니다.");
//        }
        this.isPublished = false;
    }

    public void modifyPost(String title, String postDescription, Address directSaleAddress, Long totalQuantity) {
        this.setPostTitle(title);
        this.setPostDescription(postDescription);
        this.setDirectSaleAddress(directSaleAddress);
        this.setTotalQuantity(totalQuantity);
        this.setIsPublished(true);
    }
}
