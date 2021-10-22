package com.example.webappjava.entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Country {

    @Id
    @SequenceGenerator(
            name="country_sequence",
            sequenceName = "country_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "country_sequence"
    )
    private int id;

    @NotNull
    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "country")
    private Set<City> city;

    public Country(String name, Set<City> city) {
        this.name = name;
        this.city = city;
    }
}
