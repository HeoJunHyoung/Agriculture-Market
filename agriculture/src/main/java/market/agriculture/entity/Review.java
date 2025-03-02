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

    @Column(name = "review_title")
    private String reviewTitle;

    @Column(name = "review_description")
    private String reviewDescription;

    @Column(name = "review_date")
    private LocalDateTime createAt;


    public Review() {
    }

}
