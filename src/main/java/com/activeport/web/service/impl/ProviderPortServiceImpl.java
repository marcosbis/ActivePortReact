package com.activeport.web.service.impl;

import com.activeport.web.domain.ProviderPort;
import com.activeport.web.repository.ProviderPortRepository;
import com.activeport.web.service.ProviderPortService;
import com.activeport.web.service.dto.ProviderPortDTO;
import com.activeport.web.service.mapper.ProviderPortMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ProviderPort}.
 */
@Service
@Transactional
public class ProviderPortServiceImpl implements ProviderPortService {
    private final Logger log = LoggerFactory.getLogger(ProviderPortServiceImpl.class);

    private final ProviderPortRepository providerPortRepository;

    private final ProviderPortMapper providerPortMapper;

    public ProviderPortServiceImpl(ProviderPortRepository providerPortRepository, ProviderPortMapper providerPortMapper) {
        this.providerPortRepository = providerPortRepository;
        this.providerPortMapper = providerPortMapper;
    }

    @Override
    public ProviderPortDTO save(ProviderPortDTO providerPortDTO) {
        log.debug("Request to save ProviderPort : {}", providerPortDTO);
        ProviderPort providerPort = providerPortMapper.toEntity(providerPortDTO);
        providerPort = providerPortRepository.save(providerPort);
        return providerPortMapper.toDto(providerPort);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProviderPortDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ProviderPorts");
        return providerPortRepository.findAll(pageable).map(providerPortMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProviderPortDTO> findOne(Long id) {
        log.debug("Request to get ProviderPort : {}", id);
        return providerPortRepository.findById(id).map(providerPortMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ProviderPort : {}", id);
        providerPortRepository.deleteById(id);
    }
}
