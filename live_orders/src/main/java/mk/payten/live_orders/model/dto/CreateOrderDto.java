package mk.payten.live_orders.model.dto;

import lombok.Data;

@Data
public class CreateOrderDto {
    private int orderNumber;

    public CreateOrderDto( int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public CreateOrderDto() {
    }
}
