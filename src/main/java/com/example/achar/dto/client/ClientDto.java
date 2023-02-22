package com.example.achar.dto.client;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import javax.validation.constraints.Pattern;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClientDto {

    String firstName;
    String lastName;
    @Pattern(regexp ="^(.+)@(.+)$",message = "format email ghalat ast")
    String email;
    @Pattern(regexp ="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$" , message = "format pass ghalat ast")
    String pass;
}
