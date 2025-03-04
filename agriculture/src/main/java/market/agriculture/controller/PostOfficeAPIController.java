package market.agriculture.controller;

import market.agriculture.dto.order.OrderDto;
import market.agriculture.entity.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 우체국 API의 요청을 받는 컨트롤러입니다.
 *
 * 배송이 시작된 주문의 state 값을 변경한다.
 */
@RestController
@RequestMapping("/post-office-api")
public class PostOfficeAPIController {


    /**
     *
     * @param orderDtoList
     * @apiNote 배송완료 처리된 주문의 값들을 받아 db의 배송정보 값을 변경한다.
     */
    @GetMapping("/delivery/start")
    public void getDeliveryStarted(List<OrderDto> orderDtoList){

    }

}
