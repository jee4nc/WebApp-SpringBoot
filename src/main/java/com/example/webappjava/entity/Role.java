package com.example.webappjava.entity;

import com.example.webappjava.enums.RoleName;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Role {
    @Id
    @SequenceGenerator(
            name="role_sequence",
            sequenceName = "role_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "role_sequence"
    )
    private int id;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private RoleName roleName;

    public Role(RoleName roleName) {
        this.roleName = roleName;
    }
}
