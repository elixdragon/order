package order.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import order.RestClient;
import order.dto.OrderDTO;
import order.dto.ProductDTO;
import order.entity.Order;
import order.entity.ProductInfo;
import org.springframework.beans.BeanUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ServiceUtils {
    public final static String SEARCH_API_URI = "http://10.177.7.117:8080/search/getList";
    public final static String CATALOGUE_API_URI = "http://10.177.7.117:8081/catalogue/update";


    static RestClient<List<String>> restClientString = new RestClient<>();
    static ObjectMapper mapper = new ObjectMapper();

    public static OrderDTO makeOrderDTOfromOrder(Order order, List<ProductDTO> products){
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(order, orderDTO);
        orderDTO.setProducts(products);
        Map<String, ProductInfo> productInfoMap = order.getProductInfos();
        for (ProductDTO product : products){
            product.setpPrice(productInfoMap.get(product.getProductId()).getPrice());
            product.setpUnit(productInfoMap.get(product.getProductId()).getUnits());
        }
        return orderDTO;
    }

    public static List <ProductDTO> getProductListFromPIds(List <String> productIds){
        List <ProductDTO> products = null;
        String responseString = restClientString.post(ServiceUtils.SEARCH_API_URI, productIds);
        try {
            products = mapper.readValue(responseString, new TypeReference<List<ProductDTO>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }

    public static List<OrderDTO> makeOrderDTOsFromOrderList(List <Order> orders){
        List<OrderDTO> orderDTOList = new ArrayList<>();

        orders.forEach(order ->{
            List <String> productIds = new ArrayList<>(order.getProductInfos().keySet());
            List <ProductDTO> products = ServiceUtils.getProductListFromPIds(productIds);
            orderDTOList.add(ServiceUtils.makeOrderDTOfromOrder(order, products));
        });

        return orderDTOList;
    }
}
