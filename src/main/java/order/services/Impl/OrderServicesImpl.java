package order.services.Impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import order.RestClient;
import order.dto.OrderDTO;
import order.dto.ProductDTO;
import order.entity.Order;
import order.repository.OrderRepository;
import order.services.OrderServices;
import order.services.ServiceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Instant;
import java.util.*;

@Service
public class OrderServicesImpl implements OrderServices{

    @Autowired
    OrderRepository orderRepository;


    @Override
    public void add(Order order) {
        if (order.getDate() == null)
            order.setDate(Date.from(Instant.now()));
        orderRepository.save(order);
        RestClient<Map<String, Integer>> restClient = new RestClient<>();
        Map<String, Integer> requestBody = new HashMap<>();
        order.getProductInfos().forEach((pid, pinfo) ->{
            requestBody.put(pid, -pinfo.getUnits());
        });
        restClient.post(ServiceUtils.CATALOGUE_API_URI, requestBody);
    }

    @Override
    public void deleteByOrderId(String orderId) {
        orderRepository.deleteByOrderId(orderId);
    }

    @Override
    public OrderDTO findOrderDTOById(String orderId) {
        Order order = orderRepository.findByOrderId(orderId);
        List <Order> list = new ArrayList<>();
        list.add(order);
        return getOrderDTOList(list).get(0);
    }


    @Override
    public List<OrderDTO> findJoinedRecent(int page, int size, String uid) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(Sort.Direction.DESC, "date"));
        Page<Order> orders = orderRepository.findByUId(uid, pageRequest);
        List <Order> orderList = new ArrayList<>();
        orders.forEach(orderList::add);
        return getOrderDTOList(orderList);
    }

    private List<OrderDTO> getOrderDTOList(List <Order> orders){
        List<OrderDTO> orderDTOList = new ArrayList<>();
        RestClient <List<String>> restClient = new RestClient<>();
        ObjectMapper mapper = new ObjectMapper();
        orders.forEach(order ->{
            List <String> productIds = new ArrayList<>(order.getProductInfos().keySet());
            List <ProductDTO> products = null;
            String responseString = restClient.post(ServiceUtils.PRODUCT_API_URI, productIds);
            try {
                products = mapper.readValue(responseString, new TypeReference<List<ProductDTO>>(){});
            } catch (IOException e) {
                e.printStackTrace();
            }
            orderDTOList.add(ServiceUtils.makeOrderDTOfromOrder(order, products));
        });

        return orderDTOList;
    }


    //Testing Function
    @Override
    public List<Order> findRealRecent(int start, int count) {
        Iterable<Order> orders = orderRepository.findAll();
        List <Order> retList = new ArrayList<>();
        orders.forEach(retList::add);
        return retList;
    }
}
