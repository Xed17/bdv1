package com.example.bdv1.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Privilegio")
public class Privilegio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_privilegio")
    private Long idPrivilegio;

    @Column(name = "descripcion", nullable = true, length = 100)
    private String descripcion;

    @OneToMany(mappedBy = "privilegio", fetch = FetchType.LAZY)
    private List<RolPrivilegios> roles;
}