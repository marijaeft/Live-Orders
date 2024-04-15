package mk.payten.live_orders.web;

import mk.payten.live_orders.OrderEventPublisher;
import mk.payten.live_orders.model.OrderEntity;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalTime;

@RestController
@RequestMapping("sse")
public class SSEController {

    private final OrderEventPublisher eventPublisher;
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(SSEController.class);

    public SSEController(OrderEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @GetMapping( produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<String>> streamEvents() {
        return eventPublisher.getOrderEvents();
    }

}
