package com.example.bdv1.mappers;

import com.example.bdv1.dto.UsuarioRolDTO;
import com.example.bdv1.entity.UsuarioRol;
import com.example.bdv1.mappers.base.BaseMappers;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioRolMapper extends BaseMappers<UsuarioRol, UsuarioRolDTO> {
    @Mapping(source = "idUsuarioRol", target = "id")
    UsuarioRolDTO toDTO(UsuarioRol usuarioRol);

    @InheritInverseConfiguration
    UsuarioRol toEntity(UsuarioRolDTO usuarioRolDTO);
}