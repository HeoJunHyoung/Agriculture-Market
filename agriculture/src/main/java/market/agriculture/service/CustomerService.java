package market.agriculture.service;

import lombok.RequiredArgsConstructor;
import market.agriculture.entity.Customer;
import market.agriculture.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional
    public Long join(Customer customer) {
        validateDuplicateMember(customer);
        customerRepository.save(customer);
        return customer.getId();
    }

    private void validateDuplicateMember(Customer customer) {
        List<Customer> findCustomers = customerRepository.findByName(customer.getNickname());
        if (!findCustomers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

}
