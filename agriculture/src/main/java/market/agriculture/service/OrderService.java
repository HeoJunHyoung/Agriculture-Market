package market.agriculture.service;

import lombok.extern.slf4j.Slf4j;
import market.agriculture.dto.order.CheckOrderDetailsResponse;
import market.agriculture.dto.order.CheckOrderResponse;
import market.agriculture.dto.order.CreateOrderRequest;
import market.agriculture.entity.*;
import market.agriculture.repository.ItemRepository;
import market.agriculture.repository.MemberRepository;
import market.agriculture.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
<<<<<<< HEAD
import java.util.Optional;
=======
import java.util.stream.Collectors;
>>>>>>> main

@Service
@Transactional(readOnly = true)
@Slf4j
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
     * 주문 등록
     * @param request
     * @return
     */
    @Transactional
    public Long createOrder(CreateOrderRequest request) {
        log.info("Creating order for member ID: {}", request.getMemberId());

<<<<<<< HEAD
        Optional<Member> member = memberRepository.findById(memberId);
        if(!member.isPresent()){
            throw new IllegalStateException("존재하지 않는 유저입니다.");
        }
        Item item = itemRepository.findById(itemId);
=======
        // 주문자 조회
        Member member = memberRepository.findById(request.getMemberId());
>>>>>>> main

        // 배송 정보 생성
        Delivery delivery = request.getDelivery().toEntity();
        log.info("Delivery created for order: {}", delivery);

        // 주문 상품 생성
        List<OrderItem> orderItems = request.getOrderItems().stream()
                .map(orderItemRequest -> {
                    Item item = itemRepository.findById(orderItemRequest.getItemId())
                            .orElseThrow(() -> {
                                log.error("Item not found with ID: {}", orderItemRequest.getItemId());
                                return new IllegalArgumentException("상품을 찾을 수 없습니다.");
                            });
                    return orderItemRequest.toEntity(item);
                })
                .collect(Collectors.toList());
        log.info("Order items created: {}", orderItems);

<<<<<<< HEAD
        Order order = Order.createOrder(member.get(), delivery, orderItem);
=======
        // 주문 생성
        Order order = request.toEntity(member, delivery, orderItems);
        log.info("Order created: {}", order);
>>>>>>> main

        // 주문 저장
        orderRepository.save(order);
        log.info("Order saved with ID: {}", order.getId());

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
     * 주문 전체 조회
     * @param memberId
     * @return
     */
    public List<CheckOrderResponse> getOrdersByMemberId(Long memberId) {
        List<Order> orders = orderRepository.findOrderByMemberId(memberId);
        return orders.stream()
                .map(CheckOrderResponse::new)
                .collect(Collectors.toList());
    }


    /**
     * 주문 상세 조회
     * @param orderId
     * @return
     */
    public CheckOrderDetailsResponse getOrdersDetailsByOrderId(Long orderId) {
        Order order = orderRepository.findOrderDetailsByOrderId(orderId);
        log.info("Order: {}", order);
        log.info("Order Items: {}", order.getOrderItems());
        log.info("Order Member: {}", order.getMember());
        log.info("Order Delivery: {}", order.getDelivery());

        CheckOrderDetailsResponse orderDto = new CheckOrderDetailsResponse(order);
        log.warn("CheckOrderDetailsResponse: {}", orderDto); // DTO 객체 로깅
        return orderDto;
    }


    /**
     * 주문 검색
     * TO DO
     */
//    public List<Order> findOrders(OrderSearch orderSearch) {
//
//    }

}
