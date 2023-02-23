package com.example.achar.dto.client;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClientDto {

    @NotBlank(message = "khalie")
    String firstName;
    @NotBlank(message = "khalie")
    String lastName;
//    @Pattern(regexp ="^(.+)@(.+)$",message = "format email ghalat ast")
    @Email(message = "email doros nis")
    String email;
    @Pattern(regexp ="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$" , message = "format pass ghalat ast")
    String pass;
}
