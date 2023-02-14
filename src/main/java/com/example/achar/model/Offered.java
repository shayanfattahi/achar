package com.example.achar.model;

import com.example.achar.model.order.Ordered;
import com.example.achar.model.services.UnderService;
import com.example.achar.model.users.Client;
import com.example.achar.model.users.Technician;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Entity
public class Offered {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    long price;
    String text;
    long date;
    long time;
    boolean accepted;

    @ManyToOne
    UnderService underService;

    @ManyToOne
    Client client;

    @ManyToOne
    Technician technician;

    @ManyToOne
    Ordered ordered;
}
