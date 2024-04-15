package mk.payten.live_orders.jobs;

import mk.payten.live_orders.model.OrderNumberGenerator;
import mk.payten.live_orders.service.OrderService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {
    private final OrderService orderService;

    public ScheduledTask(OrderService orderService) {
        this.orderService = orderService;
    }

    @Scheduled(cron = "0 0 * * *")
    public void IncrementOrderNumber()
    {
        OrderNumberGenerator.getInstance().resetOrderNumber();
    }
}
