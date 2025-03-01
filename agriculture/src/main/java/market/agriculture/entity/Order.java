package market.agriculture.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import market.agriculture.entity.enumerate.OrderStatus;

import java.time.LocalDateTime;

@Entity
@Getter
@Slf4j
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customers;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private LocalDateTime orderTime;


    public Order() {
    }

    public Order(Long id, Customer customers, Item item, Delivery delivery, LocalDateTime orderDate, OrderStatus orderStatus, LocalDateTime orderTime) {
        this.id = id;
        this.customers = customers;
        this.item = item;
        this.delivery = delivery;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.orderTime = orderTime;
    }
}
