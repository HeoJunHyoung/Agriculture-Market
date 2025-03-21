package market.agriculture.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import market.agriculture.entity.embedded.Address;
import market.agriculture.entity.embedded.Phone;
import market.agriculture.entity.enumerate.DeliveryStatus;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery")
    private Order order;

    @Column(name = "delivery_time")
    private LocalDateTime createdAt;

    @Column(name = "delivery_status")
    private DeliveryStatus status;

    private Address address;

    private Phone phoneNumber;

    private String receiverName;

    public Delivery() {
    }

    public static Delivery createDelivery(Address address, String receiverName, Phone receiverPhoneNumber) {
        Delivery delivery = new Delivery();
        delivery.setAddress(address);
        delivery.setPhoneNumber(receiverPhoneNumber);
        delivery.setReceiverName(receiverName);
        delivery.setCreatedAt(LocalDateTime.now());
        delivery.setStatus(DeliveryStatus.DEPARTED);
        return delivery;
    }

}
