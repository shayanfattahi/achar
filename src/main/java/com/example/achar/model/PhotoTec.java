package com.example.achar.model;

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
public class PhotoTec {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    byte[] image;
    String imagePath;
    @OneToOne
    Technician technician;
}
