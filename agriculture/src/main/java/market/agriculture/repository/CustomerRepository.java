package market.agriculture.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import market.agriculture.entity.Customer;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CustomerRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Customer customer) {
        em.persist(customer);
    }

    public Customer findById(Long customerId) {
        return em.find(Customer.class, customerId);
    }

    public List<Customer> findByNickname(String nickname) {
        return em.createQuery("select c from Customer c where c.nickname = :nickname",
                Customer.class)
                .setParameter("nickname", nickname)
                .getResultList();
    }

    public Optional<Customer> findByUsername(String username) {
        return Optional.ofNullable(em.createQuery("select c from Customer c where c.username = :username",
                        Customer.class)
                .setParameter("username", username)
                .getSingleResult());
    }
}
