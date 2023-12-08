package org.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthorizationPageController {
    @GetMapping("/login")
    public String showSignInPage(){
        return "login";
    }

    @GetMapping("/registration")
    public String showSignUpPage(){
        return "registration";
    }
}
