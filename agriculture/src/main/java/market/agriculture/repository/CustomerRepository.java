package market.agriculture.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import market.agriculture.entity.Member;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CustomerRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public Member findById(Long customerId) {
        return em.find(Member.class, customerId);
    }

    public List<Member> findByNickname(String nickname) {
        return em.createQuery("select c from Member c where c.nickname = :nickname",
                Member.class)
                .setParameter("nickname", nickname)
                .getResultList();
    }

    public Optional<Member> findByUsername(String username) {
        return Optional.ofNullable(em.createQuery("select c from Member c where c.username = :username",
                        Member.class)
                .setParameter("username", username)
                .getSingleResult());
    }
}
