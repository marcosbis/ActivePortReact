package com.activeport.web.service.impl;

import com.activeport.web.domain.ServiceCode;
import com.activeport.web.repository.ServiceCodeRepository;
import com.activeport.web.service.ServiceCodeService;
import com.activeport.web.service.dto.ServiceCodeDTO;
import com.activeport.web.service.mapper.ServiceCodeMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ServiceCode}.
 */
@Service
@Transactional
public class ServiceCodeServiceImpl implements ServiceCodeService {
    private final Logger log = LoggerFactory.getLogger(ServiceCodeServiceImpl.class);

    private final ServiceCodeRepository serviceCodeRepository;

    private final ServiceCodeMapper serviceCodeMapper;

    public ServiceCodeServiceImpl(ServiceCodeRepository serviceCodeRepository, ServiceCodeMapper serviceCodeMapper) {
        this.serviceCodeRepository = serviceCodeRepository;
        this.serviceCodeMapper = serviceCodeMapper;
    }

    @Override
    public ServiceCodeDTO save(ServiceCodeDTO serviceCodeDTO) {
        log.debug("Request to save ServiceCode : {}", serviceCodeDTO);
        ServiceCode serviceCode = serviceCodeMapper.toEntity(serviceCodeDTO);
        serviceCode = serviceCodeRepository.save(serviceCode);
        return serviceCodeMapper.toDto(serviceCode);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ServiceCodeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ServiceCodes");
        return serviceCodeRepository.findAll(pageable).map(serviceCodeMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ServiceCodeDTO> findOne(Long id) {
        log.debug("Request to get ServiceCode : {}", id);
        return serviceCodeRepository.findById(id).map(serviceCodeMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ServiceCode : {}", id);
        serviceCodeRepository.deleteById(id);
    }
}
