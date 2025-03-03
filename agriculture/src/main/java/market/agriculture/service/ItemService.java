//package market.agriculture.service;
//
//import market.agriculture.entity.Item;
//import market.agriculture.repository.ItemRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@Service
//@Transactional(readOnly = true)
//public class ItemService {
//
//    private final ItemRepository itemRepository;
//
//    @Autowired
//    public ItemService(ItemRepository itemRepository) {
//        this.itemRepository = itemRepository;
//    }
//
//    @Transactional
//    public Long registerItem(Item item) {
//        itemRepository.save(item);
//        return item.getId();
//    }
//
//}
