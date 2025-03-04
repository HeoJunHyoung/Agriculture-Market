package market.agriculture.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import market.agriculture.exception.custom.NotEnoughStockException;

@Entity
@Getter @Setter
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

    private int price;

    public Item() {
    }

    public Item(String itemName, Long weight, Long stockQuantity, int price) {
        this.itemName = itemName;
        this.weight = weight;
        this.stockQuantity = stockQuantity;
        this.price = price;
    }

    //==생성 메서드==//
    public static Item createItem(String itemName, Long kg, Long stockQuantity) {
        Item item = new Item();
        item.setItemName(itemName);
        item.setWeight(kg);
        item.setStockQuantity(stockQuantity);
        return item;
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
