package com.example.bdv1.controller.general;

import com.example.bdv1.dto.RolPrivilegiosDTO;
import com.example.bdv1.service.service.RolPrivilegiosService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rol-privilegios")
public class RolPrivilegiosController {
    private final RolPrivilegiosService rolPrivilegiosService;

    public RolPrivilegiosController(RolPrivilegiosService rolPrivilegiosService) {
        this.rolPrivilegiosService = rolPrivilegiosService;
    }

    @GetMapping
    public ResponseEntity<List<RolPrivilegiosDTO>> listarRolPrivilegios() {
        List<RolPrivilegiosDTO> privilegios = rolPrivilegiosService.findAll();
        return ResponseEntity.ok(privilegios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RolPrivilegiosDTO> obtenerRolPrivilegios(@PathVariable Long id) {
        RolPrivilegiosDTO privilegio = rolPrivilegiosService.findById(id);
        return ResponseEntity.ok(privilegio);
    }

    @PostMapping
    public ResponseEntity<RolPrivilegiosDTO> crearRolPrivilegios(@RequestBody RolPrivilegiosDTO rolPrivilegiosDTO) {
        RolPrivilegiosDTO creada = rolPrivilegiosService.create(rolPrivilegiosDTO);
        return ResponseEntity.ok(creada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RolPrivilegiosDTO> actualizarRolPrivilegios(@PathVariable Long id,
                                                                      @RequestBody RolPrivilegiosDTO rolPrivilegiosDTO) {
        RolPrivilegiosDTO actualizada = rolPrivilegiosService.update(id, rolPrivilegiosDTO);
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRolPrivilegios(@PathVariable Long id) {
        rolPrivilegiosService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}