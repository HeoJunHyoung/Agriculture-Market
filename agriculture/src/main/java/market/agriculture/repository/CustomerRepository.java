package market.agriculture.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import market.agriculture.entity.Customer;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    public List<Customer> findByName(String name) {
        return em.createQuery("select c from Customer c where c.name = :name",
                Customer.class)
                .getResultList();
    }

}
