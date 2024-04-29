package za.ac.tut.kotashop.service;

import za.ac.tut.kotashop.entity.Order;

import java.util.List;

public interface OrderService {
    void saveOrder(Order order);
    void deleteOrder(Long orderId);
    void editOrder(Order order);
    Order findById(Long id);
    List<Order> findAllOrders();
}
