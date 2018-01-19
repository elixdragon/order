package order.dto;

import java.util.Date;
import java.util.List;

public class OrderDTO {
    private String orderId;
    private String uId;
    private Date date;
    private List <ProductDTO> products;

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


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "orderId='" + orderId + '\'' +
                ", uId='" + uId + '\'' +
                ", date=" + date +
                ", products=" + products +
                '}';
    }
}
