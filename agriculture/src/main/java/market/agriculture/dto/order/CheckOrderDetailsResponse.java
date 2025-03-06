package market.agriculture.dto.order;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import market.agriculture.entity.Order;
import market.agriculture.entity.embedded.Address;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Getter @Setter
public class CheckOrderDetailsResponse {

    private Long orderId;

    private String name;

    private List<CheckOrderDetailsItemResponse> orderItemResponseList;

    public CheckOrderDetailsResponse() {
    }

    public CheckOrderDetailsResponse(Order order) {
        orderId = order.getId();
        name = order.getMember() != null ? order.getMember().getNickname() : null; // null 체크
        orderItemResponseList = order.getOrderItems() != null ?
                order.getOrderItems().stream()
                        .map(orderItem -> new CheckOrderDetailsItemResponse(orderItem))
                        .collect(Collectors.toList()) : Collections.emptyList(); // null 체크
    }

    @Override
    public String toString() {
        return "CheckOrderDetailsResponse{" +
                "orderId=" + orderId +
                ", name='" + name + '\'' +
                ", orderItemResponseList=" + (orderItemResponseList != null ?
                orderItemResponseList.stream()
                        .map(CheckOrderDetailsItemResponse::toString)
                        .collect(Collectors.toList()) : "null") +
                '}';
    }
}
