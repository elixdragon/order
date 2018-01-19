package order.controller;

import order.Exceptions.CustomException;
import order.dto.OrderDTO;
import order.entity.Order;
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


    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public ResponseEntity<?> add(@RequestBody Order order) {
        System.out.println(order);
        OrderDTO orderDTO = null;
        try {
            //place order : this can throw exception if some product in order is out of stock or catalogue api is down
            orderDTO = orderServices.add(order);
            System.out.println("OrderDTO " + orderDTO);
            return new ResponseEntity<>(orderDTO, HttpStatus.OK);
        } catch (CustomException e) {
            e.printStackTrace();
            System.out.println("Returning false response");
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{orderId}")
    public ResponseEntity<?> findOne(@PathVariable("orderId") String orderId){

        OrderDTO orderDTO = orderServices.findOrderDTOById(orderId);
        return new ResponseEntity<>(orderDTO, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/history/{uid}")
    public ResponseEntity<List<OrderDTO>> getJoinedRecent(@RequestParam(value = "p", required = false, defaultValue = "0") Integer page,
                                                          @RequestParam(value = "s", required = false, defaultValue = "10") Integer size,
                                                          @PathVariable("uid") String uid){
        if(size > 20)
            size = 20;
        return new ResponseEntity<>(orderServices.findJoinedRecent(page, size, uid), HttpStatus.OK);
    }


}
