package market.agriculture.controller;

import jakarta.servlet.http.HttpServletRequest;
import market.agriculture.dto.order.OrderDeliveryDto;
import market.agriculture.dto.order.OrderDto;
import market.agriculture.dto.order.OrderExecutionDto;
import market.agriculture.dto.order.OrderInfoDto;
import market.agriculture.entity.Order;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 주문 관련 API를 제공하는 컨트롤러이다.
 *
 * 주문 발생시 db저장 및 PostOfficeAPI 호출 처리한다.
 * 주문 조회시 상세 정보를 반환한다.
 * 구매자는 주문한 목록 전체를 조회한다.
 * 판매자는 배송처리되지 않은 상품을 조회한다.
 * 주문을 취소한다.
 */
@RestController
@RequestMapping("/order")
public class OrderController {

//    private final JwtUtil jwtUtil;
//    private final MemberService memberService;
//    private final OrderService orderService;


    /**
     *
     * @param orderExecutionDto (item_id, quantity(주문수량))
     * @return 주문 정보 및 배송정보 OrderDeliveryDto 반환
     * @apiNote 주문 발생시 호출되는 요청이다. 주문 테이블에 저장 및 api 서버로 주문을 전달한다.
     */
    @PostMapping("")
    public OrderDeliveryDto orderItem(OrderExecutionDto orderExecutionDto){


//        우체국API 서버로 요청 보내는 로직 필요

        return null;

    }

    /**
     *
     * @param order_id
     * @return 주문 정보 및 배송정보 OrderDeliveryDto 반환
     * @apiNote 주문 정보에 대한 상세 조회 페이지를 위한 요청이다.
     */
    @GetMapping("/{order_id}")
    public OrderDeliveryDto getOrderDetail(@PathVariable Long order_id){

        return null;

    }

    /**
     *
     * @param request
     * @return List<OrderInfoDto>
     * @apiNote 자신이 주문한 주문내역 전체를 조회하기 위한 요청이다.
     */
    @GetMapping("/history")
    public List<OrderInfoDto> getOrdersHistory(HttpServletRequest request){
        String accessToken = request.getHeader("Authorization");
//        String username = jwtutil.getUsername(accessToken);
//        Member member = memberservice.findByUsername(username);

        return null;
    }

    /**
     *
     * @param request
     * @return List<>
     * @apiNote 배송처리하지 못한 모든 주문을 Item단위로 묶어서 조회하기 위한 요청이다.
     */
    @GetMapping("/unprocessed")
    public void getUnprocessedOrders(HttpServletRequest request){
        String accessToken = request.getHeader("Authorization");
//        String username = jwtutil.getUsername(accessToken);
//        Member member = memberservice.findByUsername(username);

    }


    /**
     *
     * @param orderDtoList
     * @apiNote 진행중인 주문을 취소하기 위한 요청이다. 배송완료된 주문은 취소 불가능하다.
     */
    @PostMapping("/cancle")
    public void cancleOrder(List<OrderDto> orderDtoList){

    }




}
