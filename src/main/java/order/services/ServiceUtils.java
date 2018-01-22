package order.services;

import order.dto.OrderDTO;
import order.dto.ProductDTO;
import order.entity.Order;
import order.entity.ProductInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class ServiceUtils {

    @Autowired
    private ProductServices productServices;

    public OrderDTO makeOrderDTO(Order order, List<ProductDTO> products) {
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(order, orderDTO);
        orderDTO.setProducts(products);
        Map<String, ProductInfo> productInfoMap = order.getProductInfos();
        for (ProductDTO product : products) {
            product.setProductPrice(productInfoMap.get(product.getProductId()).getPrice());
            product.setProductUnit(productInfoMap.get(product.getProductId()).getUnits());
        }
        return orderDTO;
    }


    public List<OrderDTO> makeOrderDTOs(List<Order> orders) {
        List<OrderDTO> orderDTOList = new ArrayList<>();

        orders.forEach(order -> {
            Set<String> productIds = order.getProductInfos().keySet();
            List<ProductDTO> products = null;
            try {
                products = productServices.getProductDTOListFromAPI(productIds);
            } catch (RestClientException rce) {
                System.out.println("Unable to fetch complete information");
                //TODO : fetch from cache : DONE
                products = productServices.getProductCacheList(productIds);
                System.out.println(products);
            }
            orderDTOList.add(makeOrderDTO(order, products));
        });

        return orderDTOList;
    }


}
