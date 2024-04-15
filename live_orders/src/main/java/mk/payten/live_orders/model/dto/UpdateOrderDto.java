package mk.payten.live_orders.model.dto;

import lombok.Data;

@Data
public class UpdateOrderDto {
    private long id;
    private String orderStatus;

    public UpdateOrderDto(long id, String orderStatus) {
        this.id = id;
        this.orderStatus = orderStatus;
    }

    public UpdateOrderDto() {
    }
}
