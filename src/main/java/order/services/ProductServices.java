package order.services;

import order.dto.ProductDTO;
import order.entity.ProductCache;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.util.List;

public interface ProductServices{

    List<ProductDTO> getProductCacheList(List <String> productIdList);
    List <ProductDTO> getProductDTOListFromAPI(List <String> productIdList);
    void saveToCache(List <ProductCache> productCacheList);
    void saveToCacheFromDTOs(List <ProductDTO> productDTOList);

}
