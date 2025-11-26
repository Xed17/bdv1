package com.example.bdv1.service.impl;

import com.example.bdv1.controller.exceptions.ResourceNotFoundException;
import com.example.bdv1.dto.RolDTO;
import com.example.bdv1.entity.Rol;
import com.example.bdv1.mappers.RolMapper;
import com.example.bdv1.repository.RolRepository;
import com.example.bdv1.service.service.RolService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RolServiceImpl implements RolService {

    private final RolRepository repository;
    private final RolMapper mapper;

    public RolServiceImpl(RolRepository repository, RolMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public RolDTO create(RolDTO dto) throws ServiceException {
        if (dto == null) {
            throw new IllegalArgumentException("El rol no puede ser nulo");
        }
        Rol entity = mapper.toEntity(dto);
        Rol saved = repository.save(entity);
        return mapper.toDTO(saved);
    }

    @Override
    public RolDTO update(Long id, RolDTO dto) throws ServiceException {
        if (id == null || dto == null) {
            throw new IllegalArgumentException("ID y datos no pueden ser nulos");
        }
        Rol existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rol no encontrado con ID: " + id));
        existing.setDescripcion(dto.getDescripcion());
        Rol updated = repository.save(existing);
        return mapper.toDTO(updated);
    }

    @Override
    @Transactional
    public RolDTO findById(Long id) throws ServiceException {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser nulo");
        }
        Rol entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rol no encontrado con ID: " + id));
        return mapper.toDTO(entity);
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser nulo");
        }
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Rol no encontrado con ID: " + id);
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            throw new RuntimeException("No se puede eliminar: existen relaciones asociadas", ex);
        }
    }

    @Override
    @Transactional
    public List<RolDTO> findAll() throws ServiceException {
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .toList();
    }
}