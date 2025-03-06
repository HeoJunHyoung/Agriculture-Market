package market.agriculture.dto.item;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemCreateRequest {

    public ItemCreateRequest() {
    }

    @NotBlank(message = "상품 이름은 필수 입력 값입니다.")
    @Size(max = 50, message = "상품 이름은 최대 50자까지 입력 가능합니다.")
    String itemName;

    @NotBlank(message = "상품 단위(무게) 리스트는 비어 있을 수 없습니다.")
    @Size(min = 1, message = "최소 한 개 이상의 상품 단위(무게)가 필요합니다.")
    Long itemWeight;

    @NotBlank(message = "상품 수량 리스트는 비어 있을 수 없습니다.")
    @Size(min = 1, message = "최소 한 개 이상의 상품 수량이 필요합니다.")
    Long itemQuantity;

    @NotBlank(message = "상품 가격 리스트는 비어 있을 수 없습니다.")
    @Size(min = 1, message = "최소 한 개 이상의 상품 가격이 필요합니다.")
    Integer itemPrice;

}
