package com.example.bdv1.mappers;

import com.example.bdv1.dto.RolDTO;
import com.example.bdv1.entity.Rol;
import com.example.bdv1.mappers.base.BaseMappers;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RolMapper extends BaseMappers<Rol, RolDTO> {
    @Mapping(source = "idRol", target = "id")
    RolDTO toDTO(Rol rol);

    @InheritInverseConfiguration
    @Mapping(target = "usuarios", ignore = true)
    @Mapping(target = "privilegios", ignore = true)
    Rol toEntity(RolDTO rolDTO);
}