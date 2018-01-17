package order.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@Document
public class Order {
    @Id
    private String orderId;
    private String uId;
    private Map<String, ProductInfo> productInfos;
    private LocalDate date;

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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
