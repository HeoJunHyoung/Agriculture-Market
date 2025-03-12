package market.agriculture.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
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

    public Post findById(Long postId) {
        return em.find(Post.class, postId);
    }

    public List<Post> findPostsByMemberId(Long memberId) {
        return em.createQuery("select p from Post p " +
                        "join fetch p.member m " +
                        "where m.id = :memberId", Post.class)
                .setParameter("memberId", memberId)
                .getResultList();
    }

    public Post findPostDetailsByPostId(Long postId) {
        return em.createQuery("select p from Post p " +
                "join fetch p.items " +
                "where p.id = :postId", Post.class)
                .setParameter("postId", postId)
                .getSingleResult();
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
