package com.example.webappjava.entity;

import com.example.webappjava.enums.UnitName;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@EqualsAndHashCode
@Table(name = "unitMeasure")
public class UnitMeasurement {
    @Id
    @SequenceGenerator(
            name="unit_sequence",
            sequenceName = "unit_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "unit_sequence"
    )
    private int id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private UnitName unitName;

    @OneToMany(mappedBy = "unitMeasurement", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Product> products;

    public UnitMeasurement(UnitName unitName, List<Product> products) {
        this.unitName = unitName;
        this.products = products;
    }
}
