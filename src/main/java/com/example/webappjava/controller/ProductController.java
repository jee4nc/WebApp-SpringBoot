package com.example.webappjava.controller;

import com.example.webappjava.entity.Product;
import com.example.webappjava.entity.Role;
import com.example.webappjava.entity.UnitMeasurement;
import com.example.webappjava.enums.RoleName;
import com.example.webappjava.service.ProductService;
import com.example.webappjava.service.UnitMeasureService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    UnitMeasureService unitMeasureService;

    @GetMapping("list")
    public ModelAndView list() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/product/ProductList");
        List<Product> products = productService.list();
        mv.addObject("products", products);
        return mv;
    }

    @GetMapping("list2")
    public ModelAndView list2() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/product/ProductList2");
        List<Product> products = productService.list();
        mv.addObject("products", products);
        return mv;
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("new")
    public String newProduct(Model model) {
        List<UnitMeasurement> listUnits = unitMeasureService.list();
        model.addAttribute("units", listUnits);
        return "product/ProductNew";
    }


    // Create new Product
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/creator")
    public ModelAndView creator(@RequestParam String name, @RequestParam double price,
                                @RequestParam double quantity, @RequestParam int quality,
                                @RequestParam UnitMeasurement unitMeasurement) {
        ModelAndView mv = new ModelAndView();
        if (StringUtils.isBlank(name)) {
            mv.setViewName("product/ProductNew");
            mv.addObject("error", "el nombre no puede estar vacío");
            return mv;
        }
        if (price < 1) {
            mv.setViewName("product/ProductNew");
            mv.addObject("error", "el precio debe ser mayor que cero");
            return mv;
        }
        if (productService.existsByName(name)) {
            mv.setViewName("product/ProductNew");
            mv.addObject("error", "ese nombre ya existe");
            return mv;
        }
        if (quantity < 1) {
            mv.setViewName("product/ProductNew");
            mv.addObject("error", "La cantidad es requerida");
            return mv;
        }
        if (quality < 1) {
            mv.setViewName("product/ProductNew");
            mv.addObject("error", "La calidad del producto es requerida");
            return mv;
        }
        Product product = new Product(name, price, quantity, quality, unitMeasurement);
        productService.save(product);

//        Here put URL to want redirect
        mv.setViewName("redirect:/product/list");
        return mv;
    }

    @GetMapping("/details/{id}")
    public ModelAndView details(@PathVariable("id") int id) {
        if (!productService.existsById(id))
            return new ModelAndView("redirect:/product/list");
        Product product = productService.getOne(id).get();
        ModelAndView mv = new ModelAndView("/product/ProductDetails");
        mv.addObject("product", product);
        return mv;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/update")
    public ModelAndView update(@RequestParam int id,
                               @RequestParam String name,
                               @RequestParam double price,
                               @RequestParam double quantity,
                               @RequestParam int quality) {
        // Check if exists
        if (!productService.existsById(id))
            return new ModelAndView("redirect:/product/list");

        ModelAndView mv = new ModelAndView();
        Product product = productService.getOne(id).get();

        if (StringUtils.isBlank(name)) {
            mv.setViewName("product/ProductNew");
            mv.addObject("product", product);
            mv.addObject("error", "el nombre no puede estar vacío");
            return mv;
        }
        if (price < 1) {
            mv.setViewName("product/ProductSet");
            mv.addObject("product", product);
            mv.addObject("error", "el precio debe ser mayor que cero");
            return mv;
        }
        if (quantity < 1) {
            mv.setViewName("product/ProductSet");
            mv.addObject("product", product);
            mv.addObject("error", "La cantidad debe ser minimo 1 ");
            return mv;
        }
        if (quality < 1) {
            mv.setViewName("product/ProductSet");
            mv.addObject("product", product);
            mv.addObject("error", "Se debe ingresar la calidad del producto");
            return mv;
        }
        if (productService.existsByName(name) && productService.getByName(name).get().getId() != id) {
            mv.setViewName("product/ProductSet");
            mv.addObject("product", product);
            mv.addObject("error", "ese nombre ya existe");
            return mv;
        }
        product.setName(name);
        product.setPrice(price);
        product.setQuantity(quantity);
        product.setQuality(quality);
        productService.save(product);
        return new ModelAndView("redirect:/product/list");
    }

    @PreAuthorize("hasRole('ADMIN')")
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

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") int id) {
        // Check if exists
        if (productService.existsById(id)) {
            productService.delete(id);
            return new ModelAndView("redirect:/product/list");
        }
        return null;
    }
}
