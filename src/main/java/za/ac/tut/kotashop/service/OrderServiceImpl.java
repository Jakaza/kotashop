package za.ac.tut.kotashop.service;

import org.springframework.stereotype.Service;
import za.ac.tut.kotashop.dto.OrderDto;
import za.ac.tut.kotashop.dto.OrderProductDto;
import za.ac.tut.kotashop.entity.Order;
import za.ac.tut.kotashop.entity.OrderProduct;
import za.ac.tut.kotashop.entity.User;
import za.ac.tut.kotashop.repository.OrderRepository;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<OrderDto> findAllOrders() {
        return orderRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private OrderDto convertToDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderId(order.getOrderId());
        orderDto.setUserId(order.getUser().getId()); // Assuming you want to include user ID
        orderDto.setUserName(order.getUser().getFullname() +" "+ order.getUser().getSurname());
        orderDto.setAddress(order.getUser().getCountry() + " , " + order.getUser().getTownship() +" , "+ order.getUser().getTownship());
        orderDto.setProducts(convertOrderProductsToDto(order.getProducts())); // Convert order products
        orderDto.setQuantity(order.getQuantity());
        orderDto.setOrderDate(order.getOrderDate());

        String orderDateString = order.getOrderDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        orderDto.setOrderDated(orderDateString);
        return orderDto;
    }

    private List<OrderProductDto> convertOrderProductsToDto(List<OrderProduct> orderProducts) {
        return orderProducts.stream()
                .map(this::convertOrderProductToDto)
                .collect(Collectors.toList());
    }

    private OrderProductDto convertOrderProductToDto(OrderProduct orderProduct) {
        OrderProductDto orderProductDto = new OrderProductDto();
        orderProductDto.setId(orderProduct.getId());
        orderProductDto.setOrderId(orderProduct.getOrder().getOrderId());
        orderProductDto.setProductId(orderProduct.getProduct().getProductId());
        orderProductDto.setProductName(orderProduct.getProduct().getProductName());
        orderProductDto.setProductDescription(orderProduct.getProduct().getProductDescription());
        return orderProductDto;
    }

    @Override
    public List<Order> findOrdersByUser(User user) {
        return orderRepository.findByUser(user);
    }

    @Override
    public List<OrderDto> findOrdersByUserId(Long userId) {
        List<Order> orders = orderRepository.findByUserId(userId);
        return orders.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}
