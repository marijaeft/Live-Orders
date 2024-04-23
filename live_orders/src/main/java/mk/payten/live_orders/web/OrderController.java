package mk.payten.live_orders.web;

import mk.payten.live_orders.model.OrderEntity;
import mk.payten.live_orders.model.dto.UpdateOrderDto;
import mk.payten.live_orders.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
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
