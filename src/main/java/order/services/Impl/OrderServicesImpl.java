package order.services.Impl;

import order.Exceptions.CustomException;
import order.Values;
import order.dto.OrderDTO;
import order.dto.ProductDTO;
import order.entity.Order;
import order.repository.OrderRepository;
import order.services.CartServices;
import order.services.OrderServices;
import order.services.ProductServices;
import order.services.ServiceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;


@Service
public class OrderServicesImpl implements OrderServices{

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    CartServices cartServices;

    @Autowired
    ProductServices productServices;

    @Autowired
    ServiceUtils serviceUtils;


    /**
     * ADD ORDER AFTER VALIDATION
     * */
    @Override
    public OrderDTO add(Order order) throws CustomException {
        if (order.getDate() == null)
            order.setDate(Date.from(Instant.now()));

        Map<String, Integer> unitMap = new HashMap<>();
        order.getProductInfos().forEach((pid, pinfo) -> {
            unitMap.put(pid, -pinfo.getUnits());
        });

        try {
            //the following call can throw RestClientException in case of api call timeout
            List<ProductDTO> productDTOList = productServices.getProductDTOListFromAPI(new ArrayList<>(order.getProductInfos().keySet()));
            System.out.println("product dto list : " + productDTOList);
            if (validateOrder(unitMap, productDTOList)) {

                //save order
                orderRepository.save(order);

                //save cache
                productServices.saveToCacheFromDTOs(productDTOList);

                //reduce quantity from catalogue
                restTemplate.put(Values.CATALOGUE_API_BASE + Values.CATALOGUE_API_UPDATE, unitMap);

                //get latest order for sending as response / for email aspect.
                Order insertedOrder = orderRepository.findFirstByUIdOrderByDateDesc(order.getuId());//to get the order ID for sending email

                //remove from cart : call cart api from here (async)
                cartServices.emptyCart(order.getuId());

                //return latest orderDTO
                return serviceUtils.makeOrderDTO(insertedOrder, productDTOList);
            } else {
                throw new CustomException("Product Out of stock");
            }
        }catch(RuntimeException r){
            throw new CustomException("Cant connect to catalogue API");
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
        return serviceUtils.makeOrderDTOs(Arrays.asList(order)).get(0);
    }

    @Override
    public List<OrderDTO> findJoinedRecent(int page, int size, String uid) {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(Sort.Direction.DESC, "date"));
        Page<Order> orders = orderRepository.findByUId(uid, pageRequest);
        List <Order> orderList = new ArrayList<>();
        orders.forEach(orderList::add);
        return serviceUtils.makeOrderDTOs(orderList);
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
