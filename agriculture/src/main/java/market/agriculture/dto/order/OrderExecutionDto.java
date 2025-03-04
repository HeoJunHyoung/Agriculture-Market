package market.agriculture.dto.order;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderExecutionDto {

    private Long item_id;
    private int quantity;
}
