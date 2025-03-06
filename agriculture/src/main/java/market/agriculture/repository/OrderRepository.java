package market.agriculture.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import market.agriculture.entity.Order;
import market.agriculture.entity.OrderItem;
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

    public List<Order> findOrderByMemberId(Long memberId) {
        return em.createQuery("select o from Order o " +
                        "join fetch o.member " +
                        "join fetch o.delivery " +
                        "where o.member.id = :memberId", Order.class)
                .setParameter("memberId", memberId)
                .getResultList();
    }

    public Order findOrderDetailsByOrderId(Long orderId) {
        return em.createQuery("select distinct o from Order o " +
                        "join fetch o.member m " +
                        "join fetch o.delivery " +
                        "join fetch o.orderItems oi " +
                        "join fetch oi.item " +
                        "where o.id = :orderId", Order.class)
                .setParameter("orderId", orderId)
                .getSingleResult();
    }


    /**
     * Dynamic Query TO DO
     * public List<Order> findAll(OrderSearch orderSearch) {
     * }
     */

}
