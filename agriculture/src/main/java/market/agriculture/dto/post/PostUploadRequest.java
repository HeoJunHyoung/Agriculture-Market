package market.agriculture.dto.post;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import market.agriculture.dto.item.ItemCreateRequest;
import market.agriculture.entity.Item;
import market.agriculture.entity.Member;
import market.agriculture.entity.Post;
import market.agriculture.entity.embedded.Address;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Getter @Setter
public class PostUploadRequest {

    public PostUploadRequest() {
    }

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

    private List<ItemCreateRequest> itemCreateRequests;

    public int returnItemSize(){
        return this.itemCreateRequests.size();
    }
}
