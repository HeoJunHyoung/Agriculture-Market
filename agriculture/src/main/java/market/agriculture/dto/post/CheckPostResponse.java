package market.agriculture.dto.post;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import market.agriculture.entity.Post;
import market.agriculture.entity.embedded.Address;

import java.time.LocalDateTime;

@Getter @Setter
@Slf4j
public class CheckPostResponse {

    private Long postId;

    private Long totalQuantity;

    private String postTitle;

    private String postDescription;

    private Address address;

    public CheckPostResponse() {
    }

    public CheckPostResponse(Post post) {
        postId = post.getId();
        totalQuantity = post.getTotalQuantity();
        postTitle = post.getPostTitle();
        postDescription = post.getPostDescription();
        address = post.getDirectSaleAddress();
    }
}
