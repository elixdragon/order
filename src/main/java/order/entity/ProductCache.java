package order.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.StringJoiner;

@JsonIgnoreProperties(ignoreUnknown = true)
@Document
public class ProductCache {

    public static final String COLLECTION_NAME = "productCache";

    @Id
    private String productId;
    private String productName;
    private String productBrand;
    private String productCategory;
    private String productImage;

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

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", this.getClass().getSimpleName() + "[", "]")
                .add("COLLECTION_NAME = " + COLLECTION_NAME)
                .add("productBrand = " + productBrand)
                .add("productCategory = " + productCategory)
                .add("productId = " + productId)
                .add("productImage = " + productImage)
                .add("productName = " + productName)
                .toString();
    }
}
