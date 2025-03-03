package market.agriculture.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import market.agriculture.exception.custom.NotEnoughStockException;

@Entity @Getter
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    private String itemName;

    private Long weight;

    private Long stockQuantity;

    public Item() {
    }

    public Item(String itemName, Long kg, Long stockQuantity) {
        this.itemName = itemName;
        this.weight = kg;
        this.stockQuantity = stockQuantity;
    }

    //==연관관계 메서드==//
    public void setPost(Post post) {
        this.post = post;
    }

    //==비즈니스 로직==//
    public void addStockQuantity(int quantity) {
        stockQuantity += quantity;
    }
    public void reduceStockQuantity(int quantity) {
        Long rest = stockQuantity - quantity;
        if (rest < 0) {
            throw new NotEnoughStockException("재고가 부족합니다.");
        }
        stockQuantity = rest;
    }



}
