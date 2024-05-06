package za.ac.tut.kotashop.dto;

public class OrderProductDto {
    private Long id;
    private Long orderId;
    private Long productId;
    private String productName;
    private String productDescription;

    // Constructor, getters, and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public String toString() {
        return "OrderProductDto{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", productId=" + productId +
                '}';
    }
}