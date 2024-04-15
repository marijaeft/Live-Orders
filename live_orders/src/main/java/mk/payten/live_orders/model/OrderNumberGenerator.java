package mk.payten.live_orders.model;

public class OrderNumberGenerator {
    private static OrderNumberGenerator instance;
    private int orderNumber;

    private OrderNumberGenerator() {
        orderNumber = 1;
    }

    public static OrderNumberGenerator getInstance() {
        if (instance == null) {
            synchronized (OrderNumberGenerator.class) {
                if (instance == null) {
                    instance = new OrderNumberGenerator();
                }
            }
        }
        return instance;
    }

    public int getNextOrderNumber() {
        return orderNumber++;
    }

    public void resetOrderNumber() {
        orderNumber = 1;
    }

}
