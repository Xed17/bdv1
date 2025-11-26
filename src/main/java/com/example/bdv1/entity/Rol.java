package com.example.bdv1.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Rol")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private Long idRol;

    @Column(name = "descripcion", nullable = true, length = 100)
    private String descripcion;

    @OneToMany(mappedBy = "rol", fetch = FetchType.LAZY)
    private List<UsuarioRol> usuarios;

    @OneToMany(mappedBy = "rol", fetch = FetchType.LAZY)
    private List<RolPrivilegios> privilegios;
}