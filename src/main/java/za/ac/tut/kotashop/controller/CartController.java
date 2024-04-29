package za.ac.tut.kotashop.controller;


import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import za.ac.tut.kotashop.dto.ProductDto;
import za.ac.tut.kotashop.entity.Order;
import za.ac.tut.kotashop.entity.OrderProduct;
import za.ac.tut.kotashop.entity.Product;
import za.ac.tut.kotashop.entity.User;
import za.ac.tut.kotashop.global.GlobalData;
import za.ac.tut.kotashop.service.OrderService;
import za.ac.tut.kotashop.service.ProductService;
import za.ac.tut.kotashop.utils.SessionManager;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
public class CartController {


    @Autowired
    ProductService productService;
    private final SessionManager sessionManager;
    private final OrderService orderService;

    public CartController(SessionManager sessionManager, OrderService orderService) {
        this.sessionManager = sessionManager;
        this.orderService = orderService;
    }

    @PostMapping("/cart/add")
    public String addToCart(@RequestParam("id") Long id){
        GlobalData.cart.add(productService.findById(id));
        return "redirect:/";
    }


    @PostMapping("/cart/remove")
    public RedirectView removeFromCart(@RequestParam("id") Long id) {
        Iterator<Product> iterator = GlobalData.cart.iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product.getProductId().equals(id)) {
                iterator.remove();
                break;
            }
        }
        return new RedirectView("/cart");
    }


    @PostMapping("/order/add")
    public String placeOrder(HttpSession session) {
        User loggedInUser = sessionManager.getUserFromSession(session.getId());

        Order order = new Order();
        order.setUser(loggedInUser);
        order.setOrderDate(LocalDateTime.now());

        List<OrderProduct> orderProducts = new ArrayList<>();

        for (Product product : GlobalData.cart) {
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setProduct(product);
            orderProduct.setOrder(order);
            orderProducts.add(orderProduct);
        }
        order.setProducts(orderProducts);
        orderService.saveOrder(order);
        GlobalData.cart.clear();
        return "orderFeedBack";
    }





    @GetMapping("/cart")
    public String getCart(Model model , HttpSession session){

        User user = sessionManager.getUserFromSession(session.getId());

        model.addAttribute("cartCount", GlobalData.cart.size());

        if (user != null) {

            List<Order> userOrders = orderService.findOrdersByUser(user);

            System.out.println("User Orders");

            for (Order order : userOrders) {
                System.out.println("Order ID: " + order.getOrderId());
                System.out.println("Order Date: " + order.getOrderDate());
                System.out.println("User: " + order.getUser().getFullname()); // Assuming getUsername() retrieves the username of the user associated with the order

                System.out.println("Products:");

                for (OrderProduct orderProduct : order.getProducts()) {
                    System.out.println("  Product Name: " + orderProduct.getProduct().getProductName());
                    System.out.println("  Price per unit: " + orderProduct.getProduct().getPrice());
                }
                System.out.println("-----------------------");
            }


            List<ProductDto> allProducts = productService.findAllProducts();
            model.addAttribute("cartCount", GlobalData.cart.size());
            model.addAttribute("total", GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
            model.addAttribute("cart", GlobalData.cart);

            return "cart";
        }else{
            return "redirect:/login";
        }
    }

}
