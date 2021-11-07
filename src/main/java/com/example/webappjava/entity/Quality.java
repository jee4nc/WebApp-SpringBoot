package com.example.webappjava.entity;

import com.example.webappjava.enums.QualityName;
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
@Table(name = "quality")
public class Quality {
    @Id
    @SequenceGenerator(
            name="quality_sequence",
            sequenceName = "quality_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "quality_sequence"
    )
    private int id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private QualityName qualityName;

    @OneToMany(mappedBy = "quality", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Product> products;
}
