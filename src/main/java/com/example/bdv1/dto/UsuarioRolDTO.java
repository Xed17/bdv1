package com.example.bdv1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsuarioRolDTO {
    private Long id;
    private Long idUsuario;
    private Long idRol;
}