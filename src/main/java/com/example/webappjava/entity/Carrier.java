//package com.example.webappjava.entity;
//
//import com.sun.istack.NotNull;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import javax.persistence.*;
//import java.util.Date;
//
//@Entity
//@Getter
//@Setter
//@NoArgsConstructor
//public class Carrier {
//
//    @Id
//    @SequenceGenerator(
//            name="carrier_sequence",
//            sequenceName = "carrier_sequence",
//            allocationSize = 1
//    )
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "carrier_sequence"
//    )
//    private int id;
//
//    @NotNull
//    @Column(unique = true)
//    private String rut;
//
//    @NotNull
//    private Date birthday;
//
//    @NotNull
//    private String typeLicense;
//
//    @NotNull
//    private String destiny;
//
//    @NotNull
//    private Float tariff;
//
//}
