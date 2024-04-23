package mk.payten.live_orders.service.impl;

import mk.payten.live_orders.OrderEventPublisher;
import mk.payten.live_orders.exceptions.InvalidOrderIdException;
import mk.payten.live_orders.model.OrderEntity;
import mk.payten.live_orders.model.OrderNumberGenerator;
import mk.payten.live_orders.model.OrderStatus;
import mk.payten.live_orders.model.dto.CreateOrderDto;
import mk.payten.live_orders.model.dto.UpdateOrderDto;
import mk.payten.live_orders.repository.OrderRepository;
import mk.payten.live_orders.service.OrderService;
import mk.payten.live_orders.web.SSEController;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderEventPublisher eventPublisher;

    public OrderServiceImpl(OrderRepository orderRepository,OrderEventPublisher eventPublisher) {
        this.orderRepository = orderRepository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public OrderEntity save() {
        OrderStatus orderStatus = OrderStatus.IN_PROGRESS;
        int orderNumber = OrderNumberGenerator.getInstance().getNextOrderNumber();
        OrderEntity orderEntity = new OrderEntity(orderStatus, orderNumber);

        OrderEntity savedOrder = orderRepository.save(orderEntity);
        eventPublisher.publishOrderSavedEvent(savedOrder);

        return savedOrder;
    }

    @Override
    public OrderEntity update(UpdateOrderDto orderDto) {
        OrderEntity orderEntity = orderRepository.findById(orderDto.getId()).orElseThrow(InvalidOrderIdException::new);
        OrderStatus orderStatus = OrderStatus.valueOf(orderDto.getOrderStatus());
        orderEntity.setOrderStatus(orderStatus);

        OrderEntity updatedOrder = orderRepository.save(orderEntity);
        eventPublisher.publishOrderUpdatedEvent(updatedOrder);

        return updatedOrder;
    }

    @Override
    public List<OrderEntity> findAllOrders() {
        LocalDateTime tenSecondsAgo = LocalDateTime.now().minusSeconds(10);
        return orderRepository.fetchAll(tenSecondsAgo);
    }

    @Override
    public OrderEntity findById(Long id) {
        return orderRepository.findById(id).orElseThrow(InvalidOrderIdException::new);
    }

}
