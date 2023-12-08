package org.example.controller;

import jakarta.servlet.http.HttpSession;
import org.example.beans.DishInfo;
import org.example.beans.UserInfo;
import org.example.exception.ServiceException;
import org.example.model.Dish;
import org.example.service.DishService;
import org.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class DishController {
    private DishService dishService;
    private OrderService orderService;

    @Autowired
    public void setOrderService(OrderService orderService){
        this.orderService = orderService;
    }

    @Autowired
    public void setDishService(DishService dishService){
        this.dishService = dishService;
    }

    @GetMapping("/basket")
    public ModelAndView showBasket(RedirectAttributes redirectAttributes){
        ModelAndView modelAndView = new ModelAndView();
        try {
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession();
            UserInfo userinfo = (UserInfo) session.getAttribute("user");
            List<DishInfo> dishesInfo = dishService.getUserDishesInBasket(userinfo.getId());
            modelAndView.setViewName("basket");
            modelAndView.addObject("dishesInfo", dishesInfo);
            modelAndView.addObject("dishesCount", dishesInfo.size());
        }catch (ServiceException e){
            modelAndView.setViewName("redirect:/error");
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return modelAndView;
    }

    @PostMapping("/basket/{dish_id}/increment")
    public ModelAndView increment(@PathVariable("dish_id") Integer dish_id, RedirectAttributes redirectAttributes){
        ModelAndView modelAndView = new ModelAndView();
        try {
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession();
            UserInfo userinfo = (UserInfo) session.getAttribute("user");
            dishService.incrementDishAmount(userinfo.getId(), dish_id);
            modelAndView.setViewName("redirect:/basket");
        }catch (ServiceException e){
            modelAndView.setViewName("redirect:/error");
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return modelAndView;
    }

    @PostMapping("/basket/{dish_id}/decrement")
    public ModelAndView decrement(@PathVariable("dish_id") Integer dish_id, RedirectAttributes redirectAttributes){
        ModelAndView modelAndView = new ModelAndView();
        try {
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession();
            UserInfo userinfo = (UserInfo) session.getAttribute("user");
            dishService.decrementDishAmount(userinfo.getId(), dish_id);
            modelAndView.setViewName("redirect:/basket");
        }catch (ServiceException e){
            modelAndView.setViewName("redirect:/error");
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return modelAndView;
    }

    @GetMapping("/single-category/{category_id}")
    public ModelAndView showCategory(@PathVariable("category_id") Integer category_id, RedirectAttributes redirectAttributes){
        ModelAndView modelAndView = new ModelAndView();
        try{
            List<Dish> dishes =  dishService.getDishesByCategoryId(category_id);
            modelAndView.setViewName("redirect:/single-category");
            redirectAttributes.addFlashAttribute("dishes", dishes);
            redirectAttributes.addFlashAttribute("category_id", category_id);
        }catch (ServiceException e){
            modelAndView.setViewName("redirect:/error");
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return modelAndView;
    }

    @GetMapping("/single-category")
    public ModelAndView singleCategoryPage(@ModelAttribute("dishes") List<Dish> dishes, @ModelAttribute("category_id") Integer id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("dishes", dishes);
        modelAndView.addObject("category_id", id);
        return modelAndView;
    }

    @PostMapping("/single-category/{dish_id}/{category_id}/add-to-basket")
    public ModelAndView singleCategoryPage(@PathVariable("dish_id") Integer dishId, @PathVariable("category_id") Integer category_id,
                                           RedirectAttributes redirectAttributes){
        ModelAndView modelAndView = new ModelAndView();
        try {
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession();
            UserInfo userinfo = (UserInfo) session.getAttribute("user");
            int userId = userinfo.getId();
            dishService.addDishToBasket(userId, dishId);
            modelAndView.setViewName("redirect:/single-category/"+category_id);
        }catch (ServiceException e){
            modelAndView.setViewName("redirect:/error");
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return modelAndView;
    }

    @PostMapping("/basket/order/{userId}")
    public ModelAndView makeOrder(@PathVariable("userId") Integer userId, RedirectAttributes redirectAttributes){
        ModelAndView modelAndView = new ModelAndView();
        try {
            orderService.makeOrder(userId);
            modelAndView.setViewName("redirect:/order");
        }catch (ServiceException e){
            modelAndView.setViewName("redirect:/error");
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return modelAndView;
    }
}
