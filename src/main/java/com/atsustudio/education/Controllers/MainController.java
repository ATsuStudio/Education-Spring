package com.atsustudio.education.Controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    @GetMapping("/")
    public String homepage( Model model) {
        model.addAttribute("text", "Home page text");
        return "homepage";
    }
    @GetMapping("/about")
    public String about (Model model) {
        return "about";
    }
}
