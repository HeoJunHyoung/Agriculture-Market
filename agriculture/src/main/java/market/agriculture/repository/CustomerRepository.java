package market.agriculture.repository;

import market.agriculture.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByNaverId(String naverId);
}
