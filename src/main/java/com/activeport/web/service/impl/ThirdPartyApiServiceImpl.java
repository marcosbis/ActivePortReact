package com.activeport.web.service.impl;

import com.activeport.web.domain.ThirdPartyApi;
import com.activeport.web.repository.ThirdPartyApiRepository;
import com.activeport.web.service.ThirdPartyApiService;
import com.activeport.web.service.dto.ThirdPartyApiDTO;
import com.activeport.web.service.mapper.ThirdPartyApiMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ThirdPartyApi}.
 */
@Service
@Transactional
public class ThirdPartyApiServiceImpl implements ThirdPartyApiService {
    private final Logger log = LoggerFactory.getLogger(ThirdPartyApiServiceImpl.class);

    private final ThirdPartyApiRepository thirdPartyApiRepository;

    private final ThirdPartyApiMapper thirdPartyApiMapper;

    public ThirdPartyApiServiceImpl(ThirdPartyApiRepository thirdPartyApiRepository, ThirdPartyApiMapper thirdPartyApiMapper) {
        this.thirdPartyApiRepository = thirdPartyApiRepository;
        this.thirdPartyApiMapper = thirdPartyApiMapper;
    }

    @Override
    public ThirdPartyApiDTO save(ThirdPartyApiDTO thirdPartyApiDTO) {
        log.debug("Request to save ThirdPartyApi : {}", thirdPartyApiDTO);
        ThirdPartyApi thirdPartyApi = thirdPartyApiMapper.toEntity(thirdPartyApiDTO);
        thirdPartyApi = thirdPartyApiRepository.save(thirdPartyApi);
        return thirdPartyApiMapper.toDto(thirdPartyApi);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ThirdPartyApiDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ThirdPartyApis");
        return thirdPartyApiRepository.findAll(pageable).map(thirdPartyApiMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ThirdPartyApiDTO> findOne(Long id) {
        log.debug("Request to get ThirdPartyApi : {}", id);
        return thirdPartyApiRepository.findById(id).map(thirdPartyApiMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ThirdPartyApi : {}", id);
        thirdPartyApiRepository.deleteById(id);
    }
}
