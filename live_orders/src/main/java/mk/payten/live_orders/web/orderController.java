package mk.payten.live_orders.web;

import mk.payten.live_orders.model.OrderEntity;
import mk.payten.live_orders.model.dto.CreateOrderDto;
import mk.payten.live_orders.model.dto.UpdateOrderDto;
import mk.payten.live_orders.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class orderController {
    private final OrderService orderService;

    public orderController(OrderService orderService) {
        this.orderService = orderService;
    }

    //create order,update status
    @GetMapping("/api/orders")
    public List<OrderEntity> findAllOrders() {
        return orderService.findAllOrders();
    }

    @PostMapping("/api/add/order")
    public OrderEntity addOrder() {
        return orderService.save();
    }

    @PostMapping("api/edit/order")
    public OrderEntity editOrder(@RequestBody UpdateOrderDto updateOrderDto) {
        return orderService.update(updateOrderDto);
    }

}
