package market.agriculture.dto.delivery;

import lombok.Getter;
import lombok.Setter;
import market.agriculture.entity.Delivery;
import market.agriculture.entity.embedded.Address;
import market.agriculture.entity.embedded.Phone;

@Getter @Setter
public class CreateDeliveryRequest {

    private Address address;

    private String receiverName;

    private Phone receiverPhone;

    public CreateDeliveryRequest() {
    }

    public Delivery toEntity() {
        return Delivery.createDelivery(
                this.getAddress(),
                this.getReceiverName(),
                this.getReceiverPhone()
        );
    }

}
