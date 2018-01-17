package order.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.net.URL;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@Document
public class Product {

    public static final String COLLECTION_NAME = "product";

    @Id
    private String productId;
    private String pName;
    private Double pPrice;
    private String pBrand;
    private String pCategory;
    private URL pimage;
    private Integer pUnit;
    private Date dateCreated;

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

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

    public Double getpPrice() {
        return pPrice;
    }

    public void setpPrice(Double pPrice) {
        this.pPrice = pPrice;
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

    public Integer getpUnit() {
        return pUnit;
    }

    public void setpUnit(Integer pUnit) {
        this.pUnit = pUnit;
    }
}
