package com.example.bdv1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RolPrivilegiosDTO {
    private Long id;
    private Long idRol;
    private Long idPrivilegio;
}