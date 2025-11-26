package com.example.bdv1.mappers;

import com.example.bdv1.dto.RolPrivilegiosDTO;
import com.example.bdv1.entity.RolPrivilegios;
import com.example.bdv1.mappers.base.BaseMappers;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RolPrivilegiosMapper extends BaseMappers<RolPrivilegios, RolPrivilegiosDTO> {
    @Mapping(source = "idRolPrivilegios", target = "id")
    RolPrivilegiosDTO toDTO(RolPrivilegios rolPrivilegios);

    @InheritInverseConfiguration
    RolPrivilegios toEntity(RolPrivilegiosDTO rolPrivilegiosDTO);
}