package market.agriculture.dto.post;


import lombok.Getter;
import lombok.Setter;
import market.agriculture.dto.item.ItemTotalResponse;
import market.agriculture.entity.Member;
import market.agriculture.entity.Post;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PostDetailsResponse {

    public PostDetailsResponse() {
    }

    public String sellerNickname;

    public String phoneNumber;

    public String postTitle;

    public String postDescription;

    public LocalDateTime createdAt;

    public Long totalQuantity;

    public String directSaleAddress;

    public List<ItemTotalResponse> itemTotalResponses;

    public List<ReviewTotalResponse> reviewTotalRespones;

    public static PostDetailsResponse createPostDetailResponse(String nickname,String phoneNumber, String postTitle, String postDescription,
                                                   LocalDateTime postCreatedAt, Long totalQuantity, String directSaleAddress,
                                                   List<ItemTotalResponse> itemTotalResponses, List<ReviewTotalResponse> reviewTotalResponseList){

        PostDetailsResponse postDetailsResponse = new PostDetailsResponse();

        postDetailsResponse.setSellerNickname(nickname);
        postDetailsResponse.setPhoneNumber(phoneNumber);

        postDetailsResponse.setPostTitle(postTitle);
        postDetailsResponse.setPostDescription(postDescription);
        postDetailsResponse.setCreatedAt(postCreatedAt);
        postDetailsResponse.setTotalQuantity(totalQuantity);
        postDetailsResponse.setDirectSaleAddress(directSaleAddress);

        postDetailsResponse.setItemTotalResponses(itemTotalResponses);
        postDetailsResponse.setReviewTotalRespones(reviewTotalResponseList);

        return postDetailsResponse;

    }

}

