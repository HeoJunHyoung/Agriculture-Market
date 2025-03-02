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

    public Item(Long id, Post post, int weight, int quantity) {
        this.id = id;
        this.post = post;
        this.weight = weight;
        this.quantity = quantity;
    }
}
