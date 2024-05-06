package za.ac.tut.kotashop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.tut.kotashop.entity.Order;
import za.ac.tut.kotashop.entity.User;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);

    List<Order> findByUserId(Long userId);
}
