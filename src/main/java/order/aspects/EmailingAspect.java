package order.aspects;

import order.dto.OrderDTO;
import order.services.MailService;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class EmailingAspect {

    @Autowired
    MailService mailService;

    @AfterReturning(returning = "orderDTOResponseEntity", pointcut = "execution(* order.controller.OrderController.add(..))")
    public void validateOrderAndSendEmail(ResponseEntity<?> orderDTOResponseEntity) {
        System.out.println("Aspect Activated");
//        if (orderDTOResponseEntity.getBody().getClass() != OrderDTO.class) {
//            return;
//        }
        if (orderDTOResponseEntity.getBody() instanceof OrderDTO){
            mailService.sendEmail((OrderDTO) orderDTOResponseEntity.getBody());
            System.out.println("Sending Email...");
        }
    }

}
