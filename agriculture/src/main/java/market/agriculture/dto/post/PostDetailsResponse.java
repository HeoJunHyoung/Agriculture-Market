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

    public static PostDetailsResponse createByPost(Post post){

        PostDetailsResponse postDetailsResponse = new PostDetailsResponse();
        Member seller = post.getMember();

        postDetailsResponse.setSellerNickname(seller.getNickname());
        postDetailsResponse.setPhoneNumber(seller.getPhoneNumber().toString());

        postDetailsResponse.setPostTitle(post.getPostTitle());
        postDetailsResponse.setPostDescription(post.getPostDescription());
        postDetailsResponse.setCreatedAt(post.getCreatedAt());
        postDetailsResponse.setTotalQuantity(post.getTotalQuantity());
        postDetailsResponse.setDirectSaleAddress(post.getDirectSaleAddress().toString());

        List<ItemTotalResponse> itemTotalResponseList = new ArrayList<>();
        post.getItems().stream().forEach(item -> {
            itemTotalResponseList.add(ItemTotalResponse.createByReview(item));
        });
        postDetailsResponse.setItemTotalResponses(itemTotalResponseList);

        List<ReviewTotalResponse> reviewTotalResponseList = new ArrayList<>();
                post.getReviews().stream().forEach(review -> {
                    reviewTotalResponseList.add(ReviewTotalResponse.createByReview(review));
        });
        postDetailsResponse.setReviewTotalRespones(reviewTotalResponseList);


        return postDetailsResponse;

    }

}

