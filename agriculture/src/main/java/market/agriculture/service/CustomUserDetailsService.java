package market.agriculture.service;

import market.agriculture.dto.CustomCustomerDetails;
import market.agriculture.dto.CustomSellerDetails;
import market.agriculture.entity.Customer;
import market.agriculture.entity.Seller;
import market.agriculture.repository.CustomerRepository;
import market.agriculture.repository.SellerRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final CustomerRepository customerRepository;
    private final SellerRepository sellerRepository;

    public CustomUserDetailsService(CustomerRepository customerRepository, SellerRepository sellerRepository) {
        this.customerRepository = customerRepository;
        this.sellerRepository = sellerRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Customer> optionalCustomer = customerRepository.findByUsername(username);

        if (optionalCustomer.isPresent()){
            return new CustomCustomerDetails(optionalCustomer.get());
        }
        else{
            Optional<Seller> optionalSeller = sellerRepository.findByUsername(username);
            if(optionalSeller.isPresent()){
                return new CustomSellerDetails(optionalSeller.get());
            }
            else{
                return null;
            }

        }

    }
}
