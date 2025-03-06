package market.agriculture.dto.item;

import lombok.Getter;
import lombok.Setter;
import market.agriculture.entity.Item;

@Getter @Setter
public class ItemTotalResponse {
    public ItemTotalResponse() {
    }

    String itemName;
    Long weight;
    Long quantity;
    int price;

    public static ItemTotalResponse createByReview(Item item) {

        ItemTotalResponse itemTotalResponse = new ItemTotalResponse();
        itemTotalResponse.setItemName(item.getItemName());
        itemTotalResponse.setWeight(item.getWeight());
        itemTotalResponse.setQuantity(item.getStockQuantity());
        itemTotalResponse.setPrice(item.getPrice());

        return itemTotalResponse;
    }
}
