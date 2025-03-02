package market.agriculture.repository;

import market.agriculture.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SellerRepository extends JpaRepository<Seller,Long> {
    Optional<Seller> findByUsername(String username);
}
