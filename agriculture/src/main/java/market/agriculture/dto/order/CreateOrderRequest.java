package market.agriculture.dto.order;

import lombok.Getter;
import lombok.Setter;
import market.agriculture.dto.delivery.CreateDeliveryRequest;
import market.agriculture.entity.Delivery;
import market.agriculture.entity.Member;
import market.agriculture.entity.Order;
import market.agriculture.entity.OrderItem;

import java.util.List;

@Getter @Setter
public class CreateOrderRequest {

    private Long memberId;

    private List<CreateOrderItemRequest> orderItems;

    private CreateDeliveryRequest delivery;

    public CreateOrderRequest() {
    }

    public Order toEntity(Member member, Delivery delivery, List<OrderItem> orderItems) {
        return Order.createOrder(member, delivery, orderItems.toArray(new OrderItem[0]));
    }

}
