package market.agriculture.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id") // 외래키
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "review_title")
    private String reviewTitle;

    @Min(1)
    @Max(5)
    private int satisfy;

    @Column(name = "review_description")
    private String reviewDescription;

    @Column(name = "review_date")
    private LocalDateTime createdAt;


    public Review() {
    }

    public void setPost(Post post) {
        this.post = post;
        post.getReviews().add(this);
    }

    //== 생성 메서드 ==//
    public static Review createReview(Member member, Post post, int satisfy, String reviewTitle, String reviewDescription) {
        Review review = new Review();
        review.setMember(member);
        review.setPost(post);
        review.setSatisfy(satisfy);
        review.setReviewTitle(reviewTitle);
        review.setReviewDescription(reviewDescription);
        review.setCreatedAt(LocalDateTime.now());
        return review;
    }
}
