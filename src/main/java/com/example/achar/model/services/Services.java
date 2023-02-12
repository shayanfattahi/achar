package com.example.achar.model.services;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Entity
public class Services {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(unique = true)
    String name;
}
