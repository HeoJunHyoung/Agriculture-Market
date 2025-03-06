package market.agriculture.dto.post;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import market.agriculture.entity.Post;

@Getter @Setter
@Slf4j
public class CreateItemDto {

    private Long postId;

    private String itemName;

    private Long weight;

    private Long stockQuantity;

    private int price;



    public CreateItemDto() {
    }
}
