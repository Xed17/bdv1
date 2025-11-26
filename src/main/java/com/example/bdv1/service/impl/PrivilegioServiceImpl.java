package com.example.bdv1.service.impl;

import com.example.bdv1.controller.exceptions.ResourceNotFoundException;
import com.example.bdv1.dto.PrivilegioDTO;
import com.example.bdv1.entity.Privilegio;
import com.example.bdv1.mappers.PrivilegioMapper;
import com.example.bdv1.repository.PrivilegioRepository;
import com.example.bdv1.service.service.PrivilegioService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PrivilegioServiceImpl implements PrivilegioService {

    private final PrivilegioRepository repository;
    private final PrivilegioMapper mapper;

    public PrivilegioServiceImpl(PrivilegioRepository repository, PrivilegioMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public PrivilegioDTO create(PrivilegioDTO dto) throws ServiceException {
        if (dto == null) {
            throw new IllegalArgumentException("El privilegio no puede ser nulo");
        }
        Privilegio entity = mapper.toEntity(dto);
        Privilegio saved = repository.save(entity);
        return mapper.toDTO(saved);
    }

    @Override
    public PrivilegioDTO update(Long id, PrivilegioDTO dto) throws ServiceException {
        if (id == null || dto == null) {
            throw new IllegalArgumentException("ID y datos no pueden ser nulos");
        }
        Privilegio existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Privilegio no encontrado con ID: " + id));
        existing.setDescripcion(dto.getDescripcion());
        Privilegio updated = repository.save(existing);
        return mapper.toDTO(updated);
    }

    @Override
    @Transactional
    public PrivilegioDTO findById(Long id) throws ServiceException {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser nulo");
        }
        Privilegio entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Privilegio no encontrado con ID: " + id));
        return mapper.toDTO(entity);
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser nulo");
        }
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Privilegio no encontrado con ID: " + id);
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            throw new RuntimeException("No se puede eliminar: existen relaciones asociadas", ex);
        }
    }

    @Override
    @Transactional
    public List<PrivilegioDTO> findAll() throws ServiceException {
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .toList();
    }
}