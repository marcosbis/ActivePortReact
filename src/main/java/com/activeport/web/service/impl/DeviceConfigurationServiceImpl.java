package com.activeport.web.service.impl;

import com.activeport.web.domain.DeviceConfiguration;
import com.activeport.web.repository.DeviceConfigurationRepository;
import com.activeport.web.service.DeviceConfigurationService;
import com.activeport.web.service.dto.DeviceConfigurationDTO;
import com.activeport.web.service.mapper.DeviceConfigurationMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link DeviceConfiguration}.
 */
@Service
@Transactional
public class DeviceConfigurationServiceImpl implements DeviceConfigurationService {
    private final Logger log = LoggerFactory.getLogger(DeviceConfigurationServiceImpl.class);

    private final DeviceConfigurationRepository deviceConfigurationRepository;

    private final DeviceConfigurationMapper deviceConfigurationMapper;

    public DeviceConfigurationServiceImpl(
        DeviceConfigurationRepository deviceConfigurationRepository,
        DeviceConfigurationMapper deviceConfigurationMapper
    ) {
        this.deviceConfigurationRepository = deviceConfigurationRepository;
        this.deviceConfigurationMapper = deviceConfigurationMapper;
    }

    @Override
    public DeviceConfigurationDTO save(DeviceConfigurationDTO deviceConfigurationDTO) {
        log.debug("Request to save DeviceConfiguration : {}", deviceConfigurationDTO);
        DeviceConfiguration deviceConfiguration = deviceConfigurationMapper.toEntity(deviceConfigurationDTO);
        deviceConfiguration = deviceConfigurationRepository.save(deviceConfiguration);
        return deviceConfigurationMapper.toDto(deviceConfiguration);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DeviceConfigurationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DeviceConfigurations");
        return deviceConfigurationRepository.findAll(pageable).map(deviceConfigurationMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DeviceConfigurationDTO> findOne(Long id) {
        log.debug("Request to get DeviceConfiguration : {}", id);
        return deviceConfigurationRepository.findById(id).map(deviceConfigurationMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete DeviceConfiguration : {}", id);
        deviceConfigurationRepository.deleteById(id);
    }
}
