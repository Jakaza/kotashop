package za.ac.tut.kotashop.controller;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import za.ac.tut.kotashop.dto.UserDto;
import za.ac.tut.kotashop.entity.User;
import za.ac.tut.kotashop.service.UserService;

@Controller
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
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
    public String adminProducts(){
        return "viewAllProducts";
    }

    @GetMapping("/admin/categories")
    public String adminCategories(){
        return "viewCategories";
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