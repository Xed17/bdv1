package com.example.bdv1.controller.general;

import com.example.bdv1.dto.DestinoDTO;
import com.example.bdv1.service.service.DestinoService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/destinos")
public class DestinoController {
    private final DestinoService destinoService;

    public DestinoController(DestinoService destinoService) {
        this.destinoService = destinoService;
    }

    @PreAuthorize("hasAuthority('GET_ALL_DESTINOS')")
    @GetMapping
    public ResponseEntity<List<DestinoDTO>> listarDestinos() {
        List<DestinoDTO> destinos = destinoService.findAll();
        return ResponseEntity.ok(destinos);
    }

    @PreAuthorize("hasAuthority('GET_ONE_DESTINOS')")
    @GetMapping("/{id}")
    public ResponseEntity<DestinoDTO> obtenerDestino(@PathVariable Long id) {
        DestinoDTO destino = destinoService.findById(id);
        return ResponseEntity.ok(destino);
    }

    @PreAuthorize("hasAuthority('CREATE_DESTINOS')")
    @PostMapping
    public ResponseEntity<DestinoDTO> crearDestino(@RequestBody DestinoDTO destinoDTO) {
        DestinoDTO creada = destinoService.create(destinoDTO);
        return ResponseEntity.ok(creada);
    }

    @PreAuthorize("hasAuthority('UPDATE_DESTINOS')")
    @PutMapping("/{id}")
    public ResponseEntity<DestinoDTO> actualizarDestino(@PathVariable Long id,
                                                        @RequestBody DestinoDTO destinoDTO) {
        DestinoDTO actualizada = destinoService.update(id, destinoDTO);
        return ResponseEntity.ok(actualizada);
    }

    @PreAuthorize("hasAuthority('DELETE_DESTINOS')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDestino(@PathVariable Long id) {
        destinoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}