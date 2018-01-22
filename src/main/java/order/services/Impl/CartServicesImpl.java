package order.services.Impl;

import com.fasterxml.jackson.databind.node.ObjectNode;
import order.ApiEndpoints;
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

    @Autowired
    ObjectNode objectNode;

    @Override
    @Async
    public void emptyCart(String uid) {
        System.out.println("CALLING CART API");
        try{
            objectNode.removeAll();
            objectNode.put("userId", uid);
            System.out.println(objectNode);
            restTemplate.postForEntity(ApiEndpoints.CART_API_BASE + ApiEndpoints.CART_API_DELETE_ENDPOINT, objectNode, String.class);
        }catch(RestClientException e) {
            e.printStackTrace();
            System.out.println("CALL TO CART API FAILED");
        }
    }

}
