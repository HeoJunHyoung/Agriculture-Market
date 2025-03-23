package market.agriculture.entity;
import market.agriculture.entity.enumerate.DeliveryStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;


class OrderTest {

    @DisplayName("주문 생성 테스트")
    @Test
    public void testCreateOrder() {
        // given
        Member member = new Member();
        Delivery delivery = new Delivery();

        OrderItem orderItem1 = new OrderItem();
        OrderItem orderItem2 = new OrderItem();

        // when
        Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);

        // then
        assertThat(order.getMember()).isEqualTo(member);
        assertThat(order.getDelivery()).isEqualTo(delivery);
        assertThat(order.getOrderItems()).containsExactly(orderItem1, orderItem2);
        assertThat(order.getStatus()).isEqualTo(DeliveryStatus.PREPARED);
        assertThat(order.getCreatedAt()).isBeforeOrEqualTo(LocalDateTime.now());
    }

    @DisplayName("주문 취소 테스트")
    @Test
    public void testCancel() {
        // given
        Order order = new Order();

        Item item1 = Item.createItem("귤", 1L, 10L, 10000);
        Item item2 = Item.createItem("사과", 2L, 20L, 20000);

        OrderItem orderItem1 = OrderItem.createOrderItem(item1, 10000, 2);
        OrderItem orderItem2 = OrderItem.createOrderItem(item2, 20000, 3);

        //when(1)
        order.addOrderItem(orderItem1);
        order.addOrderItem(orderItem2);

        //then(1) : 주문 생성에 따른 재고 감소
        assertThat(item1.getStockQuantity()).isEqualTo(8L); // 10 - 2
        assertThat(item2.getStockQuantity()).isEqualTo(17L); // 20 - 3

        //when(2)
        order.cancel();

        //then(2) : 주문 취소에 따른 재고 증가
        assertThat(order.getStatus()).isEqualTo(DeliveryStatus.CANCEL);
        assertThat(item1.getStockQuantity()).isEqualTo(10L); // 8 + 2
        assertThat(item2.getStockQuantity()).isEqualTo(20L); // 17 + 3
    }

}