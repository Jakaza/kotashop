package za.ac.tut.kotashop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.springframework.data.annotation.Id;

import jakarta.persistence.*;

import java.time.LocalDate;


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

    @Lob
    @Column(name = "product_image", columnDefinition = "BLOB")
    private byte[] productImage;

    @Column(name = "price")
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "uploaded_date")
    private LocalDate uploadedDate;

}
