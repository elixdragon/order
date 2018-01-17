package order.controller;

import order.dto.OrderDTO;
import order.entity.Order;
import order.services.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/o")
@RestController
public class OrderController {

    @Autowired
    OrderServices orderServices;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<?> add(@RequestBody Order order){
        orderServices.add(order);

        return new ResponseEntity<>("Everything went well", HttpStatus.OK);
    }

    @RequestMapping("/getOne/{orderId}")
    public ResponseEntity<?> findOne(@PathVariable("orderId") String orderId){

        OrderDTO orderDTO = orderServices.findOrderDTOById(orderId);
        return new ResponseEntity<>(orderDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/getRecent/{uid}")
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
