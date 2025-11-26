package com.example.bdv1.controller.general;

import com.example.bdv1.dto.UsuarioRolDTO;
import com.example.bdv1.service.service.UsuarioRolService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usuario-roles")
public class UsuarioRolController {
    private final UsuarioRolService usuarioRolService;

    public UsuarioRolController(UsuarioRolService usuarioRolService) {
        this.usuarioRolService = usuarioRolService;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioRolDTO>> listarUsuarioRoles() {
        List<UsuarioRolDTO> roles = usuarioRolService.findAll();
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioRolDTO> obtenerUsuarioRol(@PathVariable Long id) {
        UsuarioRolDTO rol = usuarioRolService.findById(id);
        return ResponseEntity.ok(rol);
    }

    @PostMapping
    public ResponseEntity<UsuarioRolDTO> crearUsuarioRol(@RequestBody UsuarioRolDTO usuarioRolDTO) {
        UsuarioRolDTO creada = usuarioRolService.create(usuarioRolDTO);
        return ResponseEntity.ok(creada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioRolDTO> actualizarUsuarioRol(@PathVariable Long id,
                                                              @RequestBody UsuarioRolDTO usuarioRolDTO) {
        UsuarioRolDTO actualizada = usuarioRolService.update(id, usuarioRolDTO);
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuarioRol(@PathVariable Long id) {
        usuarioRolService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}