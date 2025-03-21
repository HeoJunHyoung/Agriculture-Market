package market.agriculture.controller;

import market.agriculture.dto.order.OrderDto;
import market.agriculture.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 우체국 API의 요청을 받는 컨트롤러입니다.
 *
 * 배송이 시작된 주문의 state 값을 변경한다.
 */
@RestController
@RequestMapping("/post-office-api")
public class PostOfficeAPIController {

    private final WebClient webClient;

    @Autowired
    public PostOfficeAPIController(WebClient webClient) {
        this.webClient = webClient;
    }

    /**
     *
     * @param orderDtoList
     * @apiNote 배송완료 처리된 주문의 값들을 받아 db의 배송정보 값을 변경한다.
     */
    @GetMapping("/delivery/start")
    public void getDeliveryStarted(List<OrderDto> orderDtoList){



    }

//    @GetMapping("/orderSendTest")
//    public Mono<String> test(){
//        Map<String, Object> requestBody = new HashMap<>();
//        Long itemId = 4L;
//        OrderDto orderDto = OrderDto.createOrderDto(itemId);

//        return webClient
//                .post()
//                .uri("/saveItem")
//                .bodyValue(orderDto)
//                .retrieve()
//                .onStatus(t -> HttpStatus.isError(t), response -> {
//                    return response.bodyToMono(String.class)
//                            .flatMap(errorBody -> {
//                                // Log the error body or handle it as needed
//                                return Mono.error(new RuntimeException("Server error: " + errorBody));
//                            });
//                })
//                .bodyToMono(String.class)
//                .onErrorResume(e -> {
//                    // Log the exception and return a fallback value or rethrow
//                    return Mono.error(new RuntimeException("Error during WebClient call", e));
//                });
//
//    }

}
