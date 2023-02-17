package com.example.achar.dto.ordered;
import com.example.achar.model.order.OrderedStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderedDto {
    long price;
    String text;
    long date;
    String address;
    @Enumerated(EnumType.STRING)
    OrderedStatus orderedStatus;
}
