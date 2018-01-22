package order.services;

import order.dto.ProductDTO;
import order.entity.ProductCache;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ProductServices{

    List<ProductDTO> getProductCacheList(Set<String> productIdList);
    List <ProductDTO> getProductDTOListFromAPI(Set <String> productIdList);
    void saveToCache(List <ProductCache> productCacheList);
    void saveToCacheFromDTOs(List <ProductDTO> productDTOList);
    void changeQuantity(Map<String, Integer> unitMap);
}
