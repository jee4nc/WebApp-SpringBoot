package com.example.webappjava.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping(value = {"/", "/index"})
    public String index() {
        return "index";
    }


    @GetMapping(value = { "/login"})
    public String login() {
        return "login";
    }

    @GetMapping("/forbidden")
    public String forbidden() {
        return "forbidden";
    }

}
