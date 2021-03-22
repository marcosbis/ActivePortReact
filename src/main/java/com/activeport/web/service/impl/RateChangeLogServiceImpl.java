package com.activeport.web.service.impl;

import com.activeport.web.domain.RateChangeLog;
import com.activeport.web.repository.RateChangeLogRepository;
import com.activeport.web.service.RateChangeLogService;
import com.activeport.web.service.dto.RateChangeLogDTO;
import com.activeport.web.service.mapper.RateChangeLogMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link RateChangeLog}.
 */
@Service
@Transactional
public class RateChangeLogServiceImpl implements RateChangeLogService {
    private final Logger log = LoggerFactory.getLogger(RateChangeLogServiceImpl.class);

    private final RateChangeLogRepository rateChangeLogRepository;

    private final RateChangeLogMapper rateChangeLogMapper;

    public RateChangeLogServiceImpl(RateChangeLogRepository rateChangeLogRepository, RateChangeLogMapper rateChangeLogMapper) {
        this.rateChangeLogRepository = rateChangeLogRepository;
        this.rateChangeLogMapper = rateChangeLogMapper;
    }

    @Override
    public RateChangeLogDTO save(RateChangeLogDTO rateChangeLogDTO) {
        log.debug("Request to save RateChangeLog : {}", rateChangeLogDTO);
        RateChangeLog rateChangeLog = rateChangeLogMapper.toEntity(rateChangeLogDTO);
        rateChangeLog = rateChangeLogRepository.save(rateChangeLog);
        return rateChangeLogMapper.toDto(rateChangeLog);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<RateChangeLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RateChangeLogs");
        return rateChangeLogRepository.findAll(pageable).map(rateChangeLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RateChangeLogDTO> findOne(Long id) {
        log.debug("Request to get RateChangeLog : {}", id);
        return rateChangeLogRepository.findById(id).map(rateChangeLogMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete RateChangeLog : {}", id);
        rateChangeLogRepository.deleteById(id);
    }
}
