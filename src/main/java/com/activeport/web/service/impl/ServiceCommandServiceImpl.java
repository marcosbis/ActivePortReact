package com.activeport.web.service.impl;

import com.activeport.web.domain.ServiceCommand;
import com.activeport.web.repository.ServiceCommandRepository;
import com.activeport.web.service.ServiceCommandService;
import com.activeport.web.service.dto.ServiceCommandDTO;
import com.activeport.web.service.mapper.ServiceCommandMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ServiceCommand}.
 */
@Service
@Transactional
public class ServiceCommandServiceImpl implements ServiceCommandService {
    private final Logger log = LoggerFactory.getLogger(ServiceCommandServiceImpl.class);

    private final ServiceCommandRepository serviceCommandRepository;

    private final ServiceCommandMapper serviceCommandMapper;

    public ServiceCommandServiceImpl(ServiceCommandRepository serviceCommandRepository, ServiceCommandMapper serviceCommandMapper) {
        this.serviceCommandRepository = serviceCommandRepository;
        this.serviceCommandMapper = serviceCommandMapper;
    }

    @Override
    public ServiceCommandDTO save(ServiceCommandDTO serviceCommandDTO) {
        log.debug("Request to save ServiceCommand : {}", serviceCommandDTO);
        ServiceCommand serviceCommand = serviceCommandMapper.toEntity(serviceCommandDTO);
        serviceCommand = serviceCommandRepository.save(serviceCommand);
        return serviceCommandMapper.toDto(serviceCommand);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ServiceCommandDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ServiceCommands");
        return serviceCommandRepository.findAll(pageable).map(serviceCommandMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ServiceCommandDTO> findOne(Long id) {
        log.debug("Request to get ServiceCommand : {}", id);
        return serviceCommandRepository.findById(id).map(serviceCommandMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ServiceCommand : {}", id);
        serviceCommandRepository.deleteById(id);
    }
}
