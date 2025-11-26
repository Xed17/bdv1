package com.example.bdv1.mappers;

import com.example.bdv1.dto.PrivilegioDTO;
import com.example.bdv1.entity.Privilegio;
import com.example.bdv1.mappers.base.BaseMappers;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PrivilegioMapper extends BaseMappers<Privilegio, PrivilegioDTO> {
    @Mapping(source = "idPrivilegio", target = "id")
    PrivilegioDTO toDTO(Privilegio privilegio);

    @InheritInverseConfiguration
    @Mapping(target = "roles", ignore = true)
    Privilegio toEntity(PrivilegioDTO privilegioDTO);
}