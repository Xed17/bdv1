package com.example.bdv1.controller.general;

import com.example.bdv1.dto.ChoferDTO;
import com.example.bdv1.service.service.ChoferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/choferes")
public class ChoferController {
    private final ChoferService choferService;

    public ChoferController(ChoferService choferService) {
        this.choferService = choferService;
    }

    @GetMapping
    public ResponseEntity<List<ChoferDTO>> listarChoferes() {
        List<ChoferDTO> choferes = choferService.findAll();
        return ResponseEntity.ok(choferes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChoferDTO> obtenerChofer(@PathVariable Long id) {
        ChoferDTO chofer = choferService.findById(id);
        return ResponseEntity.ok(chofer);
    }

    @PostMapping
    public ResponseEntity<ChoferDTO> crearChofer(@RequestBody ChoferDTO choferDTO) {
        ChoferDTO creada = choferService.create(choferDTO);
        return ResponseEntity.ok(creada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChoferDTO> actualizarChofer(@PathVariable Long id,
                                                      @RequestBody ChoferDTO choferDTO) {
        ChoferDTO actualizada = choferService.update(id, choferDTO);
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarChofer(@PathVariable Long id) {
        choferService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}