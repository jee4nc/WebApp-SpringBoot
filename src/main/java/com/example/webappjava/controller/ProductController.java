package com.example.webappjava.controller;

import com.example.webappjava.entity.Product;
import com.example.webappjava.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("list")
    public ModelAndView list() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/product/ProductList");
        List<Product> products = productService.list();
        mv.addObject("products", products);
        return mv;
    }

    @GetMapping("new")
    public String newProduct() {
        return "product/ProductNew";
    }
}
