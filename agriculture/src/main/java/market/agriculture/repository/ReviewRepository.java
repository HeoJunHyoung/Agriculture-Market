package market.agriculture.repository;

import jakarta.persistence.EntityManager;
import market.agriculture.entity.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.parser.Entity;

@Repository
public class ReviewRepository {

    private final EntityManager em;

    @Autowired
    public ReviewRepository(EntityManager em) {
        this.em = em;
    }

    public void save(Review review) {
        em.persist(review);
    }
}
