package com.example.webappjava.controller;

import com.example.webappjava.entity.AppUser;
import com.example.webappjava.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Optional;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    AppUserService appUserService;

    Optional<AppUser> currentUserEmailTop;

    @GetMapping
    public String profile(Model model, @RequestParam String emailUser){
        Optional<AppUser> optionalAppUser = appUserService.getByEmail(emailUser);
        currentUserEmailTop = appUserService.getByEmail(emailUser);
        AppUser usuarioLogeado = currentUserEmailTop.get();
        DateFormat df = new SimpleDateFormat();
        Integer exists = 0;
        String itsSupplier = "";
        String itsCarrier = "";
        if(usuarioLogeado.getCarrier() == null && usuarioLogeado.getSupplier() == null){
            exists = 0;
        }else{
            exists = 1;
        }

        if(usuarioLogeado.getSupplier() != null){
            itsSupplier = "Usted es usuario de tipo Proovedor";
        }
        if(usuarioLogeado.getCarrier() != null){
            itsCarrier = "Usted es usuario de tipo Transportista";
        }

        model.addAttribute("user", usuarioLogeado);
        model.addAttribute("formatter", df);
        model.addAttribute("exist", exists);
        model.addAttribute("itsSupplier", itsSupplier);
        model.addAttribute("itsCarrier", itsCarrier);
        model.addAttribute("emptyStr", "");
        return "profile";
    }
}
