package mk.payten.live_orders.repository;

import mk.payten.live_orders.model.OrderEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity,Long> {
    @Query("SELECT u FROM OrderEntity u WHERE u.orderStatus != 'FINISHED' OR (u.orderStatus = 'FINISHED' AND u.updatedAt >= :tenSecondsAgo)")
    List<OrderEntity> fetchAll(@Param("tenSecondsAgo") LocalDateTime tenSecondsAgo);

}
