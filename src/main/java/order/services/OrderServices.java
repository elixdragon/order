package order.services;

import order.dto.OrderDTO;
import order.entity.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderServices {
    void add(Order order);
    void deleteByOrderId(String orderId);
    OrderDTO findOrderDTOById(String orderId);
    List<OrderDTO> findJoinedRecent(int page, int size);

    List<Order> findRealRecent(int start, int count);
}
