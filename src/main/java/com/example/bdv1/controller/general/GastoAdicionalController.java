package com.example.bdv1.controller.general;

import com.example.bdv1.dto.GastoAdicionalDTO;
import com.example.bdv1.service.service.GastoAdicionalService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/gastosadicionales")
public class GastoAdicionalController {
    private final GastoAdicionalService gastoAdicionalService;

    public GastoAdicionalController(GastoAdicionalService gastoAdicionalService) {
        this.gastoAdicionalService = gastoAdicionalService;
    }

    @PreAuthorize("hasAuthority('GET_ALL_GASTOS_ADICIONALES')")
    @GetMapping
    public ResponseEntity<List<GastoAdicionalDTO>> listarGastosAdicionales() {
        List<GastoAdicionalDTO> gastos = gastoAdicionalService.findAll();
        return ResponseEntity.ok(gastos);
    }

    @PreAuthorize("hasAuthority('GET_ONE_GASTOS_ADICIONALES')")
    @GetMapping("/{id}")
    public ResponseEntity<GastoAdicionalDTO> obtenerGastoAdicional(@PathVariable Long id) {
        GastoAdicionalDTO gasto = gastoAdicionalService.findById(id);
        return ResponseEntity.ok(gasto);
    }

    @PreAuthorize("hasAuthority('CREATE_GASTOS_ADICIONALES')")
    @PostMapping
    public ResponseEntity<GastoAdicionalDTO> crearGastoAdicional(@RequestBody GastoAdicionalDTO gastoAdicionalDTO) {
        GastoAdicionalDTO creada = gastoAdicionalService.create(gastoAdicionalDTO);
        return ResponseEntity.ok(creada);
    }

    @PreAuthorize("hasAuthority('UPDATE_GASTOS_ADICIONALES')")
    @PutMapping("/{id}")
    public ResponseEntity<GastoAdicionalDTO> actualizarGastoAdicional(@PathVariable Long id,
                                                                      @RequestBody GastoAdicionalDTO gastoAdicionalDTO) {
        GastoAdicionalDTO actualizada = gastoAdicionalService.update(id, gastoAdicionalDTO);
        return ResponseEntity.ok(actualizada);
    }

    @PreAuthorize("hasAuthority('DELETE_GASTOS_ADICIONALES')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarGastoAdicional(@PathVariable Long id) {
        gastoAdicionalService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}