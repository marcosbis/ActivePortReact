package com.activeport.web.service.impl;

import com.activeport.web.domain.PolicerRange;
import com.activeport.web.repository.PolicerRangeRepository;
import com.activeport.web.service.PolicerRangeService;
import com.activeport.web.service.dto.PolicerRangeDTO;
import com.activeport.web.service.mapper.PolicerRangeMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link PolicerRange}.
 */
@Service
@Transactional
public class PolicerRangeServiceImpl implements PolicerRangeService {
    private final Logger log = LoggerFactory.getLogger(PolicerRangeServiceImpl.class);

    private final PolicerRangeRepository policerRangeRepository;

    private final PolicerRangeMapper policerRangeMapper;

    public PolicerRangeServiceImpl(PolicerRangeRepository policerRangeRepository, PolicerRangeMapper policerRangeMapper) {
        this.policerRangeRepository = policerRangeRepository;
        this.policerRangeMapper = policerRangeMapper;
    }

    @Override
    public PolicerRangeDTO save(PolicerRangeDTO policerRangeDTO) {
        log.debug("Request to save PolicerRange : {}", policerRangeDTO);
        PolicerRange policerRange = policerRangeMapper.toEntity(policerRangeDTO);
        policerRange = policerRangeRepository.save(policerRange);
        return policerRangeMapper.toDto(policerRange);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PolicerRangeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PolicerRanges");
        return policerRangeRepository.findAll(pageable).map(policerRangeMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PolicerRangeDTO> findOne(Long id) {
        log.debug("Request to get PolicerRange : {}", id);
        return policerRangeRepository.findById(id).map(policerRangeMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete PolicerRange : {}", id);
        policerRangeRepository.deleteById(id);
    }
}
