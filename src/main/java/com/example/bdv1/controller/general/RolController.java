package com.example.bdv1.controller.general;

import com.example.bdv1.dto.RolDTO;
import com.example.bdv1.service.service.RolService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
public class RolController {
    private final RolService rolService;

    public RolController(RolService rolService) {
        this.rolService = rolService;
    }

    @GetMapping
    public ResponseEntity<List<RolDTO>> listarRoles() {
        List<RolDTO> roles = rolService.findAll();
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RolDTO> obtenerRol(@PathVariable Long id) {
        RolDTO rol = rolService.findById(id);
        return ResponseEntity.ok(rol);
    }

    @PostMapping
    public ResponseEntity<RolDTO> crearRol(@RequestBody RolDTO rolDTO) {
        RolDTO creada = rolService.create(rolDTO);
        return ResponseEntity.ok(creada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RolDTO> actualizarRol(@PathVariable Long id,
                                                @RequestBody RolDTO rolDTO) {
        RolDTO actualizada = rolService.update(id, rolDTO);
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRol(@PathVariable Long id) {
        rolService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}