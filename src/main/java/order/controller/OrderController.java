package order.controller;

import com.mongodb.util.JSON;
import order.dto.OrderDTO;
import order.entity.Order;
import order.services.CartServices;
import order.services.MailService;
import order.services.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/order")
@RestController
public class OrderController {

    @Autowired
    OrderServices orderServices;


    @Autowired
    CartServices cartServices;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<OrderDTO> add(@RequestBody Order order) {

        OrderDTO orderDTO = null;
        try {
            //place order : this can throw exception if some product in order is out of stock
            orderDTO = orderServices.add(order);
            //remove from cart
            cartServices.emptyCart(order.getuId());
            return new ResponseEntity<>(orderDTO, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Returning false response");
            return new ResponseEntity<>(orderDTO, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @RequestMapping(value = "/{orderId}", method = RequestMethod.GET)
    public ResponseEntity<?> findOne(@PathVariable("orderId") String orderId){

        OrderDTO orderDTO = orderServices.findOrderDTOById(orderId);
        return new ResponseEntity<>(orderDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/history/{uid}", method = RequestMethod.GET)
    public ResponseEntity<List<OrderDTO>> getJoinedRecent(@RequestParam(value = "p", required = false, defaultValue = "0") Integer page,
                                                          @RequestParam(value = "s", required = false, defaultValue = "10") Integer size,
                                                          @PathVariable("uid") String uid){
        if(size > 20)
            size = 20;
        return new ResponseEntity<>(orderServices.findJoinedRecent(page, size, uid), HttpStatus.OK);
    }


    public ResponseEntity<List<Order>> getRealRecent(){
        return new ResponseEntity<>(orderServices.findRealRecent(0, 0), HttpStatus.OK);
    }
}
