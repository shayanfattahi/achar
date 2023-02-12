package com.example.achar.model.users;

import com.example.achar.model.Users;
import com.example.achar.model.services.UnderService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Entity
public class Technician extends Users {

    ArrayList<String> reviews;
    double point;
    TecStatus tecStatus;
    @ManyToMany(cascade = CascadeType.ALL)
    Set<UnderService> underServices = new HashSet<>();
}
