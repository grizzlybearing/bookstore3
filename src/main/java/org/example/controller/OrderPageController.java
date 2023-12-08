package org.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderPageController {
    @GetMapping("/order")
    public String showOrderPage(){
        return "order";
    }
}
