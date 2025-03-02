package market.agriculture.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import market.agriculture.entity.embedded.Address;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity @Getter
@Slf4j
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id")
    private Seller seller;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Item> items = new ArrayList<>();

    private String postTitle;

    @Column(name = "post_description")
    private String postDescription;

    @Column(name = "post_date")
    private LocalDateTime createAt;

    private Long totalQuantity;

    @Column(name = "direct_sale_address")
    @Embedded
    private Address directSaleAddress;

    public Post() {
    }

    public Post(Long id, Seller seller, List<Review> reviews, List<Item> items, String postTitle, String postDescription, LocalDateTime createAt, Long totalQuantity, Address directSaleAddress) {
        this.id = id;
        this.seller = seller;
        this.reviews = reviews;
        this.items = items;
        this.postTitle = postTitle;
        this.postDescription = postDescription;
        this.createAt = createAt;
        this.totalQuantity = totalQuantity;
        this.directSaleAddress = directSaleAddress;
    }
}
