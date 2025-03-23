package market.agriculture.entity;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ReviewTest {

    @Test
    public void testCreateReview() {
        //given
        Member member = new Member();
        Post post = new Post();

        int satisfy = 5;
        String reviewTitle = "좋은 상품입니다.";
        String reviewDescription = "귤이 아주 신선하고 과즙이 터지는게 아주 좋네유~";

        //when
        Review review = Review.createReview(member, post, satisfy, reviewTitle, reviewDescription);

        // then
        assertThat(review.getMember()).isEqualTo(member);
        assertThat(review.getPost()).isEqualTo(post);
        assertThat(review.getSatisfy()).isEqualTo(satisfy);
        assertThat(review.getReviewTitle()).isEqualTo(reviewTitle);
        assertThat(review.getReviewDescription()).isEqualTo(reviewDescription);
        assertThat(review.getCreatedAt()).isBeforeOrEqualTo(LocalDateTime.now());
    }

}