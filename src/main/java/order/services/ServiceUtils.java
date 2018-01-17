package order.services;

import order.dto.OrderDTO;
import order.dto.ProductDTO;
import order.entity.Order;
import order.entity.ProductInfo;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Map;

public class ServiceUtils {
    public final static String PRODUCT_API_URI = "http://localhost:8080/p/getList";
    public static OrderDTO makeOrderDTOfromOrder(Order order, List<ProductDTO> products){
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(order, orderDTO);
//        List<ProductDTO> productDTOList = new ArrayList<>();
        orderDTO.setProducts(products);
        Map<String, ProductInfo> productInfoMap = order.getProductInfos();
        for (ProductDTO product : products){
            product.setProductPrice(productInfoMap.get(product.getProductId()).getPrice());
            product.setProductUnits(productInfoMap.get(product.getProductId()).getUnits());
        }
        return orderDTO;
    }
}
