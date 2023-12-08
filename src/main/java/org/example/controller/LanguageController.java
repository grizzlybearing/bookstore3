package org.example.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LanguageController {
    @PostMapping("/change-lang/**")
    public ModelAndView changeLang(@RequestParam("lang") String lang){
        ModelAndView modelAndView = new ModelAndView();
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        session.setAttribute("language", lang);
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }
}
