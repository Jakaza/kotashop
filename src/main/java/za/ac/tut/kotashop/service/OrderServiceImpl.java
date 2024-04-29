package za.ac.tut.kotashop.service;

import org.springframework.stereotype.Service;
import za.ac.tut.kotashop.entity.Order;
import za.ac.tut.kotashop.entity.User;
import za.ac.tut.kotashop.repository.OrderRepository;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    @Override
    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Long orderId) {

    }

    @Override
    public void editOrder(Order order) {

    }

    @Override
    public Order findById(Long id) {
        return null;
    }

    @Override
    public List<Order> findAllOrders() {
        return List.of();
    }

    @Override
    public List<Order> findOrdersByUser(User user) {
        return orderRepository.findByUser(user);
    }
}
