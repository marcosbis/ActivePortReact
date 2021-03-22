package com.activeport.web.service.impl;

import com.activeport.web.domain.CentralSwitch;
import com.activeport.web.repository.CentralSwitchRepository;
import com.activeport.web.service.CentralSwitchService;
import com.activeport.web.service.dto.CentralSwitchDTO;
import com.activeport.web.service.mapper.CentralSwitchMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link CentralSwitch}.
 */
@Service
@Transactional
public class CentralSwitchServiceImpl implements CentralSwitchService {
    private final Logger log = LoggerFactory.getLogger(CentralSwitchServiceImpl.class);

    private final CentralSwitchRepository centralSwitchRepository;

    private final CentralSwitchMapper centralSwitchMapper;

    public CentralSwitchServiceImpl(CentralSwitchRepository centralSwitchRepository, CentralSwitchMapper centralSwitchMapper) {
        this.centralSwitchRepository = centralSwitchRepository;
        this.centralSwitchMapper = centralSwitchMapper;
    }

    @Override
    public CentralSwitchDTO save(CentralSwitchDTO centralSwitchDTO) {
        log.debug("Request to save CentralSwitch : {}", centralSwitchDTO);
        CentralSwitch centralSwitch = centralSwitchMapper.toEntity(centralSwitchDTO);
        centralSwitch = centralSwitchRepository.save(centralSwitch);
        return centralSwitchMapper.toDto(centralSwitch);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CentralSwitchDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CentralSwitches");
        return centralSwitchRepository.findAll(pageable).map(centralSwitchMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CentralSwitchDTO> findOne(Long id) {
        log.debug("Request to get CentralSwitch : {}", id);
        return centralSwitchRepository.findById(id).map(centralSwitchMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete CentralSwitch : {}", id);
        centralSwitchRepository.deleteById(id);
    }
}
