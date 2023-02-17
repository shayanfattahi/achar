package com.example.achar.dto.ordered;
import com.example.achar.model.order.Ordered;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Optional;


@Mapper
public interface OrderedMapper {

    OrderedMapper INSTANCE = Mappers.getMapper(OrderedMapper.class);

    Ordered dtoToModel(OrderedDto orderedDto);

    List<GetOrderedDto> modelToGetDto(List<Ordered> ordered);
}
