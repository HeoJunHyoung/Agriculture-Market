package market.agriculture.dto.post;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
public class UpdateItemInnerDto {

    private Long id;

    private String itemName;

    private Long weight;

    private Long stockQuantity;

    private int price;

    public UpdateItemInnerDto() {
    }

}