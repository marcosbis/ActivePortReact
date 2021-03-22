package com.activeport.web.service.impl;

import com.activeport.web.domain.ProviderLog;
import com.activeport.web.repository.ProviderLogRepository;
import com.activeport.web.service.ProviderLogService;
import com.activeport.web.service.dto.ProviderLogDTO;
import com.activeport.web.service.mapper.ProviderLogMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ProviderLog}.
 */
@Service
@Transactional
public class ProviderLogServiceImpl implements ProviderLogService {
    private final Logger log = LoggerFactory.getLogger(ProviderLogServiceImpl.class);

    private final ProviderLogRepository providerLogRepository;

    private final ProviderLogMapper providerLogMapper;

    public ProviderLogServiceImpl(ProviderLogRepository providerLogRepository, ProviderLogMapper providerLogMapper) {
        this.providerLogRepository = providerLogRepository;
        this.providerLogMapper = providerLogMapper;
    }

    @Override
    public ProviderLogDTO save(ProviderLogDTO providerLogDTO) {
        log.debug("Request to save ProviderLog : {}", providerLogDTO);
        ProviderLog providerLog = providerLogMapper.toEntity(providerLogDTO);
        providerLog = providerLogRepository.save(providerLog);
        return providerLogMapper.toDto(providerLog);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProviderLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ProviderLogs");
        return providerLogRepository.findAll(pageable).map(providerLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProviderLogDTO> findOne(Long id) {
        log.debug("Request to get ProviderLog : {}", id);
        return providerLogRepository.findById(id).map(providerLogMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ProviderLog : {}", id);
        providerLogRepository.deleteById(id);
    }
}
