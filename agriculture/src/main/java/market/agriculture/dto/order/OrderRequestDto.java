package market.agriculture.dto.order;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderRequestDto {
    public OrderRequestDto() {
    }

    private Long item_id;
    private int quantity;
}
