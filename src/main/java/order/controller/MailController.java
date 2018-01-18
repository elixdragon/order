package order.controller;

import javafx.beans.binding.ListBinding;
import javafx.collections.ObservableList;
import order.dto.OrderDTO;
import order.dto.ProductDTO;
import order.entity.Order;
import order.entity.Product;
import order.services.MailService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URL;
import java.sql.Timestamp;
import java.util.*;


@RestController
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private MailService mailService;


    @RequestMapping("/smail")
    @ResponseBody
    String home() {
        try {
            OrderDTO order = new OrderDTO();
            String myUrl = "http://pipsum.com/435x310.jpg";
            URL url = new URL(myUrl);
            ProductDTO product = new ProductDTO();
            ProductDTO product1 = new ProductDTO();
            product.setProductId("ubfiuwbfiew");
            product.setpBrand("nike");
            product.setpCategory("shoes");
            product.setPimage(url);
            product.setpName("nike shoe");
            product.setpUnit(3);
            product.setpPrice(34.00);
            product1 = product;
            List<ProductDTO> products = new ArrayList<ProductDTO>();
            products.add(product);
            products.add(product1);
            order.setDate(new Timestamp(System.currentTimeMillis()));
            order.setOrderId("5a5df49b7695909c9f376ca0");
            order.setuId("ed94d79a-d28f-4dbf-8260-b1ae8c5b7166");
            order.setProducts(products);
            mailService.sendEmail(order);
            return "Email Sent!";
        }catch(Exception ex) {
            return "Error in sending email: "+ex;
        }
    }
}
