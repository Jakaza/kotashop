package za.ac.tut.kotashop.controller;


import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import za.ac.tut.kotashop.dto.OrderDto;
import za.ac.tut.kotashop.dto.ProductDto;
import za.ac.tut.kotashop.entity.Order;
import za.ac.tut.kotashop.entity.Product;
import za.ac.tut.kotashop.entity.User;
import za.ac.tut.kotashop.global.GlobalData;
import za.ac.tut.kotashop.service.CategoryService;
import za.ac.tut.kotashop.service.OrderService;
import za.ac.tut.kotashop.service.ProductService;
import za.ac.tut.kotashop.service.UserService;
import za.ac.tut.kotashop.utils.SessionManager;

import java.util.List;

@Controller
public class UserController {

    private UserService userService;
    private CategoryService categoryService;
    private ProductService productService;
    private SessionManager sessionManager;
    private OrderService orderService;

    public UserController(UserService userService , CategoryService categoryService , ProductService productService, SessionManager sessionManager ,OrderService orderService) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.productService = productService;
        this.sessionManager = sessionManager;
        this.orderService = orderService;
    }

    @GetMapping("/kotashop")
    public String home(Model model , HttpSession session){
        User user = sessionManager.getUserFromSession(session.getId());
        if (user != null && user.getRole().equals("CUSTOMER")) {
            model.addAttribute("cartCount", GlobalData.cart.size());
            List<ProductDto> allProducts = productService.findAllProducts();
            List<OrderDto> customerOrders = orderService.findOrdersByUserId(user.getId());
            int ordersTotal = customerOrders.size();
            model.addAttribute("ordersTotal", ordersTotal);
            model.addAttribute("products", allProducts);
            model.addAttribute("user_id", user.getId());
            return "index";
        }else{
            return "redirect:/";
        }
    }

    @GetMapping("/cutomer/orders")
    public String viewCustomerOrders(@RequestParam("id") Long userId, Model model , HttpSession session) {
        User user = sessionManager.getUserFromSession(session.getId());
        if (user != null && user.getRole().equals("CUSTOMER")) {
            List<OrderDto> customerOrders = orderService.findOrdersByUserId(userId);
            int ordersTotal = customerOrders.size();
            model.addAttribute("ordersTotal", ordersTotal);
            model.addAttribute("allOrders", customerOrders);
            return "customerOrders"; // Assuming "customerOrders.html" is your view template
        }else{
            return "redirect:/";
        }
    }

    @GetMapping("/orders")
    public String viewOrders(@RequestParam("id") Long userId, Model model , HttpSession session) {
        User user = sessionManager.getUserFromSession(session.getId());
        if (user != null && user.getRole().equals("CUSTOMER")) {
            List<OrderDto> customerOrders = orderService.findOrdersByUserId(userId);
            int ordersTotal = customerOrders.size();
            model.addAttribute("allOrders", customerOrders);
            model.addAttribute("ordersTotal", ordersTotal);
            return "customerOrders";
        }else{
            return "redirect:/";
        }
    }

    @GetMapping("/cart")
    public String getCart(Model model , HttpSession session){
        User user = sessionManager.getUserFromSession(session.getId());
        if (user != null && user.getRole().equals("CUSTOMER")) {
            model.addAttribute("cartCount", GlobalData.cart.size());
            List<Order> userOrders = orderService.findOrdersByUser(user);
            model.addAttribute("user_id", user.getId());
            List<ProductDto> allProducts = productService.findAllProducts();
            model.addAttribute("cartCount", GlobalData.cart.size());
            model.addAttribute("total", GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
            model.addAttribute("cart", GlobalData.cart);
            return "cart";
        }else{
            return "redirect:/";
        }
    }

}
