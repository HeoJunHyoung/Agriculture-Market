package market.agriculture.dto.review;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import market.agriculture.entity.Member;
import market.agriculture.entity.Post;
import market.agriculture.entity.Review;

import java.time.LocalDateTime;

@Getter @Setter
public class ReviewRequest {

    private Member member;

    private Post post;

    private int satisfy;

    private String reviewTitle;

    private String reviewDescription;

    private LocalDateTime createdAt;

    public ReviewRequest() {
    }

    public Review toEntity(Member member, Post post) {
        return Review.createReview(
          member,
          post,
          this.getSatisfy(),
          this.getReviewTitle(),
          this.getReviewDescription()
        );
    }
}
