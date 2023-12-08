package org.example.controller;

import jakarta.servlet.http.HttpSession;
import org.example.beans.AdminUserInfo;
import org.example.beans.UserInfo;
import org.example.beans.UserRoles;
import org.example.exception.ServiceException;
import org.example.model.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ModelAndView signIn(@RequestParam(name = "username") String login, @RequestParam(name = "password") String password,
                               RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            User user = userService.signIn(login, password);
            if (user != null) {
                if (user.getRole().equals(UserRoles.ADMIN)) {
                    modelAndView.setViewName("redirect:/admin");
                } else {
                    modelAndView.setViewName("redirect:/");
                    UserInfo userInfo = new UserInfo();
                    userInfo.setId(user.getId());
                    userInfo.setName(user.getLogin());
                    userInfo.setIsAuth(true);

                    ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
                    HttpSession session = attr.getRequest().getSession();
                    session.setAttribute("user", userInfo);
                }
            } else {
                modelAndView.setViewName("redirect:/error");
                redirectAttributes.addFlashAttribute("error", "Incorrect login or password. User does'nt exist");
            }
        } catch (ServiceException e) {
            modelAndView.setViewName("redirect:/error");
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            System.out.println(e.getMessage());
        }
        return modelAndView;
    }

    @PostMapping("/logout")
    public ModelAndView exit(RedirectAttributes redirectAttributes){
        ModelAndView modelAndView = new ModelAndView();
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        session.removeAttribute("user");
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }

    @PostMapping("/registration")
    public ModelAndView signUp(@RequestParam(name = "username") String login, @RequestParam(name = "password") String password,
                               @RequestParam(name = "email") String email, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            User user = new User();
            user.setPassword(password);
            user.setLogin(login);
            user.setEmail(email);
            user.setRole(UserRoles.USER);
            UserInfo userInfo = userService.registration(user);
            if (userInfo != null) {
                modelAndView.setViewName("redirect:/");
                ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
                HttpSession session = attr.getRequest().getSession();
                session.setAttribute("user", userInfo);
            } else {
                modelAndView.setViewName("redirect:/error");
                redirectAttributes.addFlashAttribute("error", "Server error, sorry:(");
            }
        } catch (ServiceException e) {
            modelAndView.setViewName("redirect:/error");
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            System.out.println(e.getMessage());
        }
        return modelAndView;
    }

    @GetMapping("/admin")
    public ModelAndView showAdminPage(RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            List<AdminUserInfo> users = userService.getAllUsersInfo();
            modelAndView.setViewName("admin");
            modelAndView.addObject("users", users);
        } catch (ServiceException e) {
            modelAndView.setViewName("redirect:/error");
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return modelAndView;
    }
}
