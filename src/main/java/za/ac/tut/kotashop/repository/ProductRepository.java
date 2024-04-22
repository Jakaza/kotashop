package za.ac.tut.kotashop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.tut.kotashop.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
