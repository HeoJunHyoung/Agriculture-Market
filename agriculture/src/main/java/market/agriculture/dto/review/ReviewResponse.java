package market.agriculture.dto.review;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import market.agriculture.entity.Member;
import market.agriculture.entity.Post;
import market.agriculture.entity.Review;

import java.time.LocalDateTime;

@Getter @Setter
@Slf4j
public class ReviewResponse {

    private String memberNickname;

    private int satisfy;

    private String reviewTitle;

    private String reviewDescription;

    private LocalDateTime createdAt;

    public ReviewResponse() {
    }

    public ReviewResponse(Review review) {
        memberNickname = review.getMember().getNickname();
        satisfy = review.getSatisfy();
        reviewTitle = review.getReviewTitle();
        reviewDescription = review.getReviewDescription();
        createdAt = review.getCreatedAt();
        log.warn("memberNickname = {}", memberNickname);
    }
}
