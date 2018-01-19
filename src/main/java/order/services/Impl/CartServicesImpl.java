package order.services.Impl;

import order.Values;
import order.services.CartServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class CartServicesImpl implements CartServices {

    @Autowired
            @Qualifier("secondaryMongoTemplate")
    MongoTemplate cartMongoTemplate;

    @Override
    public void emptyCart(String uid) {
        System.out.println(cartMongoTemplate.count(new Query(), Values.COLLECTION_CART));
        cartMongoTemplate.remove(new Query().addCriteria(Criteria.where(Values.CART_USERID_FIELD_NAME).is(uid)), Values.COLLECTION_CART);
    }
}
