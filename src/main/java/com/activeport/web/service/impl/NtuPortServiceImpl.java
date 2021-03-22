package com.activeport.web.service.impl;

import com.activeport.web.domain.NtuPort;
import com.activeport.web.repository.NtuPortRepository;
import com.activeport.web.service.NtuPortService;
import com.activeport.web.service.dto.NtuPortDTO;
import com.activeport.web.service.mapper.NtuPortMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link NtuPort}.
 */
@Service
@Transactional
public class NtuPortServiceImpl implements NtuPortService {
    private final Logger log = LoggerFactory.getLogger(NtuPortServiceImpl.class);

    private final NtuPortRepository ntuPortRepository;

    private final NtuPortMapper ntuPortMapper;

    public NtuPortServiceImpl(NtuPortRepository ntuPortRepository, NtuPortMapper ntuPortMapper) {
        this.ntuPortRepository = ntuPortRepository;
        this.ntuPortMapper = ntuPortMapper;
    }

    @Override
    public NtuPortDTO save(NtuPortDTO ntuPortDTO) {
        log.debug("Request to save NtuPort : {}", ntuPortDTO);
        NtuPort ntuPort = ntuPortMapper.toEntity(ntuPortDTO);
        ntuPort = ntuPortRepository.save(ntuPort);
        return ntuPortMapper.toDto(ntuPort);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<NtuPortDTO> findAll(Pageable pageable) {
        log.debug("Request to get all NtuPorts");
        return ntuPortRepository.findAll(pageable).map(ntuPortMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<NtuPortDTO> findOne(Long id) {
        log.debug("Request to get NtuPort : {}", id);
        return ntuPortRepository.findById(id).map(ntuPortMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete NtuPort : {}", id);
        ntuPortRepository.deleteById(id);
    }
}
