package order.services.Impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import order.Values;
import order.dto.ProductDTO;
import order.entity.ProductCache;
import order.repository.ProductRepository;
import order.services.ProductServices;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServicesImpl implements ProductServices {

    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ProductRepository productRepository;


    @Override
    public List<ProductDTO> getProductCacheList(List<String> productIdList) {
        List<ProductCache> productCacheList = productRepository.findAllByProductIdIn(productIdList);
        //convert List<ProductCache> to List<ProductDTO>
        System.out.println(productCacheList);
        List<ProductDTO> productDTOList = new ArrayList<>();
        productCacheList.forEach(productCache -> {
            ProductDTO productDTO = new ProductDTO();
            BeanUtils.copyProperties(productCache, productDTO);
            productDTOList.add(productDTO);
        });
        return productDTOList;
    }

    @Override
    public List<ProductDTO> getProductDTOListFromAPI(List<String> productIds) {
        List<ProductDTO> products = null;
        //the following line could throw RestClientException
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(Values.CATALOGUE_API_BASE + Values.CATALOGUE_API_LIST, productIds, String.class);
        String responseString = responseEntity.getBody();
        try {
            products = mapper.readValue(responseString, new TypeReference<List<ProductDTO>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public void saveToCache(List<ProductCache> productCacheList) {
        productRepository.save(productCacheList);
    }

    @Override
    public void saveToCacheFromDTOs(List<ProductDTO> productDTOList) {
        List<ProductCache> productCacheList = new ArrayList<>();
        productDTOList.forEach(productDTO -> {
            ProductCache productCache = new ProductCache();
            BeanUtils.copyProperties(productDTO, productCache);
            productCacheList.add(productCache);
        });
        saveToCache(productCacheList);
    }


}
