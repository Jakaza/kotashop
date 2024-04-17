package za.ac.tut.kotashop.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Page {


//    @GetMapping("/")
//    private String homePage(){
//        return "index";
//    }
//
//    @GetMapping("/login")
//    private String loginPage(){
//        return "login";
//    }


    @GetMapping("/*")
    private String pageNotFound(){
        return "404";
    }
}
