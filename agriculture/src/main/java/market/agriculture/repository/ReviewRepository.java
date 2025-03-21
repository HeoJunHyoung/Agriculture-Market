package market.agriculture.repository;

import jakarta.persistence.EntityManager;
<<<<<<< HEAD
import jakarta.persistence.PersistenceContext;
=======
>>>>>>> main
import market.agriculture.entity.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

<<<<<<< HEAD
import java.util.List;
=======
import javax.swing.text.html.parser.Entity;
>>>>>>> main

@Repository
public class ReviewRepository {

<<<<<<< HEAD
    @PersistenceContext
=======
>>>>>>> main
    private final EntityManager em;

    @Autowired
    public ReviewRepository(EntityManager em) {
        this.em = em;
    }

    public void save(Review review) {
        em.persist(review);
    }
<<<<<<< HEAD

    public List<Review> findByPostId(Long postId) {
        return em.createQuery("select r from Review r " +
                        "join fetch Post m " +
                        "where r.post.id = :postId", Review.class)
                .setParameter("postId", postId)
                .getResultList();
    }
=======
>>>>>>> main
}
