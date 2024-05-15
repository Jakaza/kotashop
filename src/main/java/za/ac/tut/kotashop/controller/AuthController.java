package za.ac.tut.kotashop.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;
import za.ac.tut.kotashop.dto.*;
import za.ac.tut.kotashop.entity.Category;
import za.ac.tut.kotashop.entity.Product;
import za.ac.tut.kotashop.entity.User;
import za.ac.tut.kotashop.global.GlobalData;
import za.ac.tut.kotashop.service.CategoryService;
import za.ac.tut.kotashop.service.OrderService;
import za.ac.tut.kotashop.service.ProductService;
import za.ac.tut.kotashop.service.UserService;
import za.ac.tut.kotashop.utils.ApplicationProperties;
import za.ac.tut.kotashop.utils.SessionManager;

import java.io.IOException;
import java.util.List;

@Controller
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    private UserService userService;
    private CategoryService categoryService;
    private ProductService productService;
    private SessionManager sessionManager;
    private OrderService orderService;

    public AuthController(UserService userService , CategoryService categoryService , ProductService productService, SessionManager sessionManager ,OrderService orderService) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.productService = productService;
        this.sessionManager = sessionManager;
        this.orderService = orderService;
    }

    @GetMapping("/")
    public String home(Model model , HttpSession session){
        User user = sessionManager.getUserFromSession(session.getId());
        if(user != null){
            if(user.getRole().equals("ADMIN")){
                return "redirect:/dashboard";
            }else{
                return "redirect:/kotashop";
            }
        }else{
            return "redirect:/login";
        }
    }


    @PostMapping("/admin/products/create")
    public RedirectView adminCreateProduct(@Valid @ModelAttribute("product") ProductDto productDto,
                                     BindingResult result,
                                     Model model , @RequestParam("file") MultipartFile file){

        if (result.hasErrors()) {
            // Handle validation errors
            // You can add error messages to the model if needed
            System.out.println(result.toString());
            return new RedirectView("/admin/products?error", true);
        }

        if (file.isEmpty()) {
            // Handle file not uploaded error
            // You can add error messages to the model if needed
            return new RedirectView("/admin/products?error", true);
        }


        // Save the product and its image
        try {
            productService.saveProduct(productDto, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new RedirectView("/admin/products?success", true);
    }





    @PostMapping("/admin/category/create")
    public String adminCreateCategory(@Valid @ModelAttribute("category") CategoryDto categoryDto,
                                     BindingResult result,
                                     Model model){
        categoryService.saveCategory(categoryDto);
        return "redirect:/admin/categories?success";
    }

    @PostMapping("/admin/category/delete/{categoryId}")
    @ResponseBody
    public RedirectView deleteCategory(@PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return new RedirectView("/admin/categories?success", true);
    }


    @GetMapping("/admin/order")
    public String adminOrder(){
        return "viewEachOrder";
    }


    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model){
        UserLogin userLogin = new UserLogin();
        model.addAttribute("userLogin", userLogin);
        return "login";
    }

    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto,
                               BindingResult result,
                               Model model){

        User existingUser = userService.findUserByEmail(userDto.getEmail());

        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }

        System.out.println(userDto.toString());

        if(result.hasErrors()){
            model.addAttribute("user", userDto);
            return "/register";
        }

        userService.saveUser(userDto);
        return "redirect:/register?success";
    }


    @PostMapping("/auth/login")
    public String loginUser(@Valid @ModelAttribute("userLogin") UserLogin userDto,
                                  BindingResult result,
                                  Model model , HttpSession session){
        User user = userService.loginUser(userDto.getEmail(), userDto.getPassword());
        if (userDto.getEmail().equals("admin@gmail.com") && userDto.getPassword().equals("TUT2024")) {
            User amdinUser = new User();
            amdinUser.setEmail("admin@gamil.com");
            amdinUser.setPassword("TUT2024");
            amdinUser.setRole("ADMIN");
            sessionManager.createSession(session.getId(), amdinUser);
            return "redirect:/dashboard";
        }

        if(result.hasErrors()){
            model.addAttribute("userLogin", userDto);
            return "/login";
        }

        if (user != null) {
            sessionManager.createSession(session.getId(), user);
            return "redirect:/";
        } else {
            model.addAttribute("error", "Invalid email or password, Please enter correct credential.");
            return "/login"; // Return to the login page
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        String sessionId = session.getId();
        sessionManager.removeSession(sessionId);
        return "redirect:/login";
    }

}