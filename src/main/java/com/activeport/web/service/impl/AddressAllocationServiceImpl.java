package com.activeport.web.service.impl;

import com.activeport.web.domain.AddressAllocation;
import com.activeport.web.repository.AddressAllocationRepository;
import com.activeport.web.service.AddressAllocationService;
import com.activeport.web.service.dto.AddressAllocationDTO;
import com.activeport.web.service.mapper.AddressAllocationMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link AddressAllocation}.
 */
@Service
@Transactional
public class AddressAllocationServiceImpl implements AddressAllocationService {
    private final Logger log = LoggerFactory.getLogger(AddressAllocationServiceImpl.class);

    private final AddressAllocationRepository addressAllocationRepository;

    private final AddressAllocationMapper addressAllocationMapper;

    public AddressAllocationServiceImpl(
        AddressAllocationRepository addressAllocationRepository,
        AddressAllocationMapper addressAllocationMapper
    ) {
        this.addressAllocationRepository = addressAllocationRepository;
        this.addressAllocationMapper = addressAllocationMapper;
    }

    @Override
    public AddressAllocationDTO save(AddressAllocationDTO addressAllocationDTO) {
        log.debug("Request to save AddressAllocation : {}", addressAllocationDTO);
        AddressAllocation addressAllocation = addressAllocationMapper.toEntity(addressAllocationDTO);
        addressAllocation = addressAllocationRepository.save(addressAllocation);
        return addressAllocationMapper.toDto(addressAllocation);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AddressAllocationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AddressAllocations");
        return addressAllocationRepository.findAll(pageable).map(addressAllocationMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AddressAllocationDTO> findOne(Long id) {
        log.debug("Request to get AddressAllocation : {}", id);
        return addressAllocationRepository.findById(id).map(addressAllocationMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete AddressAllocation : {}", id);
        addressAllocationRepository.deleteById(id);
    }
}
