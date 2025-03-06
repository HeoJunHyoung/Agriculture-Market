package market.agriculture.entity;

import jakarta.persistence.*;
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

    @ManyToOne
    @JoinColumn(name = "post_id") // 외래키
    private Post post;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "review_title")
    private String reviewTitle;

    @Column(name = "review_description")
    private String reviewDescription;

    @Column(name = "review_date")
    private LocalDateTime createdAt;

    public Review() {
    }

    //==생성 메서드==//
    public static Review createReview(Post post, Member member, String reviewTitle, String reviewDescription){

        Review review = new Review();
        review.setCreatedAt(LocalDateTime.now());
        review.setPost(post);
        review.setMember(member);
        review.setReviewTitle(reviewTitle);
        review.setReviewDescription(reviewDescription);

        return review;
    }

}
