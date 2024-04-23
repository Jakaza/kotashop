package za.ac.tut.kotashop.controller;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;
import za.ac.tut.kotashop.dto.CategoryDto;
import za.ac.tut.kotashop.dto.ProductDto;
import za.ac.tut.kotashop.dto.UserDto;
import za.ac.tut.kotashop.entity.User;
import za.ac.tut.kotashop.service.CategoryService;
import za.ac.tut.kotashop.service.ProductService;
import za.ac.tut.kotashop.service.UserService;

import java.util.List;

@Controller
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    private UserService userService;
    private CategoryService categoryService;
    private ProductService productService;

    public AuthController(UserService userService , CategoryService categoryService , ProductService productService) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @GetMapping("/")
    public String home(){
        return "index";
    }

    @GetMapping("/dashboard")
    public String adminHome(){
        return "dashboard";
    }

    @GetMapping("/admin/customers")
    public String adminCustomers(){
        return "viewCustomers";
    }

    @GetMapping("/admin/products")
    public String adminProducts(Model model){

        List<ProductDto> allProducts = productService.findAllProducts();

        System.out.println(allProducts);

        List<CategoryDto> allCategories = categoryService.findAllCategories();
        model.addAttribute("categories", allCategories);
        return "viewAllProducts";
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
        productService.saveProduct(productDto, file);

        return new RedirectView("/admin/products?success", true);
    }



    @GetMapping("/admin/categories")
    public String adminCategories(Model model){
        List<CategoryDto> allCategories = categoryService.findAllCategories();
        model.addAttribute("categories", allCategories);
        return "viewCategories";
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

    @GetMapping("/admin/orders")
    public String adminOrders(){
        return "viewAllOrders";
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


}