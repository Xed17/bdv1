package com.example.bdv1.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

    private Long id;

    @NotBlank(message = "El nombre de usuario no puede estar vacío")
    @Size(max = 50, message = "El nombre de usuario no puede superar los 50 caracteres")
    private String usuario;

    @NotBlank(message = "La clave no puede estar vacía")
    @Size(max = 100, message = "La clave no puede superar los 100 caracteres")
    private String clave;

}