package com.example.achar.model.users;

import com.example.achar.model.Users;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Entity
public class Client extends Users {
}
