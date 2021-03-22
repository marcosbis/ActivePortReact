package com.activeport.web.service.impl;

import com.activeport.web.domain.TileConfiguration;
import com.activeport.web.repository.TileConfigurationRepository;
import com.activeport.web.service.TileConfigurationService;
import com.activeport.web.service.dto.TileConfigurationDTO;
import com.activeport.web.service.mapper.TileConfigurationMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TileConfiguration}.
 */
@Service
@Transactional
public class TileConfigurationServiceImpl implements TileConfigurationService {
    private final Logger log = LoggerFactory.getLogger(TileConfigurationServiceImpl.class);

    private final TileConfigurationRepository tileConfigurationRepository;

    private final TileConfigurationMapper tileConfigurationMapper;

    public TileConfigurationServiceImpl(
        TileConfigurationRepository tileConfigurationRepository,
        TileConfigurationMapper tileConfigurationMapper
    ) {
        this.tileConfigurationRepository = tileConfigurationRepository;
        this.tileConfigurationMapper = tileConfigurationMapper;
    }

    @Override
    public TileConfigurationDTO save(TileConfigurationDTO tileConfigurationDTO) {
        log.debug("Request to save TileConfiguration : {}", tileConfigurationDTO);
        TileConfiguration tileConfiguration = tileConfigurationMapper.toEntity(tileConfigurationDTO);
        tileConfiguration = tileConfigurationRepository.save(tileConfiguration);
        return tileConfigurationMapper.toDto(tileConfiguration);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TileConfigurationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TileConfigurations");
        return tileConfigurationRepository.findAll(pageable).map(tileConfigurationMapper::toDto);
    }

    public Page<TileConfigurationDTO> findAllWithEagerRelationships(Pageable pageable) {
        return tileConfigurationRepository.findAllWithEagerRelationships(pageable).map(tileConfigurationMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TileConfigurationDTO> findOne(Long id) {
        log.debug("Request to get TileConfiguration : {}", id);
        return tileConfigurationRepository.findOneWithEagerRelationships(id).map(tileConfigurationMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TileConfiguration : {}", id);
        tileConfigurationRepository.deleteById(id);
    }
}
