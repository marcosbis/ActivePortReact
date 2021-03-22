package com.activeport.web.service.impl;

import com.activeport.web.domain.EntityManager;
import com.activeport.web.repository.EntityManagerRepository;
import com.activeport.web.service.EntityManagerService;
import com.activeport.web.service.dto.EntityManagerDTO;
import com.activeport.web.service.mapper.EntityManagerMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link EntityManager}.
 */
@Service
@Transactional
public class EntityManagerServiceImpl implements EntityManagerService {
    private final Logger log = LoggerFactory.getLogger(EntityManagerServiceImpl.class);

    private final EntityManagerRepository entityManagerRepository;

    private final EntityManagerMapper entityManagerMapper;

    public EntityManagerServiceImpl(EntityManagerRepository entityManagerRepository, EntityManagerMapper entityManagerMapper) {
        this.entityManagerRepository = entityManagerRepository;
        this.entityManagerMapper = entityManagerMapper;
    }

    @Override
    public EntityManagerDTO save(EntityManagerDTO entityManagerDTO) {
        log.debug("Request to save EntityManager : {}", entityManagerDTO);
        EntityManager entityManager = entityManagerMapper.toEntity(entityManagerDTO);
        entityManager = entityManagerRepository.save(entityManager);
        return entityManagerMapper.toDto(entityManager);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<EntityManagerDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EntityManagers");
        return entityManagerRepository.findAll(pageable).map(entityManagerMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<EntityManagerDTO> findOne(Long id) {
        log.debug("Request to get EntityManager : {}", id);
        return entityManagerRepository.findById(id).map(entityManagerMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete EntityManager : {}", id);
        entityManagerRepository.deleteById(id);
    }
}
