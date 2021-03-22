package com.activeport.web.service.impl;

import com.activeport.web.domain.PolicerSchedule;
import com.activeport.web.repository.PolicerScheduleRepository;
import com.activeport.web.service.PolicerScheduleService;
import com.activeport.web.service.dto.PolicerScheduleDTO;
import com.activeport.web.service.mapper.PolicerScheduleMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link PolicerSchedule}.
 */
@Service
@Transactional
public class PolicerScheduleServiceImpl implements PolicerScheduleService {
    private final Logger log = LoggerFactory.getLogger(PolicerScheduleServiceImpl.class);

    private final PolicerScheduleRepository policerScheduleRepository;

    private final PolicerScheduleMapper policerScheduleMapper;

    public PolicerScheduleServiceImpl(PolicerScheduleRepository policerScheduleRepository, PolicerScheduleMapper policerScheduleMapper) {
        this.policerScheduleRepository = policerScheduleRepository;
        this.policerScheduleMapper = policerScheduleMapper;
    }

    @Override
    public PolicerScheduleDTO save(PolicerScheduleDTO policerScheduleDTO) {
        log.debug("Request to save PolicerSchedule : {}", policerScheduleDTO);
        PolicerSchedule policerSchedule = policerScheduleMapper.toEntity(policerScheduleDTO);
        policerSchedule = policerScheduleRepository.save(policerSchedule);
        return policerScheduleMapper.toDto(policerSchedule);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PolicerScheduleDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PolicerSchedules");
        return policerScheduleRepository.findAll(pageable).map(policerScheduleMapper::toDto);
    }

    public Page<PolicerScheduleDTO> findAllWithEagerRelationships(Pageable pageable) {
        return policerScheduleRepository.findAllWithEagerRelationships(pageable).map(policerScheduleMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PolicerScheduleDTO> findOne(Long id) {
        log.debug("Request to get PolicerSchedule : {}", id);
        return policerScheduleRepository.findOneWithEagerRelationships(id).map(policerScheduleMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete PolicerSchedule : {}", id);
        policerScheduleRepository.deleteById(id);
    }
}
