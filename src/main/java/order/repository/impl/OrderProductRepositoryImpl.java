//package order.repository.impl;
//
//import com.mongodb.BasicDBObject;
//import com.mongodb.DBObject;
//import order.dto.OrderDTO;
//import order.entity.ProductCache;
//import order.repository.OrderProductJoinRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.aggregation.Aggregation;
//import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
//import org.springframework.data.mongodb.core.aggregation.AggregationOperationContext;
//import org.springframework.data.mongodb.core.aggregation.AggregationResults;
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Repository
//public class OrderProductRepositoryImpl implements OrderProductJoinRepository {
//    @Autowired
//    MongoTemplate mongoTemplate;
//
//    @Override
//    public List<OrderDTO> getHistory(int start, int count) {
//
//        DBObject operation = new BasicDBObject("$lookup", new BasicDBObject("from", ProductCache.COLLECTION_NAME))
//                .append("localField", "productInfos")
//                .append("foreignField", "productId")
//                .append("as", "products");
//
//        Aggregation aggregation = Aggregation.newAggregation(context -> context.getMappedObject(operation));
//        AggregationResults<OrderDTO> aggregationResults = mongoTemplate.aggregate(aggregation, "orders", OrderDTO.class);
//        List <OrderDTO> retList = new ArrayList<>();
//        aggregationResults.forEach(retList::add);
//        return retList;
//    }
//
//    class CustomAggregationOperation implements AggregationOperation{
//
//        @Override
//        public DBObject toDBObject(AggregationOperationContext context) {
//            return null;
//        }
//    }
//
//}
