package market.agriculture.dto.post;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import market.agriculture.entity.Member;
import market.agriculture.entity.Post;
import market.agriculture.entity.embedded.Address;
import market.agriculture.entity.embedded.Phone;
import market.agriculture.entity.enumerate.Role;

import java.util.List;

@Getter @Setter
@Slf4j
public class CreatePostRequest {

    private Long memberId;

    private String nickName;

    private String postTitle;

    private String postDescription;

    private Long totalQuantity;

    private Address directSaleAddress;

    private Phone phoneNumber;

    private List<CreateItemDto> items;

    public CreatePostRequest() {
    }

}
