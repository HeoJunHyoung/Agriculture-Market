package market.agriculture.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import market.agriculture.entity.enumerate.DeliveryStatus;

import java.time.LocalDateTime;

@Entity @Getter
@Slf4j
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery")
    private Order order;

    @Column(name = "delivery_time")
    private LocalDateTime createAt;

    @Column(name = "delivery_status")
    private DeliveryStatus status;

    public Delivery() {
    }

    //==연관관계 메서드==//
    public void setOrder(Order order) {
        this.order = order;
    }

}
