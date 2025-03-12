package market.agriculture.dto.post;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import market.agriculture.entity.Item;
import market.agriculture.entity.Post;

@Getter @Setter
@Slf4j
public class CheckItemDto {

    private Long id;

    private String itemName;

    private Long weight;

    private Long stockQuantity;

    private int price;

    public CheckItemDto() {
    }

    public CheckItemDto(Item item) {
        id = item.getId();
        itemName = item.getItemName();
        weight = item.getWeight();
        stockQuantity = item.getStockQuantity();
        price = item.getPrice();
    }
}
