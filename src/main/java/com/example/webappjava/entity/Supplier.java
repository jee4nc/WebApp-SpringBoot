package com.example.webappjava.entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Supplier {
    @Id
    @SequenceGenerator(
            name = "supplier_sequence",
            sequenceName = "supplier_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "supplier_sequence"
    )
    private int id;

    @NotNull
    @Column(unique = true)
    private String rut;

    @NotNull
    private Date birthday;

    @OneToOne(mappedBy = "supplier")
    private AppUser appUser;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    public Supplier(String rut, Date birthday, AppUser appUser, City city) {
        this.rut = rut;
        this.birthday = birthday;
        this.appUser = appUser;
        this.city = city;
    }
}
