package order.services.Impl;

import com.mongodb.connection.QueryResult;
import order.entity.Product;
import order.repository.ProductRepository;
import order.services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServicesImpl implements ProductServices{

    @Autowired
    MongoTemplate mongoTemplate;
    @Autowired
    ProductRepository productRepository;
    @Override
    public Product findByProductId(String productId) {
        return productRepository.findByProductId(productId);
    }

    @Override
    public void add(Product product) {
        productRepository.save(product);
    }

    @Override
    public List<Product> findByProductIds(List<String> productIds) {
        Query query = new Query();
        query.addCriteria(Criteria.where("productId").in(productIds));
        return mongoTemplate.find(query, Product.class);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
