package market.agriculture.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import market.agriculture.entity.Item;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ItemRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Item item) {
        em.persist(item);
    }

    public Item findById(Long itemId) {
        return em.find(Item.class, itemId);
    }


}
