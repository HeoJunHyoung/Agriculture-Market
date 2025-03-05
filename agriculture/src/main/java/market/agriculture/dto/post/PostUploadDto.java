package market.agriculture.dto.post;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import market.agriculture.entity.Item;
import market.agriculture.entity.Member;
import market.agriculture.entity.Post;
import market.agriculture.entity.embedded.Address;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Getter @Setter
public class PostUploadDto {

    @NotBlank(message = "게시글 제목은 필수 입력 값입니다.")
    @Size(max = 100, message = "게시글 제목은 최대 100자까지 입력 가능합니다.")
    private String title;

    @NotBlank(message = "게시글 설명은 필수 입력 값입니다.")
    @Size(max = 500, message = "게시글 설명은 최대 500자까지 입력 가능합니다.")
    private String postDescription;

    //    private String imageAddress;

    private Address directSaleAddress; // null 허용 (비었을 경우, 회원 주소 자동 설정)

    @NotNull(message = "총 재고량은 필수 입력 값입니다.")
    @Positive(message = "총 재고량은 1 이상이어야 합니다.")
    private Long totalQuantity;

    @NotBlank(message = "상품 이름은 필수 입력 값입니다.")
    @Size(max = 50, message = "상품 이름은 최대 50자까지 입력 가능합니다.")
    private String itemName;

    @NotEmpty(message = "상품 단위(무게) 리스트는 비어 있을 수 없습니다.")
    @Size(min = 1, message = "최소 한 개 이상의 상품 단위(무게)가 필요합니다.")
    private List<@NotNull(message = "상품 단위(무게)는 null일 수 없습니다.") @Positive(message = "상품 단위(무게)는 양수여야 합니다.") Long> itemWeights;

    @NotEmpty(message = "상품 수량 리스트는 비어 있을 수 없습니다.")
    @Size(min = 1, message = "최소 한 개 이상의 상품 수량이 필요합니다.")
    private List<@NotNull(message = "상품 수량은 null일 수 없습니다.") @Positive(message = "상품 수량은 양수여야 합니다.") Long> itemQuantities;

    @NotEmpty(message = "상품 가격 리스트는 비어 있을 수 없습니다.")
    @Size(min = 1, message = "최소 한 개 이상의 상품 가격이 필요합니다.")
    private List<@NotNull(message = "상품 가격은 null일 수 없습니다.") @Positive(message = "상품 가격은 1원 이상이어야 합니다.") Integer> itemPrices;

    public boolean isLengthEqual(){
        // 세 리스트의 크기가 같은지 확인
        return itemWeights.size() == itemQuantities.size() && itemQuantities.size() == itemPrices.size();
    }

    public Post createPost(Member member) {

        return Post.createPost(member, this.getTitle(), this.getPostDescription(), this.getDirectSaleAddress(), this.getTotalQuantity());

    }

    public List<Item> createItems() {
        List<Item> items = new ArrayList<>();
        // items를 만드는 함수를 item entity에 만드는게 맞으려나? 일단 item 1개만 생성하는 함수만 있어서 이렇게 짜둠.
        {
            IntStream.range(0,this.getItemPrices().size())
                    .forEach(i ->items.add(Item.createItem(
                                    this.getItemName(),
                                    this.getItemWeights().get(i),
                                    this.getItemQuantities().get(i),
                                    this.getItemPrices().get(i))
                            )
                    );
        }
        return items;
    }
}
