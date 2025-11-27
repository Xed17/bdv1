package com.example.bdv1.controller.general;

import com.example.bdv1.dto.DetalleGastoAdicionalDTO;
import com.example.bdv1.service.service.DetalleGastoAdicionalService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/detallegastosadicionales")
public class DetalleGastoAdicionalController {
    private final DetalleGastoAdicionalService detalleGastoAdicionalService;

    public DetalleGastoAdicionalController(DetalleGastoAdicionalService detalleGastoAdicionalService) {
        this.detalleGastoAdicionalService = detalleGastoAdicionalService;
    }

    @PreAuthorize("hasAuthority('GET_ALL_DETALLE_GASTOS_ADICIONALES')")
    @GetMapping
    public ResponseEntity<List<DetalleGastoAdicionalDTO>> listarDetalleGastosAdicionales() {
        List<DetalleGastoAdicionalDTO> detalles = detalleGastoAdicionalService.findAll();
        return ResponseEntity.ok(detalles);
    }

    @PreAuthorize("hasAuthority('GET_ONE_DETALLE_GASTOS_ADICIONALES')")
    @GetMapping("/{id}")
    public ResponseEntity<DetalleGastoAdicionalDTO> obtenerDetalleGastoAdicional(@PathVariable Long id) {
        DetalleGastoAdicionalDTO detalle = detalleGastoAdicionalService.findById(id);
        return ResponseEntity.ok(detalle);
    }

    @PreAuthorize("hasAuthority('CREATE_DETALLE_GASTOS_ADICIONALES')")
    @PostMapping
    public ResponseEntity<DetalleGastoAdicionalDTO> crearDetalleGastoAdicional(@RequestBody DetalleGastoAdicionalDTO detalleGastoAdicionalDTO) {
        DetalleGastoAdicionalDTO creada = detalleGastoAdicionalService.create(detalleGastoAdicionalDTO);
        return ResponseEntity.ok(creada);
    }

    @PreAuthorize("hasAuthority('UPDATE_DETALLE_GASTOS_ADICIONALES')")
    @PutMapping("/{id}")
    public ResponseEntity<DetalleGastoAdicionalDTO> actualizarDetalleGastoAdicional(@PathVariable Long id,
                                                                                    @RequestBody DetalleGastoAdicionalDTO detalleGastoAdicionalDTO) {
        DetalleGastoAdicionalDTO actualizada = detalleGastoAdicionalService.update(id, detalleGastoAdicionalDTO);
        return ResponseEntity.ok(actualizada);
    }

    @PreAuthorize("hasAuthority('DELETE_DETALLE_GASTOS_ADICIONALES')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDetalleGastoAdicional(@PathVariable Long id) {
        detalleGastoAdicionalService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}