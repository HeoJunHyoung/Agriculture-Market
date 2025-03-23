package market.agriculture.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class OrderItemTest {

    @Test
    public void testCreateOrderItem() {
        // given
        Item item = new Item();
        int orderPrice = 1000;
        int count = 2;

        // when
        OrderItem orderItem = OrderItem.createOrderItem(item, orderPrice, count);

        // then
        assertThat(orderItem.getItem()).isEqualTo(item);
        assertThat(orderItem.getPrice()).isEqualTo(orderPrice);
        assertThat(orderItem.getCount()).isEqualTo(count);
    }

    @Test
    public void testGetTotalPrice() {
        // given
        OrderItem orderItem = new OrderItem();
        orderItem.setPrice(1000);
        orderItem.setCount(3);

        // when
        int totalPrice = orderItem.getTotalPrice();

        // then
        assertThat(totalPrice).isEqualTo(3000);
    }
}