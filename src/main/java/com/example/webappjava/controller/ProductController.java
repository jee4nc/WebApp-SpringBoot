package com.example.webappjava.controller;

import com.example.webappjava.entity.Product;
import com.example.webappjava.service.ProductService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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


    // Create new Product
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

    @GetMapping("/details/{id}")
    public ModelAndView details(@PathVariable("id") int id ) {
        if (!productService.existsById(id))
            return new ModelAndView("redirect:/product/list");
        Product product = productService.getOne(id).get();
        ModelAndView mv = new ModelAndView("/product/ProductDetails");
        mv.addObject("product", product);
        return mv;
    }

    @PostMapping("/update")
    public ModelAndView update(@RequestParam int id,
                               @RequestParam String name,
                               @RequestParam float price) {
        // Check if exists
        if (!productService.existsById(id))
            return new ModelAndView("redirect:/product/list");

        ModelAndView mv = new ModelAndView();
        Product product = productService.getOne(id).get();

        if(StringUtils.isBlank(name)){
            mv.setViewName("product/ProductNew");
            mv.addObject("product", product);
            mv.addObject("error", "el nombre no puede estar vacío");
            return mv;
        }
        if(price <1 ){
            mv.setViewName("product/ProductSet");
            mv.addObject("product", product);
            mv.addObject("error", "el precio debe ser mayor que cero");
            return mv;
        }
        if(productService.existsByName(name) && productService.getByName(name).get().getId() != id){
            mv.setViewName("product/ProductSet");
            mv.addObject("product", product);
            mv.addObject("error", "ese nombre ya existe");
            return mv;
        }
        product.setName(name);
        product.setPrice(price);
        productService.save(product);
        return new ModelAndView("redirect:/product/list");
    }

    // When clicked icon edit
    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") int id) {
        // Check if exists
        if (!productService.existsById(id))
            return new ModelAndView("redirect:/product/list");

        Product product = productService.getOne(id).get();
        ModelAndView mv = new ModelAndView("/product/ProductSet");
        mv.addObject("product", product);
        return mv;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") int id){
        // Check if exists
        if (productService.existsById(id)){
            productService.delete(id);
            return new ModelAndView("redirect:/product/list");
        }
    return null;
    }
}
