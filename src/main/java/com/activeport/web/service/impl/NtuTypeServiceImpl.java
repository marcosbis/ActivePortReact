package com.activeport.web.service.impl;

import com.activeport.web.domain.NtuType;
import com.activeport.web.repository.NtuTypeRepository;
import com.activeport.web.service.NtuTypeService;
import com.activeport.web.service.dto.NtuTypeDTO;
import com.activeport.web.service.mapper.NtuTypeMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link NtuType}.
 */
@Service
@Transactional
public class NtuTypeServiceImpl implements NtuTypeService {
    private final Logger log = LoggerFactory.getLogger(NtuTypeServiceImpl.class);

    private final NtuTypeRepository ntuTypeRepository;

    private final NtuTypeMapper ntuTypeMapper;

    public NtuTypeServiceImpl(NtuTypeRepository ntuTypeRepository, NtuTypeMapper ntuTypeMapper) {
        this.ntuTypeRepository = ntuTypeRepository;
        this.ntuTypeMapper = ntuTypeMapper;
    }

    @Override
    public NtuTypeDTO save(NtuTypeDTO ntuTypeDTO) {
        log.debug("Request to save NtuType : {}", ntuTypeDTO);
        NtuType ntuType = ntuTypeMapper.toEntity(ntuTypeDTO);
        ntuType = ntuTypeRepository.save(ntuType);
        return ntuTypeMapper.toDto(ntuType);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<NtuTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all NtuTypes");
        return ntuTypeRepository.findAll(pageable).map(ntuTypeMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<NtuTypeDTO> findOne(Long id) {
        log.debug("Request to get NtuType : {}", id);
        return ntuTypeRepository.findById(id).map(ntuTypeMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete NtuType : {}", id);
        ntuTypeRepository.deleteById(id);
    }
}
