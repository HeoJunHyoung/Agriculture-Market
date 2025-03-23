package market.agriculture.entity;

import market.agriculture.entity.embedded.Address;
import market.agriculture.entity.embedded.Phone;
import market.agriculture.entity.enumerate.DeliveryStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

class DeliveryTest {

    @Test
    public void testCreateDelivery() {
        //given
        Address address = new Address("경기도", "평택시", "장당동");
        Phone phone = new Phone("010", "62904016");
        String receiverName = "허준형";

        //when
        Delivery delivery = Delivery.createDelivery(address, receiverName, phone);

        //then
        assertThat(delivery.getAddress()).isEqualTo(address);
        assertThat(delivery.getPhoneNumber()).isEqualTo(phone);
        assertThat(delivery.getReceiverName()).isEqualTo(receiverName);
        assertThat(delivery.getCreatedAt()).isBeforeOrEqualTo(LocalDateTime.now());
        assertThat(delivery.getStatus()).isEqualTo(DeliveryStatus.DEPARTED);
    }
}