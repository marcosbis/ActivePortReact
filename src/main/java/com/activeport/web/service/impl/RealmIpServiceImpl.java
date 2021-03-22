package com.activeport.web.service.impl;

import com.activeport.web.domain.RealmIp;
import com.activeport.web.repository.RealmIpRepository;
import com.activeport.web.service.RealmIpService;
import com.activeport.web.service.dto.RealmIpDTO;
import com.activeport.web.service.mapper.RealmIpMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link RealmIp}.
 */
@Service
@Transactional
public class RealmIpServiceImpl implements RealmIpService {
    private final Logger log = LoggerFactory.getLogger(RealmIpServiceImpl.class);

    private final RealmIpRepository realmIpRepository;

    private final RealmIpMapper realmIpMapper;

    public RealmIpServiceImpl(RealmIpRepository realmIpRepository, RealmIpMapper realmIpMapper) {
        this.realmIpRepository = realmIpRepository;
        this.realmIpMapper = realmIpMapper;
    }

    @Override
    public RealmIpDTO save(RealmIpDTO realmIpDTO) {
        log.debug("Request to save RealmIp : {}", realmIpDTO);
        RealmIp realmIp = realmIpMapper.toEntity(realmIpDTO);
        realmIp = realmIpRepository.save(realmIp);
        return realmIpMapper.toDto(realmIp);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<RealmIpDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RealmIps");
        return realmIpRepository.findAll(pageable).map(realmIpMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RealmIpDTO> findOne(Long id) {
        log.debug("Request to get RealmIp : {}", id);
        return realmIpRepository.findById(id).map(realmIpMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete RealmIp : {}", id);
        realmIpRepository.deleteById(id);
    }
}
