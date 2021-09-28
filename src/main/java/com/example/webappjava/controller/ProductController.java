package com.example.webappjava.controller;

import com.example.webappjava.entity.Product;
import com.example.webappjava.service.ProductService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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


    @PostMapping("/creator")
    public ModelAndView creator(@RequestParam String name, @RequestParam float price){
        ModelAndView mv = new ModelAndView();
        if(StringUtils.isBlank(name)){
            mv.setViewName("product/ProductNew");
            mv.addObject("error", "el nombre no puede estar vacío");
            return mv;
        }
        if(price <1 ){
            mv.setViewName("product/ProductNew");
            mv.addObject("error", "el precio debe ser mayor que cero");
            return mv;
        }
        if(productService.existsByName(name)){
            mv.setViewName("product/ProductNew");
            mv.addObject("error", "ese nombre ya existe");
            return mv;
        }
        Product product = new Product(name, price);
        productService.save(product);

//        Here put URL to want redirect
        mv.setViewName("redirect:/product/list");
        return mv;
    }
}
