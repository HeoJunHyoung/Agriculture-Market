package market.agriculture.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import market.agriculture.entity.enumerate.OrderStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    private Customer customer;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;


    public Order() {
    }

    public Order(Long id, Customer customer, List<OrderItem> orderItems, Delivery delivery, LocalDateTime orderDate, OrderStatus orderStatus) {
        this.id = id;
        this.customer = customer;
        this.orderItems = orderItems;
        this.delivery = delivery;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
    }
}
