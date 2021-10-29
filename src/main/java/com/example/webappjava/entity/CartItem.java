package com.example.webappjava.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "cartitem")
public class CartItem {

    @Id
    @SequenceGenerator(
            name="cartItem_sequence",
            sequenceName = "cartItem_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "cartItem_sequence"
    )
    private int id;

    private int quantity;

    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;

    @Temporal(TemporalType.DATE)
    private Date date;



}
