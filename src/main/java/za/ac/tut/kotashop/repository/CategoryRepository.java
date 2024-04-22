package za.ac.tut.kotashop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.tut.kotashop.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
