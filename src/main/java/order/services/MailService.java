package order.services;

import order.dto.OrderDTO;
import org.springframework.stereotype.Service;

@Service
public interface MailService {
    void sendEmail(OrderDTO Order);
}
