package com.activeport.web.service.impl;

import com.activeport.web.domain.ProviderConfiguration;
import com.activeport.web.repository.ProviderConfigurationRepository;
import com.activeport.web.service.ProviderConfigurationService;
import com.activeport.web.service.dto.ProviderConfigurationDTO;
import com.activeport.web.service.mapper.ProviderConfigurationMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ProviderConfiguration}.
 */
@Service
@Transactional
public class ProviderConfigurationServiceImpl implements ProviderConfigurationService {
    private final Logger log = LoggerFactory.getLogger(ProviderConfigurationServiceImpl.class);

    private final ProviderConfigurationRepository providerConfigurationRepository;

    private final ProviderConfigurationMapper providerConfigurationMapper;

    public ProviderConfigurationServiceImpl(
        ProviderConfigurationRepository providerConfigurationRepository,
        ProviderConfigurationMapper providerConfigurationMapper
    ) {
        this.providerConfigurationRepository = providerConfigurationRepository;
        this.providerConfigurationMapper = providerConfigurationMapper;
    }

    @Override
    public ProviderConfigurationDTO save(ProviderConfigurationDTO providerConfigurationDTO) {
        log.debug("Request to save ProviderConfiguration : {}", providerConfigurationDTO);
        ProviderConfiguration providerConfiguration = providerConfigurationMapper.toEntity(providerConfigurationDTO);
        providerConfiguration = providerConfigurationRepository.save(providerConfiguration);
        return providerConfigurationMapper.toDto(providerConfiguration);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProviderConfigurationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ProviderConfigurations");
        return providerConfigurationRepository.findAll(pageable).map(providerConfigurationMapper::toDto);
    }

    public Page<ProviderConfigurationDTO> findAllWithEagerRelationships(Pageable pageable) {
        return providerConfigurationRepository.findAllWithEagerRelationships(pageable).map(providerConfigurationMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProviderConfigurationDTO> findOne(Long id) {
        log.debug("Request to get ProviderConfiguration : {}", id);
        return providerConfigurationRepository.findOneWithEagerRelationships(id).map(providerConfigurationMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ProviderConfiguration : {}", id);
        providerConfigurationRepository.deleteById(id);
    }
}
