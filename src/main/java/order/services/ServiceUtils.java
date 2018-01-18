package order.services;

import order.dto.OrderDTO;
import order.dto.ProductDTO;
import order.entity.Order;
import order.entity.ProductInfo;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Map;

public class ServiceUtils {
    public final static String SEARCH_API_URI = "http://10.177.7.117:8080/search/getList";
    public final static String CATALOGUE_API_URI = "http://10.177.7.117:8081/catalogue/update";

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
}
