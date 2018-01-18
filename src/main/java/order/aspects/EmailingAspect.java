package order.aspects;

import order.RestClient;
import order.dto.OrderDTO;
import order.dto.ProductDTO;
import order.entity.Order;
import order.services.MailService;
import order.services.ServiceUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@Aspect
@Component
public class EmailingAspect{

    @Autowired
    MailService mailService;

    @Around("execution(* order.controller.OrderController.add(..))")
    public ResponseEntity<?> validateOrderAndSendEmail(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("Aspect fired");
        Object arg = proceedingJoinPoint.getArgs()[0];
        Order order = (Order) arg;
        Map<String, Integer> quantityMap = new HashMap<>();
        order.getProductInfos().forEach((pid, pinfo) ->{
            quantityMap.put(pid, pinfo.getUnits());
        });

        List<ProductDTO> productDTOList = ServiceUtils.getProductListFromPIds(new ArrayList<>(order.getProductInfos().keySet()));
        AtomicReference<ProductDTO> faultProduct = new AtomicReference<>(null);
        AtomicReference<Boolean> flag = new AtomicReference<>(true);
        productDTOList.forEach(productDTO -> {
            if (productDTO.getpUnit() < quantityMap.get(productDTO.getProductId())){
                flag.set(false);
                faultProduct.set(productDTO);
            }
        });
        if (!flag.get() && faultProduct.get() != null){
            new ResponseEntity<>("Can't purchase " + faultProduct.get().getpName() + " as it is out of stock.", HttpStatus.NOT_ACCEPTABLE);
        }
        Object object =  proceedingJoinPoint.proceed();
        ResponseEntity <OrderDTO> responseEntity = (ResponseEntity<OrderDTO>) object;
        OrderDTO orderDTO = responseEntity.getBody();
        mailService.sendEmail(orderDTO);
        return responseEntity;
    }
}
