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
public class Carrier {

    @Id
    @SequenceGenerator(
            name="carrier_sequence",
            sequenceName = "carrier_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "carrier_sequence"
    )
    private int id;

    @NotNull
    @Column(unique = true)
    private String rut;

    @NotNull
    private Date birthday;

    @NotNull
    private String typeLicense;

    @NotNull
    private String destiny;

    @NotNull
    private Float tariff;

    @OneToOne(mappedBy = "carrier")
    private AppUser appUser;

    @ManyToOne
    @JoinColumn(name="city_id", nullable = false)
    private City city;



    public Carrier(String rut, Date birthday, String typeLicense, String destiny, Float tariff, AppUser appUser, City city) {
        this.rut = rut;
        this.birthday = birthday;
        this.typeLicense = typeLicense;
        this.destiny = destiny;
        this.tariff = tariff;
        this.appUser = appUser;
        this.city = city;
    }
}
