package market.agriculture.dto.order;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import market.agriculture.entity.Delivery;
import market.agriculture.entity.Member;
import market.agriculture.entity.Order;
import market.agriculture.entity.OrderItem;
import market.agriculture.entity.enumerate.OrderStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class CheckOrderResponse {

    private Long orderId;

    private Long memberId;

    private String name;

    private Long deliveryId;

    private LocalDateTime createdAt;

    private OrderStatus status;

    public CheckOrderResponse() {
    }

    public CheckOrderResponse(Order order) {
        orderId = order.getId();
        memberId = order.getMember().getId();
        name = order.getMember().getNickname();
        deliveryId = order.getDelivery().getId();
        createdAt = order.getCreatedAt();
        status = order.getStatus();
    }
}
