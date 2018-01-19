package order.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.net.URL;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@Document
public class ProductCache {

    public static final String COLLECTION_NAME = "productCache";

    @Id
    private String productId;
    private String pName;
    private String pBrand;
    private String pCategory;
    private URL pimage;

    public static String getCollectionName() {
        return COLLECTION_NAME;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpBrand() {
        return pBrand;
    }

    public void setpBrand(String pBrand) {
        this.pBrand = pBrand;
    }

    public String getpCategory() {
        return pCategory;
    }

    public void setpCategory(String pCategory) {
        this.pCategory = pCategory;
    }

    public URL getPimage() {
        return pimage;
    }

    public void setPimage(URL pimage) {
        this.pimage = pimage;
    }

    @Override
    public String toString() {
        return "ProductCache{" +
                "productId='" + productId + '\'' +
                ", pName='" + pName + '\'' +
                ", pBrand='" + pBrand + '\'' +
                ", pCategory='" + pCategory + '\'' +
                ", pimage=" + pimage +
                '}';
    }
}
