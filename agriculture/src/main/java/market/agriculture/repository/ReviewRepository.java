package market.agriculture.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import market.agriculture.entity.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReviewRepository {

    @PersistenceContext
    private final EntityManager em;

    @Autowired
    public ReviewRepository(EntityManager em) {
        this.em = em;
    }

    public void save(Review review) {
        em.persist(review);
    }

    public List<Review> findByPostId(Long postId) {
        return em.createQuery("select r from Review r " +
                        "join fetch Post m " +
                        "where r.post.id = :postId", Review.class)
                .setParameter("postId", postId)
                .getResultList();
    }
}
