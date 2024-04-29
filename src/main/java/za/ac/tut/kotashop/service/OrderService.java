package za.ac.tut.kotashop.service;

import za.ac.tut.kotashop.entity.Order;
import za.ac.tut.kotashop.entity.User;

import java.util.List;

public interface OrderService {
    void saveOrder(Order order);
    void deleteOrder(Long orderId);
    void editOrder(Order order);
    Order findById(Long id);
    List<Order> findAllOrders();

    List<Order> findOrdersByUser(User user);
}
