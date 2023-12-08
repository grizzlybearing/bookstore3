package org.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorPageController {
    @GetMapping("/error")
    public ModelAndView errorPage(@ModelAttribute("error") String error){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("error", error);
        return modelAndView;
    }
}
