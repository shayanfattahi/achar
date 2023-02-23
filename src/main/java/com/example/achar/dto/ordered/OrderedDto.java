package com.example.achar.dto.ordered;
import com.example.achar.model.order.OrderedStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderedDto {

    @NotNull(message = "khalie")
    long price;

    String text;

    @NotNull(message = "khalie")
    long date;

    String address;

    @Enumerated(EnumType.STRING)
    OrderedStatus orderedStatus;
}
