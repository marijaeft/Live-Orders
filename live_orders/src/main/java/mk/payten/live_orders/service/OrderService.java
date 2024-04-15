package mk.payten.live_orders.service;

import mk.payten.live_orders.model.OrderEntity;
import mk.payten.live_orders.model.dto.CreateOrderDto;
import mk.payten.live_orders.model.dto.UpdateOrderDto;

import java.util.List;

public interface OrderService {
    OrderEntity save();
    OrderEntity update(UpdateOrderDto orderDto);
    List<OrderEntity> findAllOrders();
    OrderEntity findById(Long id);
}
