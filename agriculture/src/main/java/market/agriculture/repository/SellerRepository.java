package market.agriculture.repository;

import market.agriculture.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller,Long> {
    Seller findByNaverId(String naverId);
}
