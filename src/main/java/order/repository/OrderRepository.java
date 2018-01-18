package order.repository;

import order.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
    Order findByOrderId(String orderId);
    Order deleteByOrderId(String orderId);
    Page<Order> findByUId(String uid, Pageable pageable);
}
