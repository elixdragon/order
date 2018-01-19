package order.services;

import order.dto.OrderDTO;
import order.entity.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderServices {
    OrderDTO add(Order order) throws Exception;
    void deleteByOrderId(String orderId);
    OrderDTO findOrderDTOById(String orderId);
    List<OrderDTO> findJoinedRecent(int page, int size, String uid);

    List<Order> findRealRecent(int start, int count);
}
