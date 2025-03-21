package market.agriculture.dto.order;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import market.agriculture.entity.enumerate.DeliveryStatus;
import org.aspectj.weaver.ast.Or;

@Getter
@Setter
public class OrderDto {
    public OrderDto() {
    }

    public OrderDto(Long itemId) {
        this.itemId = itemId;
    }

    Long itemId;

    public static OrderDto createOrderDto(Long itemId) {
        return new OrderDto(itemId);
    }
}
