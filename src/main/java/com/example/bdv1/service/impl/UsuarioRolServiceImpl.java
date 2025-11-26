package com.example.bdv1.service.impl;

import com.example.bdv1.controller.exceptions.ResourceNotFoundException;
import com.example.bdv1.dto.UsuarioRolDTO;
import com.example.bdv1.entity.Usuario;
import com.example.bdv1.entity.Rol;
import com.example.bdv1.entity.UsuarioRol;
import com.example.bdv1.mappers.UsuarioRolMapper;
import com.example.bdv1.repository.UsuarioRolRepository;
import com.example.bdv1.repository.UsuarioRepository;
import com.example.bdv1.repository.RolRepository;
import com.example.bdv1.service.service.UsuarioRolService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioRolServiceImpl implements UsuarioRolService {

    private final UsuarioRolRepository repository;
    private final UsuarioRolMapper mapper;
    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;

    public UsuarioRolServiceImpl(
            UsuarioRolRepository repository,
            UsuarioRolMapper mapper,
            UsuarioRepository usuarioRepository,
            RolRepository rolRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
    }

    @Override
    public UsuarioRolDTO create(UsuarioRolDTO dto) throws ServiceException {
        if (dto == null) {
            throw new IllegalArgumentException("El usuario rol no puede ser nulo");
        }

        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + dto.getIdUsuario()));
        Rol rol = rolRepository.findById(dto.getIdRol())
                .orElseThrow(() -> new ResourceNotFoundException("Rol no encontrado con ID: " + dto.getIdRol()));

        UsuarioRol entity = new UsuarioRol();
        entity.setUsuario(usuario);
        entity.setRol(rol);

        UsuarioRol saved = repository.save(entity);
        return mapper.toDTO(saved);
    }

    @Override
    public UsuarioRolDTO update(Long id, UsuarioRolDTO dto) throws ServiceException {
        if (id == null || dto == null) {
            throw new IllegalArgumentException("ID y datos no pueden ser nulos");
        }

        UsuarioRol existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UsuarioRol no encontrado con ID: " + id));

        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + dto.getIdUsuario()));
        Rol rol = rolRepository.findById(dto.getIdRol())
                .orElseThrow(() -> new ResourceNotFoundException("Rol no encontrado con ID: " + dto.getIdRol()));

        existing.setUsuario(usuario);
        existing.setRol(rol);

        UsuarioRol updated = repository.save(existing);
        return mapper.toDTO(updated);
    }

    // findById, deleteById, findAll → estándar
    @Override
    public UsuarioRolDTO findById(Long id) throws ServiceException {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser nulo");
        }
        UsuarioRol entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UsuarioRol no encontrado con ID: " + id));
        return mapper.toDTO(entity);
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser nulo");
        }
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("UsuarioRol no encontrado con ID: " + id);
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            throw new RuntimeException("No se puede eliminar: error de integridad", ex);
        }
    }

    @Override
    public List<UsuarioRolDTO> findAll() throws ServiceException {
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .toList();
    }
}