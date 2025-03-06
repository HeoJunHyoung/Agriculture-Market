package market.agriculture.dto.order;

import lombok.Getter;
import lombok.Setter;
import market.agriculture.entity.Item;
import market.agriculture.entity.OrderItem;

@Getter @Setter
public class CreateOrderItemRequest {

    private Long itemId;

    private int count;

    private int price;

    public CreateOrderItemRequest() {
    }

    public OrderItem toEntity(Item item) {
        return OrderItem.createOrderItem(item, this.getPrice(), this.getCount());
    }

}
