package order.dto;

import java.time.LocalDate;
import java.util.List;

public class OrderDTO {
    private String orderId;
    private String uId;
    private LocalDate date;
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

    /*public Map<Long, ProductInfo> getProductInfos() {
        return productInfos;
    }

    public void setProductInfos(Map<Long, ProductInfo> productInfos) {
        this.productInfos = productInfos;
    }*/

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }
}
