package market.agriculture.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Entity @Getter
@Slf4j
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    private int weight;

    private int quantity;

    public Item() {
    }

    //==연관관계 메서드==//
    public void setPost(Post post) {
        this.post = post;
        post.getItems().add(this);
    }
}
