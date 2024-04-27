package za.ac.tut.kotashop.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import za.ac.tut.kotashop.entity.Product;
import za.ac.tut.kotashop.global.GlobalDara;
import za.ac.tut.kotashop.service.ProductService;

@Controller
public class CartController {


    @Autowired
    ProductService productService;

    @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable Long id){
        GlobalDara.cart.add(productService.findById(id));
        return "redirect:/";
    }

    @GetMapping("/cart")
    public String getCart(Model model){

        model.addAttribute("cartCount", GlobalDara.cart.size());
        model.addAttribute("total", GlobalDara.cart.stream().mapToDouble(Product::getPrice).sum());
        model.addAttribute("cart", GlobalDara.cart);

        return "cart";
    }

}
