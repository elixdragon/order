package order.services;

import order.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductServices{
    Product findByProductId(String productId);
    void add(Product product);
    List<Product> findByProductIds(List <String> productIds);

    List<Product> findAll();

}
