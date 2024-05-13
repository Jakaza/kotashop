package za.ac.tut.kotashop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import za.ac.tut.kotashop.entity.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT new Product(p.productId, p.productName, p.productDescription, p.price, p.category, p.uploadedDate) FROM Product p")
    List<Product> findProductsWithoutImage();

}
