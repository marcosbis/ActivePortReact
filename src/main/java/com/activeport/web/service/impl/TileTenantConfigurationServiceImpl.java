package com.activeport.web.service.impl;

import com.activeport.web.domain.TileTenantConfiguration;
import com.activeport.web.repository.TileTenantConfigurationRepository;
import com.activeport.web.service.TileTenantConfigurationService;
import com.activeport.web.service.dto.TileTenantConfigurationDTO;
import com.activeport.web.service.mapper.TileTenantConfigurationMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TileTenantConfiguration}.
 */
@Service
@Transactional
public class TileTenantConfigurationServiceImpl implements TileTenantConfigurationService {
    private final Logger log = LoggerFactory.getLogger(TileTenantConfigurationServiceImpl.class);

    private final TileTenantConfigurationRepository tileTenantConfigurationRepository;

    private final TileTenantConfigurationMapper tileTenantConfigurationMapper;

    public TileTenantConfigurationServiceImpl(
        TileTenantConfigurationRepository tileTenantConfigurationRepository,
        TileTenantConfigurationMapper tileTenantConfigurationMapper
    ) {
        this.tileTenantConfigurationRepository = tileTenantConfigurationRepository;
        this.tileTenantConfigurationMapper = tileTenantConfigurationMapper;
    }

    @Override
    public TileTenantConfigurationDTO save(TileTenantConfigurationDTO tileTenantConfigurationDTO) {
        log.debug("Request to save TileTenantConfiguration : {}", tileTenantConfigurationDTO);
        TileTenantConfiguration tileTenantConfiguration = tileTenantConfigurationMapper.toEntity(tileTenantConfigurationDTO);
        tileTenantConfiguration = tileTenantConfigurationRepository.save(tileTenantConfiguration);
        return tileTenantConfigurationMapper.toDto(tileTenantConfiguration);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TileTenantConfigurationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TileTenantConfigurations");
        return tileTenantConfigurationRepository.findAll(pageable).map(tileTenantConfigurationMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TileTenantConfigurationDTO> findOne(Long id) {
        log.debug("Request to get TileTenantConfiguration : {}", id);
        return tileTenantConfigurationRepository.findById(id).map(tileTenantConfigurationMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TileTenantConfiguration : {}", id);
        tileTenantConfigurationRepository.deleteById(id);
    }
}
