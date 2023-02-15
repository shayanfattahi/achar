package com.example.achar.model.order;

import com.example.achar.model.services.UnderService;
import com.example.achar.model.users.Client;
import com.example.achar.model.users.Technician;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Entity
public class Ordered {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    long price;
    String text;
    long date;
    long time;
    long startedTime;
    long finishTime;
    String address;
    @Enumerated(EnumType.STRING)
    OrderedStatus orderedStatus;

    @JsonIgnore
    @ManyToOne
    UnderService underService;

    @JsonIgnore
    @ManyToOne
    Client client;

    @JsonIgnore
    @ManyToOne
    Technician technician;
}
