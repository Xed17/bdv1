package com.example.bdv1.controller.general;

import com.example.bdv1.dto.PrivilegioDTO;
import com.example.bdv1.service.service.PrivilegioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/privilegios")
public class PrivilegioController {
    private final PrivilegioService privilegioService;

    public PrivilegioController(PrivilegioService privilegioService) {
        this.privilegioService = privilegioService;
    }

    @GetMapping
    public ResponseEntity<List<PrivilegioDTO>> listarPrivilegios() {
        List<PrivilegioDTO> privilegios = privilegioService.findAll();
        return ResponseEntity.ok(privilegios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrivilegioDTO> obtenerPrivilegio(@PathVariable Long id) {
        PrivilegioDTO privilegio = privilegioService.findById(id);
        return ResponseEntity.ok(privilegio);
    }

    @PostMapping
    public ResponseEntity<PrivilegioDTO> crearPrivilegio(@RequestBody PrivilegioDTO privilegioDTO) {
        PrivilegioDTO creada = privilegioService.create(privilegioDTO);
        return ResponseEntity.ok(creada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PrivilegioDTO> actualizarPrivilegio(@PathVariable Long id,
                                                              @RequestBody PrivilegioDTO privilegioDTO) {
        PrivilegioDTO actualizada = privilegioService.update(id, privilegioDTO);
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPrivilegio(@PathVariable Long id) {
        privilegioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}