package za.ac.tut.kotashop.dto;

import jakarta.validation.constraints.*;

public class ProductDto {

    private Long productId;

    @NotEmpty(message = "Product name should not be empty")
    @Size(min = 3, message = "Product name should have minimum 3 or more characters")
    private String productName;

    @NotEmpty(message = "Product description should not be empty")
    private String productDescription;

    private String productImage;

    @NotNull(message = "Price should not be empty")
    private Integer price;

    @NotNull(message = "Category ID should not be empty")
    private Long categoryId;

    // Getters and setters

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
