package com.activeport.web.service.impl;

import com.activeport.web.domain.ServiceType;
import com.activeport.web.repository.ServiceTypeRepository;
import com.activeport.web.service.ServiceTypeService;
import com.activeport.web.service.dto.ServiceTypeDTO;
import com.activeport.web.service.mapper.ServiceTypeMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ServiceType}.
 */
@Service
@Transactional
public class ServiceTypeServiceImpl implements ServiceTypeService {
    private final Logger log = LoggerFactory.getLogger(ServiceTypeServiceImpl.class);

    private final ServiceTypeRepository serviceTypeRepository;

    private final ServiceTypeMapper serviceTypeMapper;

    public ServiceTypeServiceImpl(ServiceTypeRepository serviceTypeRepository, ServiceTypeMapper serviceTypeMapper) {
        this.serviceTypeRepository = serviceTypeRepository;
        this.serviceTypeMapper = serviceTypeMapper;
    }

    @Override
    public ServiceTypeDTO save(ServiceTypeDTO serviceTypeDTO) {
        log.debug("Request to save ServiceType : {}", serviceTypeDTO);
        ServiceType serviceType = serviceTypeMapper.toEntity(serviceTypeDTO);
        serviceType = serviceTypeRepository.save(serviceType);
        return serviceTypeMapper.toDto(serviceType);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ServiceTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ServiceTypes");
        return serviceTypeRepository.findAll(pageable).map(serviceTypeMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ServiceTypeDTO> findOne(Long id) {
        log.debug("Request to get ServiceType : {}", id);
        return serviceTypeRepository.findById(id).map(serviceTypeMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ServiceType : {}", id);
        serviceTypeRepository.deleteById(id);
    }
}
