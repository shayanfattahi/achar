package com.example.achar.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
}
