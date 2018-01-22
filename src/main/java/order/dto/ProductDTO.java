package order.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotNull;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

/**
 * PRODUCT DTO TO SEND AS RESPONSE IN ORDER DTO
 * THIS IS WHAT THE CATALOGUE API SHOULD RETURN
*/
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDTO {

    private String productId;
    @NotNull
    private String productName;
    private Double productPrice;
    private String productBrand;
    private String productCategory;
    private String productImage;
    private Integer productUnit;
    private Date dateCreated;
    private Date dateModified;
    private String description;
    private HashMap<String, String> specifications;


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

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public Integer getProductUnit() {
        return productUnit;
    }

    public void setProductUnit(Integer productUnit) {
        this.productUnit = productUnit;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateModified() {
        return dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public HashMap<String, String> getSpecifications() {
        return specifications;
    }

    public void setSpecifications(HashMap<String, String> specifications) {
        this.specifications = specifications;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", this.getClass().getSimpleName() + "[", "]")
                .add("dateCreated = " + dateCreated)
                .add("dateModified = " + dateModified)
                .add("description = " + description)
                .add("productBrand = " + productBrand)
                .add("productCategory = " + productCategory)
                .add("productId = " + productId)
                .add("productImage = " + productImage)
                .add("productName = " + productName)
                .add("productPrice = " + productPrice)
                .add("productUnit = " + productUnit)
                .add("specifications = " + specifications)
                .toString();
    }
}
