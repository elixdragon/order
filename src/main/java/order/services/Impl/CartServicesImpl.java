package order.services.Impl;

import order.Values;
import order.services.CartServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class CartServicesImpl implements CartServices {

    @Autowired
    RestTemplate restTemplate;

    @Override
    @Async
    public void emptyCart(String uid) {
        System.out.println("CALLING CART API");
        try{
            restTemplate.delete(Values.CART_API_BASE + Values.CART_API_DELETE_ENDPOINT + "/" + uid);
        }catch(RestClientException e) {
            System.out.println("CALL TO CART API FAILED");
        }
    }

}
