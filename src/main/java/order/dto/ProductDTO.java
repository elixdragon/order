package order.dto;

import java.net.URL;
import java.util.Date;
import java.util.Map;

/**
 * PRODUCT DTO TO SEND AS RESPONSE IN ORDER DTO
 * THIS IS WHAT THE CATALOGUE API SHOULD RETURN
*/
public class ProductDTO {

    private String productId;
    private String pName;
    private Double pPrice;
    private String pBrand;
    private String pCategory;
    private URL pimage;
    private Integer pUnit;
    private Date dateCreated;
    private String description;
    private Map<String, String> specs;


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, String> getSpecs() {
        return specs;
    }

    public void setSpecs(Map<String, String> specs) {
        this.specs = specs;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
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

    @Override
    public String toString() {
        return "ProductDTO{" +
                "productId='" + productId + '\'' +
                ", pName='" + pName + '\'' +
                ", pPrice=" + pPrice +
                ", pBrand='" + pBrand + '\'' +
                ", pCategory='" + pCategory + '\'' +
                ", pimage=" + pimage +
                ", pUnit=" + pUnit +
                ", dateCreated=" + dateCreated +
                ", description='" + description + '\'' +
                ", specs=" + specs +
                '}';
    }
}
