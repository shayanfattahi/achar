package com.example.achar.dto.client;
import com.example.achar.model.users.Client;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    Client dtoToModel(ClientDto clientDto);

    GetClientDto modelToGetDto(Client client);
}
