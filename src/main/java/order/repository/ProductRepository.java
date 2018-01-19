package order.repository;

import order.entity.ProductCache;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<ProductCache, String> {
    ProductCache findByProductId(String productId);
    List<ProductCache> findAllByProductIdIn(List<String> productIdList);

}
