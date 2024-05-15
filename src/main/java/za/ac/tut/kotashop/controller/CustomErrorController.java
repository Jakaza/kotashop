package za.ac.tut.kotashop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomErrorController {
    @GetMapping("/pageNotFound")
    public String handlePageNotFound() {
        return "pageNotFound";
    }
}
