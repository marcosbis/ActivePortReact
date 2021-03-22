package com.activeport.web.service.impl;

import com.activeport.web.domain.ConfigJob;
import com.activeport.web.repository.ConfigJobRepository;
import com.activeport.web.service.ConfigJobService;
import com.activeport.web.service.dto.ConfigJobDTO;
import com.activeport.web.service.mapper.ConfigJobMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ConfigJob}.
 */
@Service
@Transactional
public class ConfigJobServiceImpl implements ConfigJobService {
    private final Logger log = LoggerFactory.getLogger(ConfigJobServiceImpl.class);

    private final ConfigJobRepository configJobRepository;

    private final ConfigJobMapper configJobMapper;

    public ConfigJobServiceImpl(ConfigJobRepository configJobRepository, ConfigJobMapper configJobMapper) {
        this.configJobRepository = configJobRepository;
        this.configJobMapper = configJobMapper;
    }

    @Override
    public ConfigJobDTO save(ConfigJobDTO configJobDTO) {
        log.debug("Request to save ConfigJob : {}", configJobDTO);
        ConfigJob configJob = configJobMapper.toEntity(configJobDTO);
        configJob = configJobRepository.save(configJob);
        return configJobMapper.toDto(configJob);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ConfigJobDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ConfigJobs");
        return configJobRepository.findAll(pageable).map(configJobMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ConfigJobDTO> findOne(Long id) {
        log.debug("Request to get ConfigJob : {}", id);
        return configJobRepository.findById(id).map(configJobMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ConfigJob : {}", id);
        configJobRepository.deleteById(id);
    }
}
