package market.agriculture.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import market.agriculture.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderRepository {

    @PersistenceContext
    private final EntityManager em;

    @Autowired
    public OrderRepository(EntityManager em) {
        this.em = em;
    }

    public void save(Order order) {
        em.persist(order);
    }

    public Order findOne(Long id) {
        return em.find(Order.class, id);
    }

    public List<Order> findByMemberId(Long memberId) {
        return em.createQuery("select o from Order o " +
                        "join fetch Member m " +
                        "join fetch Delivery d " +
                        "where o.member.id = :memberId", Order.class)
                .setParameter("memberId", memberId)
                .getResultList();
    }

    /**
     * Dynamic Query TO DO
     * public List<Order> findAll(OrderSearch orderSearch) {
     * }
     */

}
