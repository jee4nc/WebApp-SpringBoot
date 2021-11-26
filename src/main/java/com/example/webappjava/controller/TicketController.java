package com.example.webappjava.controller;


import com.example.webappjava.entity.Product;
import com.example.webappjava.entity.Ticket;
import com.example.webappjava.entity.TicketDetail;
import com.example.webappjava.service.ProductService;
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
    ProductService productService;

    List<TicketDetail> ticketDetails = new ArrayList<>();
    Ticket ticket = new Ticket();

    Logger logger = LoggerFactory.getLogger(TicketController.class);

    @PostMapping("/cart")
    public String addCart(
            @RequestParam Integer id,
            @RequestParam Double quantity,
            Model model
    ) {
        TicketDetail ticketDetail = new TicketDetail();
        Product product = new Product();
        double sumTotal = 0;
        Optional<Product> optionalProduct = productService.getOne(id);
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
}
