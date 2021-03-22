package com.activeport.web.service.impl;

import com.activeport.web.domain.Ntu;
import com.activeport.web.repository.NtuRepository;
import com.activeport.web.service.NtuService;
import com.activeport.web.service.dto.NtuDTO;
import com.activeport.web.service.mapper.NtuMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Ntu}.
 */
@Service
@Transactional
public class NtuServiceImpl implements NtuService {
    private final Logger log = LoggerFactory.getLogger(NtuServiceImpl.class);

    private final NtuRepository ntuRepository;

    private final NtuMapper ntuMapper;

    public NtuServiceImpl(NtuRepository ntuRepository, NtuMapper ntuMapper) {
        this.ntuRepository = ntuRepository;
        this.ntuMapper = ntuMapper;
    }

    @Override
    public NtuDTO save(NtuDTO ntuDTO) {
        log.debug("Request to save Ntu : {}", ntuDTO);
        Ntu ntu = ntuMapper.toEntity(ntuDTO);
        ntu = ntuRepository.save(ntu);
        return ntuMapper.toDto(ntu);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<NtuDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Ntus");
        return ntuRepository.findAll(pageable).map(ntuMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<NtuDTO> findOne(Long id) {
        log.debug("Request to get Ntu : {}", id);
        return ntuRepository.findById(id).map(ntuMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Ntu : {}", id);
        ntuRepository.deleteById(id);
    }
}
