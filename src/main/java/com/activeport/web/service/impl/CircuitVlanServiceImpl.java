package com.activeport.web.service.impl;

import com.activeport.web.domain.CircuitVlan;
import com.activeport.web.repository.CircuitVlanRepository;
import com.activeport.web.service.CircuitVlanService;
import com.activeport.web.service.dto.CircuitVlanDTO;
import com.activeport.web.service.mapper.CircuitVlanMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link CircuitVlan}.
 */
@Service
@Transactional
public class CircuitVlanServiceImpl implements CircuitVlanService {
    private final Logger log = LoggerFactory.getLogger(CircuitVlanServiceImpl.class);

    private final CircuitVlanRepository circuitVlanRepository;

    private final CircuitVlanMapper circuitVlanMapper;

    public CircuitVlanServiceImpl(CircuitVlanRepository circuitVlanRepository, CircuitVlanMapper circuitVlanMapper) {
        this.circuitVlanRepository = circuitVlanRepository;
        this.circuitVlanMapper = circuitVlanMapper;
    }

    @Override
    public CircuitVlanDTO save(CircuitVlanDTO circuitVlanDTO) {
        log.debug("Request to save CircuitVlan : {}", circuitVlanDTO);
        CircuitVlan circuitVlan = circuitVlanMapper.toEntity(circuitVlanDTO);
        circuitVlan = circuitVlanRepository.save(circuitVlan);
        return circuitVlanMapper.toDto(circuitVlan);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CircuitVlanDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CircuitVlans");
        return circuitVlanRepository.findAll(pageable).map(circuitVlanMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CircuitVlanDTO> findOne(Long id) {
        log.debug("Request to get CircuitVlan : {}", id);
        return circuitVlanRepository.findById(id).map(circuitVlanMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete CircuitVlan : {}", id);
        circuitVlanRepository.deleteById(id);
    }
}
