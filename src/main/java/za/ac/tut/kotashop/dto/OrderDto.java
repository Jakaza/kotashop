package za.ac.tut.kotashop.dto;

import java.time.LocalDateTime;
import java.util.List;

public class OrderDto {
    private Long orderId;
    private Long userId; // Assuming you want to display user ID
    private String userName;
    private String address;
    private List<OrderProductDto> products; // Assuming you want to display order products
    private int quantity;
    private LocalDateTime orderDate;
    private String orderDated;

    // Constructor, getters, and setters


    public String getOrderDated() {
        return orderDated;
    }

    public void setOrderDated(String orderDated) {
        this.orderDated = orderDated;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<OrderProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<OrderProductDto> products) {
        this.products = products;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", products=" + products +
                ", quantity=" + quantity +
                ", orderDate=" + orderDate +
                '}';
    }
}
