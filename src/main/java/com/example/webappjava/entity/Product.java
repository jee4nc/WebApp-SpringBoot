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
    private String image;

    private Double quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unitMeasure_id")
    @JsonBackReference
    private UnitMeasurement unitMeasurement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quality_id")
    @JsonBackReference
    private Quality quality;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id")
    @JsonBackReference
    private Supplier supplier;

    public Product(String name, Double price, Double quantity, String image, Quality quality, UnitMeasurement unitMeasurement) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.quality = quality;
        this.image = image;
        this.unitMeasurement = unitMeasurement;
    }
}
