package market.agriculture.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
<<<<<<< HEAD
import market.agriculture.entity.Member;
import market.agriculture.entity.Order;
=======
>>>>>>> main
import market.agriculture.entity.Post;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Post post) {
        em.persist(post);
    }

    public Optional<Post> findById(Long postId) {
        return Optional.ofNullable(em.find(Post.class, postId));
    }

//    public List<Post> findbyIdWithReviewAndItem(Long postId){
//        return em.createQuery("select p from Post p "+
//                "join fetch Item i " +
//                "join fetch Review r "+
//                "where p.id = :postId ",Post.class)
//                .setParameter("postId",postId)
//                .getResultList();
//    }

    public List<Post> findAll() {
        return em.createQuery("select p from Post p", Post.class)
                .getResultList();
    }

    public Post findReviewsByPostId(Long postId) {
        return em.createQuery("select p from Post p " +
                "join fetch p.reviews r " +
                "join fetch r.member m " +
                "where p.id = :postId", Post.class)
                .setParameter("postId", postId)
                .getSingleResult();
    }



}
