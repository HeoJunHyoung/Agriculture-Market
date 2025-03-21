package market.agriculture.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import market.agriculture.dto.item.ItemCreateRequest;
import market.agriculture.exception.custom.NotEnoughStockException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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

    @Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean isPublished;

    public Item() {
    }

<<<<<<< HEAD
    public Item(Long id, Post post, String itemName, Long weight, Long stockQuantity, int price, Boolean isPublished) {
        this.id = id;
        this.post = post;
        this.itemName = itemName;
        this.weight = weight;
        this.stockQuantity = stockQuantity;
        this.price = price;
        this.isPublished = isPublished;
    }

    //==생성 메서드==//
    public static Item createItem(String itemName, Long kg, Long stockQuantity,int price) {
=======
    //==생성 메서드==//
    public static Item createItem(String itemName, Long kg, Long stockQuantity, int price) {
>>>>>>> main
        Item item = new Item();
        item.setItemName(itemName);
        item.setWeight(kg);
        item.setStockQuantity(stockQuantity);
        item.setPrice(price);
<<<<<<< HEAD
        item.setIsPublished(true);
=======
>>>>>>> main
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
    public void unpublish() {
//        if (!this.isPublished) {
//            throw new IllegalStateException("이미 비공개된 아이템입니다.");
//        }
        this.isPublished = false;
    }

    public int modifyCheckItem(List<ItemCreateRequest> itemCreateRequests) {
            for (int i = 0; i < itemCreateRequests.size(); i++) {

                // item 가격, 이름, 무게가 같으면 quantity값만 수정
                if (this.itemName.equals(itemCreateRequests.get(i).getItemName()) &&
                        this.price == itemCreateRequests.get(i).getItemPrice() &&
                        this.weight.equals(itemCreateRequests.get(i).getItemWeight())) {

                    // 기존 Item과 이름, 가격, 무게가 같은 경우, 수량 업데이트
                    this.setStockQuantity(itemCreateRequests.get(i).getItemQuantity());
                    this.setIsPublished(true);
                    return i;
                }
            }
            // 다른경우 flag = False
            this.setIsPublished(false);
            return -1;
        }
}
