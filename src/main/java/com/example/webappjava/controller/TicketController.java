package com.example.webappjava.controller;


import com.example.webappjava.entity.AppUser;
import com.example.webappjava.entity.Product;
import com.example.webappjava.entity.Ticket;
import com.example.webappjava.entity.TicketDetail;
import com.example.webappjava.service.AppUserService;
import com.example.webappjava.service.ProductService;
import com.example.webappjava.service.TicketDetailService;
import com.example.webappjava.service.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    TicketService ticketService;

    @Autowired
    TicketDetailService ticketDetailService;

    @Autowired
    ProductService productService;

    @Autowired
    AppUserService appUserService;

    List<TicketDetail> ticketDetails = new ArrayList<>();
    Optional<AppUser> currentUserEmailTop;
    Ticket ticket = new Ticket();

    Logger logger = LoggerFactory.getLogger(TicketController.class);

    @PostMapping("/cart")
    public String addCart(
            @RequestParam Integer id,
            @RequestParam Double quantity,
            @RequestParam String emailUser,
            Model model
    ) {
        TicketDetail ticketDetail = new TicketDetail();
        Product product = new Product();
        double sumTotal = 0;

        Optional<Product> optionalProduct = productService.getOne(id);
        Optional<AppUser> optionalAppUser = appUserService.getByEmail(emailUser);

        currentUserEmailTop = appUserService.getByEmail(emailUser);
        product.setName(optionalProduct.get().getName());
        product.setPrice(optionalProduct.get().getPrice());
        product.setId(optionalProduct.get().getId());
        ticketDetail.setQuantity(quantity);
        ticketDetail.setPrice(product.getPrice());
        ticketDetail.setName(product.getName());
        DecimalFormat df = new DecimalFormat("#.##");
        Double priceProduct = product.getPrice()*quantity;
        ticketDetail.setTotal(Double.parseDouble(df.format(priceProduct)));
        ticketDetail.setProduct(product);

        // validation of multiple product
        Integer idProduct = product.getId();
        boolean ingresado = ticketDetails.stream().anyMatch(p -> p.getProduct().getId() == idProduct);

        if(!ingresado){
            ticketDetails.add(ticketDetail);
        }

        sumTotal = ticketDetails.stream().mapToDouble(dt ->dt.getTotal()).sum();
        ticket.setTotal(sumTotal);

        model.addAttribute("cart", ticketDetails);
        model.addAttribute("ticket", ticket);

        logger.info("Producto añadido : {} ", product);
        logger.info("Cantidad añadida: {} ", quantity);
        logger.info("Calle Usuario : {} ", currentUserEmailTop.get().getStreet());
        return "carrito";
    }

    @GetMapping("/delete/cart/{id}")
    public String deleteProductCart(
            @PathVariable Integer id,
            Model model
    ){
        List<TicketDetail> ticketDetailNew = new ArrayList<TicketDetail>();
        for(TicketDetail ticketDetail: ticketDetails) {
            if(ticketDetail.getProduct().getId()!=id){
                ticketDetailNew.add(ticketDetail);
            }
        }
        ticketDetails = ticketDetailNew;
        double sumTotal = 0;
        sumTotal = ticketDetails.stream().mapToDouble(dt ->dt.getTotal()).sum();
        ticket.setTotal(sumTotal);
        model.addAttribute("cart", ticketDetails);
        model.addAttribute("ticket", ticket);
        return "carrito";
    }

    @GetMapping("/getCart")
    public String getCart(Model model){
        model.addAttribute("cart", ticketDetails);
        model.addAttribute("ticket", ticket);
        return "carrito";
    }

    @GetMapping("/getOrden")
    public String orden(Model model){
        model.addAttribute("cart", ticketDetails);
        model.addAttribute("ticket", ticket);
        model.addAttribute("currentUserName", currentUserEmailTop.get().getFirstName());
        model.addAttribute("currentUserEmail", currentUserEmailTop.get().getEmail());
        model.addAttribute("currentUserStreet", currentUserEmailTop.get().getStreet());
        return "resumenOrden";
    }

    @GetMapping("/saveOrder")
    public String saveOrder(){
        Date creationDate = new Date();
        ticket.setCreationDate(creationDate);
        ticket.setAppUser(currentUserEmailTop.get());
        ticketService.save(ticket);

        //save ticket detail
        for(TicketDetail dt:ticketDetails){
            dt.setTicket(ticket);
            ticketDetailService.save(dt);
            // Restar cantidad al inventario general
            Product product = productService.getOne(dt.getProduct().getId()).get();
            product.setQuantity(product.getQuantity() - dt.getQuantity());
            productService.save(product);
        }

        ticket = new Ticket();
        ticketDetails.clear();

        return "redirect:/";
    }
}
