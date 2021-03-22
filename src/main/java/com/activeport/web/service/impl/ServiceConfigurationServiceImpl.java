package com.activeport.web.service.impl;

import com.activeport.web.domain.ServiceConfiguration;
import com.activeport.web.repository.ServiceConfigurationRepository;
import com.activeport.web.service.ServiceConfigurationService;
import com.activeport.web.service.dto.ServiceConfigurationDTO;
import com.activeport.web.service.mapper.ServiceConfigurationMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ServiceConfiguration}.
 */
@Service
@Transactional
public class ServiceConfigurationServiceImpl implements ServiceConfigurationService {
    private final Logger log = LoggerFactory.getLogger(ServiceConfigurationServiceImpl.class);

    private final ServiceConfigurationRepository serviceConfigurationRepository;

    private final ServiceConfigurationMapper serviceConfigurationMapper;

    public ServiceConfigurationServiceImpl(
        ServiceConfigurationRepository serviceConfigurationRepository,
        ServiceConfigurationMapper serviceConfigurationMapper
    ) {
        this.serviceConfigurationRepository = serviceConfigurationRepository;
        this.serviceConfigurationMapper = serviceConfigurationMapper;
    }

    @Override
    public ServiceConfigurationDTO save(ServiceConfigurationDTO serviceConfigurationDTO) {
        log.debug("Request to save ServiceConfiguration : {}", serviceConfigurationDTO);
        ServiceConfiguration serviceConfiguration = serviceConfigurationMapper.toEntity(serviceConfigurationDTO);
        serviceConfiguration = serviceConfigurationRepository.save(serviceConfiguration);
        return serviceConfigurationMapper.toDto(serviceConfiguration);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ServiceConfigurationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ServiceConfigurations");
        return serviceConfigurationRepository.findAll(pageable).map(serviceConfigurationMapper::toDto);
    }

    public Page<ServiceConfigurationDTO> findAllWithEagerRelationships(Pageable pageable) {
        return serviceConfigurationRepository.findAllWithEagerRelationships(pageable).map(serviceConfigurationMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ServiceConfigurationDTO> findOne(Long id) {
        log.debug("Request to get ServiceConfiguration : {}", id);
        return serviceConfigurationRepository.findOneWithEagerRelationships(id).map(serviceConfigurationMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ServiceConfiguration : {}", id);
        serviceConfigurationRepository.deleteById(id);
    }
}
