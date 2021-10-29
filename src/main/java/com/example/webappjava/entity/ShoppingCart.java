package com.example.webappjava.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "shoppingcart")
public class ShoppingCart {

    @Id
    @SequenceGenerator(
            name="shoppingCart_sequence",
            sequenceName = "shoppingCart_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "shoppingCart_sequence"
    )
    private int id;

    @Transient
    private Double totalPrice;

    @Transient
    private int itemsNumber;

    @Temporal(TemporalType.DATE)
    private Date date;

    @OneToMany(cascade = CascadeType.ALL)
    private Collection<CartItem> items;

    private String tokenSession;

    public int getId() {
        return id;
    }

    public Double getTotalPrice() {
        Double sum = 0.0;
        for(CartItem item : this.items) {
            sum = sum + item.getProduct().getPrice();
        }
        return totalPrice;
    }

    public int getItemsNumber() {
        return this.items.size();
    }

    public Collection<CartItem> getItems() {
        return items;
    }

    public String getTokenSession() {
        return tokenSession;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setItemsNumber(int itemsNumber) {
        this.itemsNumber = itemsNumber;
    }

    public void setItems(Collection<CartItem> items) {
        this.items = items;
    }

    public void setTokenSession(String tokenSession) {
        this.tokenSession = tokenSession;
    }
}
