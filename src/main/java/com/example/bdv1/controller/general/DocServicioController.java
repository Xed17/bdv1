package com.example.bdv1.controller.general;

import com.example.bdv1.dto.DocServicioDTO;
import com.example.bdv1.service.service.DocServicioService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/docservicios")
public class DocServicioController {
    private final DocServicioService docServicioService;

    public DocServicioController(DocServicioService docServicioService) {
        this.docServicioService = docServicioService;
    }

    @PreAuthorize("hasAuthority('GET_ALL_DOC_SERVICIOS')")
    @GetMapping
    public ResponseEntity<List<DocServicioDTO>> listarDocServicios() {
        List<DocServicioDTO> docs = docServicioService.findAll();
        return ResponseEntity.ok(docs);
    }

    @PreAuthorize("hasAuthority('GET_ONE_DOC_SERVICIOS')")
    @GetMapping("/{id}")
    public ResponseEntity<DocServicioDTO> obtenerDocServicio(@PathVariable Long id) {
        DocServicioDTO doc = docServicioService.findById(id);
        return ResponseEntity.ok(doc);
    }

    @PreAuthorize("hasAuthority('CREATE_DOC_SERVICIOS')")
    @PostMapping
    public ResponseEntity<DocServicioDTO> crearDocServicio(@RequestBody DocServicioDTO docServicioDTO) {
        DocServicioDTO creada = docServicioService.create(docServicioDTO);
        return ResponseEntity.ok(creada);
    }

    @PreAuthorize("hasAuthority('UPDATE_DOC_SERVICIOS')")
    @PutMapping("/{id}")
    public ResponseEntity<DocServicioDTO> actualizarDocServicio(@PathVariable Long id,
                                                                @RequestBody DocServicioDTO docServicioDTO) {
        DocServicioDTO actualizada = docServicioService.update(id, docServicioDTO);
        return ResponseEntity.ok(actualizada);
    }

    @PreAuthorize("hasAuthority('DELETE_DOC_SERVICIOS')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDocServicio(@PathVariable Long id) {
        docServicioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}