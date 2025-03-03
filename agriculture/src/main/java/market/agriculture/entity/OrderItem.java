package market.agriculture.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Entity @Getter
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orders_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "order_price")
    private int price;

    @Column(name = "order_count")
    private int count;

    public OrderItem() {
    }


    //==연관관계 메서드==//
    public void setOrder(Order order) {
        this.order = order;
    }

    //==생성 메서드==//
    public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
        OrderItem orderItem = new OrderItem();
        orderItem.item = item;
        orderItem.price = orderPrice;
        orderItem.count = count;
        item.reduceStockQuantity(count);
        return orderItem;
    }

    //==비즈니스 로직==//
    public void cancel() {
        getItem().addStockQuantity(count);
    }

    //==조회 로직==//
    public int getTotalPrice() {
        return getPrice() * getCount();
    }



}
