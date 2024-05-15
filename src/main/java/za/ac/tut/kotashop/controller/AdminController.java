package za.ac.tut.kotashop.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import za.ac.tut.kotashop.dto.CategoryDto;
import za.ac.tut.kotashop.dto.OrderDto;
import za.ac.tut.kotashop.dto.ProductDto;
import za.ac.tut.kotashop.dto.UserDto;
import za.ac.tut.kotashop.entity.Product;
import za.ac.tut.kotashop.entity.User;
import za.ac.tut.kotashop.service.CategoryService;
import za.ac.tut.kotashop.service.OrderService;
import za.ac.tut.kotashop.service.ProductService;
import za.ac.tut.kotashop.service.UserService;
import za.ac.tut.kotashop.utils.SessionManager;

import java.util.List;

@Controller
public class AdminController {

    private UserService userService;
    private CategoryService categoryService;
    private ProductService productService;
    private SessionManager sessionManager;
    private OrderService orderService;

    public AdminController(UserService userService , CategoryService categoryService , ProductService productService, SessionManager sessionManager ,OrderService orderService) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.productService = productService;
        this.sessionManager = sessionManager;
        this.orderService = orderService;
    }

    @GetMapping("/dashboard")
    public String adminHome(Model model, HttpSession httpSession){
        User user = sessionManager.getUserFromSession(httpSession.getId());
        if (user != null && user.getRole().equals("ADMIN")) {

            System.out.println("USER IS AN ADMIN");

            List<Product> products = productService.getAllProductsWithoutImage();
            List<CategoryDto> categories = categoryService.findAllCategories();
            List<UserDto> users = userService.findAllUsers();
            List<OrderDto> orders = orderService.findAllOrders();
            model.addAttribute("totalProducts", products.size());
            model.addAttribute("totalCategories", categories.size());
            model.addAttribute("totalUsers", users.size());
            model.addAttribute("totalOrders", orders.size());
            return "dashboard";
        }else{
            return "redirect:/";
        }
    }

    @GetMapping("/admin/customers")
    public String adminCustomers(Model model , HttpSession httpSession){
        User user = sessionManager.getUserFromSession(httpSession.getId());
        if (user != null && user.getRole().equals("ADMIN")) {

            List<UserDto> customers = userService.findAllUsers();
            model.addAttribute("customers", customers);
            return "viewCustomers";
        }else{
            return "redirect:/";
        }
    }

    @GetMapping("/admin/categories")
    public String adminCategories(Model model , HttpSession httpSession){
        User user = sessionManager.getUserFromSession(httpSession.getId());
        if (user != null && user.getRole().equals("ADMIN")) {

            List<CategoryDto> allCategories = categoryService.findAllCategories();
            model.addAttribute("categories", allCategories);
            return "viewCategories";
        }else{
            return "redirect:/";
        }
    }

    @GetMapping("/admin/orders")
    public String adminOrders( Model model, HttpSession httpSession){
        User user = sessionManager.getUserFromSession(httpSession.getId());
        if (user != null && user.getRole().equals("ADMIN")) {

            List<OrderDto> allOrders = orderService.findAllOrders();
            model.addAttribute("allOrders", allOrders);

            return "viewAllOrders";
        }else{
            return "redirect:/";
        }
    }

    @GetMapping("/admin/products")
    public String adminProducts(Model model , HttpSession httpSession){
        User user = sessionManager.getUserFromSession(httpSession.getId());
        if (user != null && user.getRole().equals("ADMIN")) {
            List<ProductDto> allProducts = productService.findAllProducts();
            List<CategoryDto> allCategories = categoryService.findAllCategories();
            model.addAttribute("categories", allCategories);
            model.addAttribute("products", allProducts);
            return "viewAllProducts";
        }else{
            return "redirect:/";
        }
    }
}
