package com.example.bdv1.service.impl;

import com.example.bdv1.controller.exceptions.ResourceNotFoundException;
import com.example.bdv1.dto.UsuarioDTO;
import com.example.bdv1.entity.Usuario;
import com.example.bdv1.mappers.UsuarioMapper;
import com.example.bdv1.repository.UsuarioRepository;
import com.example.bdv1.service.service.UsuarioService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository repository;
    private final UsuarioMapper mapper;

    public UsuarioServiceImpl(UsuarioRepository repository, UsuarioMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public UsuarioDTO create(UsuarioDTO dto) throws ServiceException {
        if (dto == null) {
            throw new IllegalArgumentException("El usuario no puede ser nulo");
        }

        Usuario entity = mapper.toEntity(dto);
        Usuario saved = repository.save(entity);
        return mapper.toDTO(saved);
    }

    @Override
    public UsuarioDTO update(Long id, UsuarioDTO dto) throws ServiceException {
        if (id == null || dto == null) {
            throw new IllegalArgumentException("ID y datos no pueden ser nulos");
        }

        Usuario existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + id));

        existing.setUsuario(dto.getUsuario());
        existing.setClave(dto.getClave());

        Usuario updated = repository.save(existing);
        return mapper.toDTO(updated);
    }

    @Override
    @Transactional
    public UsuarioDTO findById(Long id) throws ServiceException {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser nulo");
        }
        Usuario entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + id));
        return mapper.toDTO(entity);
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser nulo");
        }
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Usuario no encontrado con ID: " + id);
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            throw new RuntimeException("No se puede eliminar: existen roles asociados", ex);
        }
    }

    @Override
    @Transactional
    public List<UsuarioDTO> findAll() throws ServiceException {
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .toList();
    }
}