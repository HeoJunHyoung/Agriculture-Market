package market.agriculture.dto.order;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import market.agriculture.entity.Item;
import market.agriculture.entity.Order;
import market.agriculture.entity.OrderItem;

import java.util.List;

@Getter @Setter
@Slf4j
public class CheckOrderDetailsItemResponse {

    private Long id;

    private Long itemId;

    private String itemName;

    private int price;

    private int count;

    public CheckOrderDetailsItemResponse() {
    }

    public CheckOrderDetailsItemResponse(OrderItem orderItem) {
        id = orderItem.getId();
        itemId = orderItem.getItem() != null ? orderItem.getItem().getId() : null; // null 체크
        itemName = orderItem.getItem() != null ? orderItem.getItem().getItemName() : null; // null 체크
        price = orderItem.getPrice();
        count = orderItem.getCount();
    }
}
