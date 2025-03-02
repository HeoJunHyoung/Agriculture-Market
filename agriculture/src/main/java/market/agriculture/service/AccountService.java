package market.agriculture.service;

import market.agriculture.dto.JoinDto;
import market.agriculture.entity.Customer;
import market.agriculture.entity.Seller;
import market.agriculture.entity.enumerate.Role;
import market.agriculture.repository.CustomerRepository;
import market.agriculture.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final CustomerRepository customerRepository;
    private final SellerRepository sellerRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public AccountService(CustomerRepository customerRepository, SellerRepository sellerRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.customerRepository = customerRepository;
        this.sellerRepository = sellerRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    public void join(JoinDto joinDto){

        if(joinDto.getRole().equals("Customer")){
            Customer customer = new Customer();
            customer.setRole(Role.Customer);
            customer.setUsername(joinDto.getUsername());
            customer.setPassword(bCryptPasswordEncoder.encode(joinDto.getPassword()));
            customerRepository.save(customer);

        }
        else{
            Seller seller = new Seller();
            seller.setRole(Role.Seller);
            seller.setUsername(joinDto.getUsername());
            seller.setPassword(bCryptPasswordEncoder.encode(joinDto.getPassword()));
            sellerRepository.save(seller);

        }
    }
}
