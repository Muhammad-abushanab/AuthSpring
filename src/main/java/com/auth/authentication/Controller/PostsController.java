package com.auth.authentication.Controller;

import com.auth.authentication.Model.Posts;
import com.auth.authentication.Model.SiteUser;
import com.auth.authentication.Repository.PostsRepo;
import com.auth.authentication.Repository.SiteUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class PostsController {
    @Autowired
    PostsRepo postsRepo;
    @Autowired
    SiteUserRepo siteUserRepo;

    @GetMapping("/posts")
    public String getPosts(HttpServletRequest request, Model model) {
        if (request.getSession().getAttribute("username") != null) {
            List<Posts> posts = postsRepo.findAll();
            model.addAttribute("posts", posts);
            model.addAttribute("name", request.getSession().getAttribute("username"));
            return "posts";
        }
        return "redirect:/login";
    }

    @PostMapping("/posts/create")
    public String addPost(HttpServletRequest request, Model model, @RequestParam("textContent") String content) {
        if (request.getSession().getAttribute("username") != null) {
            SiteUser user = siteUserRepo.findByuserName(request.getSession().getAttribute("username").toString());
            Posts post = new Posts(content, user);
            postsRepo.save(post);
            return "redirect:/posts";
        }
        return "redirect:/login";
    }
}
