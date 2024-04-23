package mk.payten.live_orders.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "`order`")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "`order_status`")
    private OrderStatus orderStatus;

    @Column(name = "`order_number`")
    private Integer orderNumber;

    @UpdateTimestamp(source = SourceType.DB)
    @Column(name = "`created_at`",nullable = false, updatable = false)
    private LocalDateTime createdAt=LocalDateTime.now();

    @UpdateTimestamp(source = SourceType.DB)
    @Column(name = "`updated_at`", nullable = false)
    private LocalDateTime updatedAt=LocalDateTime.now();

    public OrderEntity(OrderStatus orderStatus, int orderNumber) {
        this.orderStatus = orderStatus;
        this.orderNumber = orderNumber;
    }

    public OrderEntity() {
    }

    @Override
    public String toString() {
        return  "{" +
                "\"id\": " + id +
                ", \"orderStatus\": \"" + orderStatus + "\"" +
                ", \"orderNumber\": " + orderNumber +
                ", \"updatedAt\" : \"" + updatedAt.toString() + "\"" +
                "}";
    }
}
