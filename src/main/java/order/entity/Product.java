package order.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.net.URL;

@JsonIgnoreProperties(ignoreUnknown = true)
@Document
public class Product {

    public static final String COLLECTION_NAME = "product";

    @Id
    private String productId;
    private String productName;
    private Double productPrice;
    private String productBrand;
    private String productCategory;
    private URL productImage;
    private Integer productUnits;

    public static String getCollectionName() {
        return COLLECTION_NAME;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public URL getProductImage() {
        return productImage;
    }

    public void setProductImage(URL productImage) {
        this.productImage = productImage;
    }

    public Integer getProductUnits() {
        return productUnits;
    }

    public void setProductUnits(Integer productUnits) {
        this.productUnits = productUnits;
    }
}
