package mk.payten.live_orders;

import com.fasterxml.jackson.databind.ObjectMapper;
import mk.payten.live_orders.model.OrderEntity;
import mk.payten.live_orders.service.OrderService;
import org.hibernate.query.Order;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.time.Duration;

@Component
public class OrderEventPublisher {

    private final Sinks.Many<String> orderEventsSink;

    public OrderEventPublisher() {
        this.orderEventsSink = Sinks.many().multicast().onBackpressureBuffer();
    }

    public void publishOrderSavedEvent(OrderEntity order) {
        orderEventsSink.tryEmitNext(order.toString());
    }

    public void publishOrderUpdatedEvent(OrderEntity order) {

        orderEventsSink.tryEmitNext(order.toString());
        
    }

    public Flux<ServerSentEvent<String>> getOrderEvents() {
        return orderEventsSink.asFlux()
                .map(data -> ServerSentEvent.builder(data).build());
    }


}
