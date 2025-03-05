package market.agriculture.service;

import market.agriculture.entity.*;
import market.agriculture.repository.ItemRepository;
import market.agriculture.repository.MemberRepository;
import market.agriculture.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class OrderService {

    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;

    @Autowired
    public OrderService(MemberRepository memberRepository, OrderRepository orderRepository, ItemRepository itemRepository) {
        this.memberRepository = memberRepository;
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
    }

    /**
     * 주문
     * @param memberId
     * @param itemId
     * @param count
     * @return
     */
    @Transactional
    public Long order(Long memberId, Long itemId, int count) {

        Optional<Member> member = memberRepository.findById(memberId);
        if(!member.isPresent()){
            throw new IllegalStateException("존재하지 않는 유저입니다.");
        }
        Item item = itemRepository.findById(itemId);

        Delivery delivery = Delivery.createDelivery();

        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        Order order = Order.createOrder(member.get(), delivery, orderItem);

        orderRepository.save(order);
        return order.getId();
    }


    /**
     * 주문 취소
     * @param orderId
     */
    @Transactional
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findOne(orderId);
        order.cancel();
    }


    /**
     * 주문 검색
     * TO DO
     */
//    public List<Order> findOrders(OrderSearch orderSearch) {
//
//    }

}
