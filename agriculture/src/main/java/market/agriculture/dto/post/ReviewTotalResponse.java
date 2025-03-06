package market.agriculture.dto.post;

import lombok.Getter;
import lombok.Setter;
import market.agriculture.entity.Review;

import java.time.LocalDateTime;

@Getter @Setter
public class ReviewTotalResponse {
    public ReviewTotalResponse() {
    }

    String title;
    String description;
    LocalDateTime writeTime;
    String nickname;

    public static ReviewTotalResponse createByReview(Review review) {

        ReviewTotalResponse response = new ReviewTotalResponse();
        response.setTitle(review.getReviewTitle());
        response.setDescription(review.getReviewDescription());
        response.setWriteTime(review.getCreatedAt());
        response.setNickname(review.getMember().getNickname());

        return response;
    }
}
