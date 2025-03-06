package market.agriculture.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import market.agriculture.entity.Member;
import market.agriculture.entity.Order;
import market.agriculture.entity.Post;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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


}
