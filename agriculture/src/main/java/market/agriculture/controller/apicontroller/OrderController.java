package market.agriculture.controller.apicontroller;

import jakarta.validation.Valid;
import market.agriculture.dto.order.CheckOrderDetailsResponse;
import market.agriculture.dto.order.CheckOrderResponse;
import market.agriculture.dto.order.CreateOrderRequest;
import market.agriculture.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * 주문 생성
     */
    @PostMapping
    public Long createOrder(@RequestBody @Valid CreateOrderRequest request) {
        return orderService.createOrder(request);
    }

    /**
     * 주문 취소
     */
    @PostMapping("/cancel")
    public void cancelOrder(@RequestParam(value = "orderId") Long orderId) {
        orderService.cancelOrder(orderId);
    }


    /**
     * 주문 전체 확인
     */
    @GetMapping("/check")
    public List<CheckOrderResponse> checkMyOrder(@RequestParam(value="memberId") Long memberId) {
        return orderService.getOrdersByMemberId(memberId);
    }

    /**
     * 주문 상세 확인
     */
    @GetMapping("/check/details")
    public CheckOrderDetailsResponse checkMyOrderDetails(@RequestParam(value="orderId") Long orderId) {
        return orderService.getOrdersDetailsByOrderId(orderId);
    }


}
