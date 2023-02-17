package com.example.achar.dto.ordered;


import com.example.achar.model.order.OrderedStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GetOrderedDto {

    long price;
    String text;
    long date;
    long time;
    long startedTime;
    long finishTime;
    String address;
    @Enumerated(EnumType.STRING)
    OrderedStatus orderedStatus;
}
