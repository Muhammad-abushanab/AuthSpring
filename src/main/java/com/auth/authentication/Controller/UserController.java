package com.auth.authentication.Controller;

import com.auth.authentication.Model.SiteUser;
import com.auth.authentication.Repository.SiteUserRepo;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    SiteUserRepo siteRepo;
    @GetMapping("/login")
    public String loginPage(HttpServletRequest request){
        if(alreadyLoggedIn(request)){
            return ("redirect:/posts");
        }
        return "login";
    }
    @PostMapping("/login")
    public RedirectView login(Model m, HttpServletRequest request, @RequestParam("userName") String userName , @RequestParam("password") String password){
        SiteUser user = siteRepo.findByuserName(userName);
            if (user == null) {
                m.addAttribute("error" , "No User Found");
                return new RedirectView("/login");
            }
            if (BCrypt.checkpw(password,user.getPassword()) && user.getUserName().equals(userName))
            {
                HttpSession session = request.getSession();
                session.setAttribute("username",user.getUserName());
                return new RedirectView("/posts");
            }
            m.addAttribute("error","Wrong password");
            return new RedirectView("/login");
    }

    private boolean alreadyLoggedIn(HttpServletRequest req) {
//        System.out.println(req.getSession().getAttribute("username") != null);
        return req.getSession().getAttribute("username") != null ;
    }

    @GetMapping("/signup")
    public String signUpPage(HttpServletRequest request){
        if(alreadyLoggedIn(request)){
            return ("redirect:/posts");
        }
        return "signup";
    }
    @PostMapping("/signup")
    public ModelAndView signUp( @RequestParam("userName") String userName, @RequestParam("password") String password) {
        ModelAndView modelAndView = new ModelAndView();
        SiteUser existingUser = siteRepo.findByuserName(userName);
        if (existingUser != null) {
            modelAndView.setViewName("signup");
            modelAndView.addObject("error", "Username already exists. Please choose another one.");
        } else {
            String hashPassword = BCrypt.hashpw(password,BCrypt.gensalt());
            SiteUser user = new SiteUser(userName, hashPassword);
            siteRepo.save(user);
            modelAndView.setViewName("redirect:/login");
        }

        return modelAndView;
    }
    @GetMapping("/logout")
    public RedirectView logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        session.invalidate();
        return new RedirectView("/login");
    }
}
