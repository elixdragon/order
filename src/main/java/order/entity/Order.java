package order.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Map;
import java.util.StringJoiner;

@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = Order.COLLECTION_NAME)
public class Order {
    public static final String COLLECTION_NAME = "orders";

    @Id
    private String orderId;
    private String uId;
    private Map<String, ProductInfo> productInfos;
    private Date date;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public Map<String, ProductInfo> getProductInfos() {
        return productInfos;
    }

    public void setProductInfos(Map<String, ProductInfo> productInfos) {
        this.productInfos = productInfos;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    @Override
    public String toString() {
        return new StringJoiner(", ", this.getClass().getSimpleName() + "[", "]")
                .add("date = " + date)
                .add("orderId = " + orderId)
                .add("productInfos = " + productInfos)
                .add("uId = " + uId)
                .toString();
    }
}
