package com.activeport.web.service.impl;

import com.activeport.web.domain.NtuConfig;
import com.activeport.web.repository.NtuConfigRepository;
import com.activeport.web.service.NtuConfigService;
import com.activeport.web.service.dto.NtuConfigDTO;
import com.activeport.web.service.mapper.NtuConfigMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link NtuConfig}.
 */
@Service
@Transactional
public class NtuConfigServiceImpl implements NtuConfigService {
    private final Logger log = LoggerFactory.getLogger(NtuConfigServiceImpl.class);

    private final NtuConfigRepository ntuConfigRepository;

    private final NtuConfigMapper ntuConfigMapper;

    public NtuConfigServiceImpl(NtuConfigRepository ntuConfigRepository, NtuConfigMapper ntuConfigMapper) {
        this.ntuConfigRepository = ntuConfigRepository;
        this.ntuConfigMapper = ntuConfigMapper;
    }

    @Override
    public NtuConfigDTO save(NtuConfigDTO ntuConfigDTO) {
        log.debug("Request to save NtuConfig : {}", ntuConfigDTO);
        NtuConfig ntuConfig = ntuConfigMapper.toEntity(ntuConfigDTO);
        ntuConfig = ntuConfigRepository.save(ntuConfig);
        return ntuConfigMapper.toDto(ntuConfig);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<NtuConfigDTO> findAll(Pageable pageable) {
        log.debug("Request to get all NtuConfigs");
        return ntuConfigRepository.findAll(pageable).map(ntuConfigMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<NtuConfigDTO> findOne(Long id) {
        log.debug("Request to get NtuConfig : {}", id);
        return ntuConfigRepository.findById(id).map(ntuConfigMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete NtuConfig : {}", id);
        ntuConfigRepository.deleteById(id);
    }
}
