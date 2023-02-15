package com.example.achar.dto.technician;

import com.example.achar.dto.client.ClientDto;
import com.example.achar.dto.client.ClientMapper;
import com.example.achar.dto.client.GetClientDto;
import com.example.achar.model.users.Client;
import com.example.achar.model.users.Technician;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface TechnicianMapper {

    TechnicianMapper INSTANCE = Mappers.getMapper(TechnicianMapper.class);

    Technician dtoToModel(TechnicianDto technicianDto);

    GetTechnicianDto modelToGetDto(Technician technician);
}
