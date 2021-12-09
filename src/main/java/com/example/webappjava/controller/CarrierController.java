package com.example.webappjava.controller;

import com.example.webappjava.service.CarrierService;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/carrier")
public class CarrierController {

    @Autowired
    CarrierService carrierService;

    @GetMapping
    public String createForm(Model model) {
        String[] licencias = new String[] {"Licencia A2", "Licencia A3", "Licencia A4", "Licencia A5"};
        model.addAttribute("licenses", licencias);
        return ("/carrier/carrierForm");
    }


}
