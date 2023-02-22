package com.example.achar.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.lang.Nullable;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PayedDto {
    double point;
    String text;
}
