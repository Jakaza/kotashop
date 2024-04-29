package za.ac.tut.kotashop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.tut.kotashop.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
