package com.example.webappjava.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@ToString
@Table(name = "ticketDetail")
public class TicketDetail {

    @Id
    @SequenceGenerator(
            name="ticketDetail_sequence",
            sequenceName = "ticketDetail_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ticketDetail_sequence"
    )
    private int id;

    private String name;
    private double quantity;
    private double price;
    private double total;

    @ManyToOne
    private Ticket ticket;

    @ManyToOne
    private Product
    product;

    public TicketDetail(String name, double quantity, double price, double total, Ticket ticket, Product product) {
        super();
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
        this.ticket = ticket;
        this.product = product;
    }
}
