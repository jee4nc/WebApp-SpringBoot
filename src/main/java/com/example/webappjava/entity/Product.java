package com.example.webappjava.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Product {

    @Id
    @SequenceGenerator(
            name="product_sequence",
            sequenceName = "product_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_sequence"
    )
    private int id;

    @NotNull
    @Column(unique = true)
    private String name;

    private Double price;

    private Double quantity;

    private Integer quality;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unitMeasure_id")
    @JsonBackReference
    private UnitMeasurement unitMeasurement;

    public Product(String name, Double price, Double quantity, Integer quality, UnitMeasurement unitMeasurement) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.quality = quality;
        this.unitMeasurement = unitMeasurement;
    }
}
