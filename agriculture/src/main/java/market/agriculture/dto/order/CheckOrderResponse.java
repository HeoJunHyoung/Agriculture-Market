package market.agriculture.dto.order;

import lombok.Getter;
import lombok.Setter;
import market.agriculture.entity.Order;
import market.agriculture.entity.enumerate.DeliveryStatus;

import java.time.LocalDateTime;

@Getter @Setter
public class CheckOrderResponse {

    private Long orderId;

    private Long memberId;

    private String name;

    private Long deliveryId;

    private LocalDateTime createdAt;

    private DeliveryStatus status;

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
