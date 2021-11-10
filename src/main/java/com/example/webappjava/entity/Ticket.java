package com.example.webappjava.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.scheduling.support.SimpleTriggerContext;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "ticket")
public class Ticket {
    @Id
    @SequenceGenerator(
            name = "ticket_sequence",
            sequenceName = "ticket_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ticket_sequence"
    )
    private int id;

    private Date creationDate;

    private Date doneDate;

    private Double total;

    private Boolean done;

    @ManyToOne
    private AppUser appUser;

    @OneToMany(mappedBy = "ticket")
    private List<TicketDetail> detail;

    public Ticket(Date creationDate,
                  Date doneDate,
                  Double total,
                  Boolean done,
                  AppUser appUser,
                  List<TicketDetail> detail) {
        this.creationDate = creationDate;
        this.doneDate = doneDate;
        this.total = total;
        this.done = done;
        this.appUser = appUser;
        this.detail = detail;
    }
}
