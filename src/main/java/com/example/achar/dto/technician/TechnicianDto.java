package com.example.achar.dto.technician;

import com.example.achar.model.users.TecStatus;
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
public class TechnicianDto {

    @NotBlank(message = "khalie")
    String firstName;

    @NotBlank(message = "khalie")
    String lastName;

    @Email
    String email;

    @Pattern(regexp ="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$" , message = "format pass ghalat ast")
    String pass;

    TecStatus tecStatus;
}
