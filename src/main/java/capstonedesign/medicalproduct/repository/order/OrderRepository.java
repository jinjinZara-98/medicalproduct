package capstonedesign.medicalproduct.repository.order;

import capstonedesign.medicalproduct.domain.entity.Order;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long>, OrderCustomRepository {

    @EntityGraph(value = "Order.all", type = EntityGraph.EntityGraphType.LOAD)
    List<Order> findAll();
}
