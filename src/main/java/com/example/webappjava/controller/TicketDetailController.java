package com.example.webappjava.controller;

import com.example.webappjava.entity.Product;
import com.example.webappjava.entity.TicketDetail;
import com.example.webappjava.service.ProductService;
import com.example.webappjava.service.TicketDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/ticketdetail")
public class TicketDetailController {

    @Autowired
    TicketDetailService ticketDetailService;

    @Autowired
    ProductService productService;


    

//    @PostMapping("/cart")
//    public String addCart(@RequestParam Integer id, @RequestParam Integer cantidad, Model model) {
//        TicketDetail ticketDetail = new TicketDetail();
//        Product product = new Product();
//        double sumaTotal = 0;
//
//        Optional<Product> optionalProduct = productService.getOne(id);
//        product = optionalProduct.get();
//
//        ticketDetail.setQuantity(cantidad);
//        ticketDetail.setPrice(product.getPrice());
//        ticketDetail.setName(product.getName());
//        ticketDetail.setTotal(product.getPrice() * cantidad);
//        ticketDetail.setProduct(product);
//
////        //validar que le producto no se añada 2 veces
////        Integer idProducto=product.getId();
//////        boolean ingresado=ticketDetail.stream().anyMatch(p -> p.getProducto().getId()==idProducto);
////
//////        if (!ingresado) {
//////            detalles.add(detalleOrden);
//////        }
//
//        sumaTotal = ticketDetail.stream().mapToDouble(dt -> dt.getTotal()).sum();
//
//        orden.setTotal(sumaTotal);
//        model.addAttribute("cart", detalles);
//        model.addAttribute("orden", orden);
//
//        return "usuario/carrito";
//    }
}
