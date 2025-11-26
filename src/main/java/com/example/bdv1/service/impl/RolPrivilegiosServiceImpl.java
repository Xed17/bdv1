package com.example.bdv1.service.impl;

import com.example.bdv1.controller.exceptions.ResourceNotFoundException;
import com.example.bdv1.dto.RolPrivilegiosDTO;
import com.example.bdv1.entity.Rol;
import com.example.bdv1.entity.Privilegio;
import com.example.bdv1.entity.RolPrivilegios;
import com.example.bdv1.mappers.RolPrivilegiosMapper;
import com.example.bdv1.repository.RolPrivilegiosRepository;
import com.example.bdv1.repository.RolRepository;
import com.example.bdv1.repository.PrivilegioRepository;
import com.example.bdv1.service.service.RolPrivilegiosService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RolPrivilegiosServiceImpl implements RolPrivilegiosService {

    private final RolPrivilegiosRepository repository;
    private final RolPrivilegiosMapper mapper;
    private final RolRepository rolRepository;
    private final PrivilegioRepository privilegioRepository;

    public RolPrivilegiosServiceImpl(
            RolPrivilegiosRepository repository,
            RolPrivilegiosMapper mapper,
            RolRepository rolRepository,
            PrivilegioRepository privilegioRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.rolRepository = rolRepository;
        this.privilegioRepository = privilegioRepository;
    }

    @Override
    public RolPrivilegiosDTO create(RolPrivilegiosDTO dto) throws ServiceException {
        if (dto == null) {
            throw new IllegalArgumentException("El rol privilegio no puede ser nulo");
        }

        Rol rol = rolRepository.findById(dto.getIdRol())
                .orElseThrow(() -> new ResourceNotFoundException("Rol no encontrado con ID: " + dto.getIdRol()));
        Privilegio privilegio = privilegioRepository.findById(dto.getIdPrivilegio())
                .orElseThrow(() -> new ResourceNotFoundException("Privilegio no encontrado con ID: " + dto.getIdPrivilegio()));

        RolPrivilegios entity = new RolPrivilegios();
        entity.setRol(rol);
        entity.setPrivilegio(privilegio);

        RolPrivilegios saved = repository.save(entity);
        return mapper.toDTO(saved);
    }

    @Override
    public RolPrivilegiosDTO update(Long id, RolPrivilegiosDTO dto) throws ServiceException {
        if (id == null || dto == null) {
            throw new IllegalArgumentException("ID y datos no pueden ser nulos");
        }

        RolPrivilegios existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("RolPrivilegios no encontrado con ID: " + id));

        Rol rol = rolRepository.findById(dto.getIdRol())
                .orElseThrow(() -> new ResourceNotFoundException("Rol no encontrado con ID: " + dto.getIdRol()));
        Privilegio privilegio = privilegioRepository.findById(dto.getIdPrivilegio())
                .orElseThrow(() -> new ResourceNotFoundException("Privilegio no encontrado con ID: " + dto.getIdPrivilegio()));

        existing.setRol(rol);
        existing.setPrivilegio(privilegio);

        RolPrivilegios updated = repository.save(existing);
        return mapper.toDTO(updated);
    }

    @Override
    @Transactional
    public RolPrivilegiosDTO findById(Long id) throws ServiceException {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser nulo");
        }
        RolPrivilegios entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("RolPrivilegios no encontrado con ID: " + id));
        return mapper.toDTO(entity);
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser nulo");
        }
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("RolPrivilegios no encontrado con ID: " + id);
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            throw new RuntimeException("No se puede eliminar: error de integridad", ex);
        }
    }

    @Override
    @Transactional
    public List<RolPrivilegiosDTO> findAll() throws ServiceException {
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .toList();
    }
}