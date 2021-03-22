package com.activeport.web.service.impl;

import com.activeport.web.domain.VntuDownlinkPort;
import com.activeport.web.repository.VntuDownlinkPortRepository;
import com.activeport.web.service.VntuDownlinkPortService;
import com.activeport.web.service.dto.VntuDownlinkPortDTO;
import com.activeport.web.service.mapper.VntuDownlinkPortMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link VntuDownlinkPort}.
 */
@Service
@Transactional
public class VntuDownlinkPortServiceImpl implements VntuDownlinkPortService {
    private final Logger log = LoggerFactory.getLogger(VntuDownlinkPortServiceImpl.class);

    private final VntuDownlinkPortRepository vntuDownlinkPortRepository;

    private final VntuDownlinkPortMapper vntuDownlinkPortMapper;

    public VntuDownlinkPortServiceImpl(
        VntuDownlinkPortRepository vntuDownlinkPortRepository,
        VntuDownlinkPortMapper vntuDownlinkPortMapper
    ) {
        this.vntuDownlinkPortRepository = vntuDownlinkPortRepository;
        this.vntuDownlinkPortMapper = vntuDownlinkPortMapper;
    }

    @Override
    public VntuDownlinkPortDTO save(VntuDownlinkPortDTO vntuDownlinkPortDTO) {
        log.debug("Request to save VntuDownlinkPort : {}", vntuDownlinkPortDTO);
        VntuDownlinkPort vntuDownlinkPort = vntuDownlinkPortMapper.toEntity(vntuDownlinkPortDTO);
        vntuDownlinkPort = vntuDownlinkPortRepository.save(vntuDownlinkPort);
        return vntuDownlinkPortMapper.toDto(vntuDownlinkPort);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<VntuDownlinkPortDTO> findAll(Pageable pageable) {
        log.debug("Request to get all VntuDownlinkPorts");
        return vntuDownlinkPortRepository.findAll(pageable).map(vntuDownlinkPortMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<VntuDownlinkPortDTO> findOne(Long id) {
        log.debug("Request to get VntuDownlinkPort : {}", id);
        return vntuDownlinkPortRepository.findById(id).map(vntuDownlinkPortMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete VntuDownlinkPort : {}", id);
        vntuDownlinkPortRepository.deleteById(id);
    }
}
