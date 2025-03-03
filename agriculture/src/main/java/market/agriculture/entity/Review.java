package market.agriculture.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Entity @Getter
@Slf4j
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id") // 외래키
    private Post post;

    @Column(name = "review_title")
    private String reviewTitle;

    @Column(name = "review_description")
    private String reviewDescription;

    @Column(name = "review_date")
    private LocalDateTime createAt;


    public Review() {
    }

    //==연관관계 메서드==//
    public void setPost(Post post) {
        this.post = post;
    }

}
