package com.auth.authentication.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
    @GetMapping("/")
    public String homePage(HttpServletRequest req ){
        HttpSession session = req.getSession();
        if(session.getAttribute("username") != null) {
            ModelAndView m = new ModelAndView();
            m.addObject("logout" , "logout");
        }
        return "index";
    }
}
