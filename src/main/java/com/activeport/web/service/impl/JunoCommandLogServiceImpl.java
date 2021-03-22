package com.activeport.web.service.impl;

import com.activeport.web.domain.JunoCommandLog;
import com.activeport.web.repository.JunoCommandLogRepository;
import com.activeport.web.service.JunoCommandLogService;
import com.activeport.web.service.dto.JunoCommandLogDTO;
import com.activeport.web.service.mapper.JunoCommandLogMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link JunoCommandLog}.
 */
@Service
@Transactional
public class JunoCommandLogServiceImpl implements JunoCommandLogService {
    private final Logger log = LoggerFactory.getLogger(JunoCommandLogServiceImpl.class);

    private final JunoCommandLogRepository junoCommandLogRepository;

    private final JunoCommandLogMapper junoCommandLogMapper;

    public JunoCommandLogServiceImpl(JunoCommandLogRepository junoCommandLogRepository, JunoCommandLogMapper junoCommandLogMapper) {
        this.junoCommandLogRepository = junoCommandLogRepository;
        this.junoCommandLogMapper = junoCommandLogMapper;
    }

    @Override
    public JunoCommandLogDTO save(JunoCommandLogDTO junoCommandLogDTO) {
        log.debug("Request to save JunoCommandLog : {}", junoCommandLogDTO);
        JunoCommandLog junoCommandLog = junoCommandLogMapper.toEntity(junoCommandLogDTO);
        junoCommandLog = junoCommandLogRepository.save(junoCommandLog);
        return junoCommandLogMapper.toDto(junoCommandLog);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<JunoCommandLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all JunoCommandLogs");
        return junoCommandLogRepository.findAll(pageable).map(junoCommandLogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<JunoCommandLogDTO> findOne(Long id) {
        log.debug("Request to get JunoCommandLog : {}", id);
        return junoCommandLogRepository.findById(id).map(junoCommandLogMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete JunoCommandLog : {}", id);
        junoCommandLogRepository.deleteById(id);
    }
}
