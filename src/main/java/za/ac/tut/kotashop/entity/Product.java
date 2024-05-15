package za.ac.tut.kotashop.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Arrays;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_desc", columnDefinition = "TEXT")
    private String productDescription;

    @Column(name = "product_image_path", columnDefinition = "TEXT")
    private String productImage;

    @Column(name = "price")
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "uploaded_date")
    private LocalDate uploadedDate;

    // Constructors, getters, and setters

    public Product() {
        // Default constructor
    }

    public Product(Long productId, String productName, String productDescription, int price, Category category, LocalDate uploadedDate) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.price = price;
        this.category = category;
        this.uploadedDate = uploadedDate;
    }

    public Product(String productName, String productDescription, String productImage, int price, Category category, LocalDate uploadedDate) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.productImage = productImage;
        this.price = price;
        this.category = category;
        this.uploadedDate = uploadedDate;
    }

    @Transient
    public String getFileLocation(){
        return "/uploads/products/" + productId + "/" + productImage;
    }


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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public LocalDate getUploadedDate() {
        return uploadedDate;
    }

    public void setUploadedDate(LocalDate uploadedDate) {
        this.uploadedDate = uploadedDate;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", productImage=" + productImage +
                ", price=" + price +
                ", category=" + category +
                ", uploadedDate=" + uploadedDate +
                '}';
    }
}
