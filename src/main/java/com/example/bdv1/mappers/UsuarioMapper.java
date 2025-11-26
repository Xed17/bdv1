package com.example.bdv1.mappers;

import com.example.bdv1.dto.UsuarioDTO;
import com.example.bdv1.entity.Usuario;
import com.example.bdv1.mappers.base.BaseMappers;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioMapper extends BaseMappers<Usuario, UsuarioDTO> {

    @Mapping(source = "idUsuario", target = "id")
    UsuarioDTO toDTO(Usuario usuario);

    @InheritInverseConfiguration
    @Mapping(target = "idUsuario", ignore = true) // El ID lo genera la BD
    Usuario toEntity(UsuarioDTO usuarioDTO);
}