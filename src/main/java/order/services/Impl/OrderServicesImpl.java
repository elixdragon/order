package order.services.Impl;

import order.RestClient;
import order.dto.OrderDTO;
import order.dto.ProductDTO;
import order.entity.Order;
import order.repository.OrderRepository;
import order.services.OrderServices;
import order.services.ServiceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

import static order.services.ServiceUtils.makeOrderDTOsFromOrderList;

@Service
public class OrderServicesImpl implements OrderServices{

    @Autowired
    OrderRepository orderRepository;


    /**
     * ADD ORDER AFTER VALIDATION
     * */
    @Override
    public OrderDTO add(Order order) throws Exception {
        if (order.getDate() == null)
            order.setDate(Date.from(Instant.now()));

        Map<String, Integer> unitMap = new HashMap<>();
        order.getProductInfos().forEach((pid, pinfo) -> {
            unitMap.put(pid, -pinfo.getUnits());
        });

        List<ProductDTO> productDTOList = ServiceUtils.getProductListFromPIds(new ArrayList<>(order.getProductInfos().keySet()));
        if (validateOrder(unitMap, productDTOList)) {
            orderRepository.save(order);//save order

            RestClient<Map<String, Integer>> restClient = new RestClient<>();

            restClient.post(ServiceUtils.CATALOGUE_API_URI, unitMap);//reduce item quantity from catalogue
            Order insertedOrder = orderRepository.findFirstByUIdOrderByDateDesc(order.getuId());//to get the order ID for sending email

            return ServiceUtils.makeOrderDTOfromOrder(insertedOrder, productDTOList);
        }else{
            throw new Exception("Product Out of stock");
        }
    }

    @Override
    public void deleteByOrderId(String orderId) {
        orderRepository.deleteByOrderId(orderId);
    }

    /**
     * IF ORDER CONTAINS MORE UNITS THAN STOCK THEN RETURN FALSE ELSE RETURN TRUE
     * */
    public Boolean validateOrder(Map<String, Integer> unitMap, List<ProductDTO> products){
        AtomicReference<Boolean> allGood = new AtomicReference<>(true);
        products.forEach(productDTO -> {
            if (Math.abs(unitMap.get(productDTO.getProductId())) > productDTO.getpUnit()){
                allGood.set(false);
            }
        });
        return allGood.get();
    }

    @Override
    public OrderDTO findOrderDTOById(String orderId) {
        Order order = orderRepository.findByOrderId(orderId);
        return makeOrderDTOsFromOrderList(Arrays.asList(order)).get(0);
    }




    @Override
    public List<OrderDTO> findJoinedRecent(int page, int size, String uid) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(Sort.Direction.DESC, "date"));
        Page<Order> orders = orderRepository.findByUId(uid, pageRequest);
        List <Order> orderList = new ArrayList<>();
        orders.forEach(orderList::add);
        return makeOrderDTOsFromOrderList(orderList);
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
