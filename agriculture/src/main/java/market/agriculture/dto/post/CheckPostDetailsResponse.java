package market.agriculture.dto.post;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import market.agriculture.entity.Post;
import market.agriculture.entity.embedded.Address;

import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
@Slf4j
public class CheckPostDetailsResponse {

    private Long postId;

    private String postTitle;

    private String postDescription;

    private Address directSaleAddress;

    private List<CheckItemDto> items;

    public CheckPostDetailsResponse() {
    }

    public CheckPostDetailsResponse(Post post) {
        postId = post.getId();
        postTitle = post.getPostTitle();
        postDescription = post.getPostDescription();
        directSaleAddress = post.getDirectSaleAddress();
        items = post.getItems().stream()
                .map(CheckItemDto::new)
                .collect(Collectors.toList());
    }
}
