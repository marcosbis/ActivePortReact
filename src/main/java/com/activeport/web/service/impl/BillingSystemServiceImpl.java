package com.activeport.web.service.impl;

import com.activeport.web.domain.BillingSystem;
import com.activeport.web.repository.BillingSystemRepository;
import com.activeport.web.service.BillingSystemService;
import com.activeport.web.service.dto.BillingSystemDTO;
import com.activeport.web.service.mapper.BillingSystemMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link BillingSystem}.
 */
@Service
@Transactional
public class BillingSystemServiceImpl implements BillingSystemService {
    private final Logger log = LoggerFactory.getLogger(BillingSystemServiceImpl.class);

    private final BillingSystemRepository billingSystemRepository;

    private final BillingSystemMapper billingSystemMapper;

    public BillingSystemServiceImpl(BillingSystemRepository billingSystemRepository, BillingSystemMapper billingSystemMapper) {
        this.billingSystemRepository = billingSystemRepository;
        this.billingSystemMapper = billingSystemMapper;
    }

    @Override
    public BillingSystemDTO save(BillingSystemDTO billingSystemDTO) {
        log.debug("Request to save BillingSystem : {}", billingSystemDTO);
        BillingSystem billingSystem = billingSystemMapper.toEntity(billingSystemDTO);
        billingSystem = billingSystemRepository.save(billingSystem);
        return billingSystemMapper.toDto(billingSystem);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BillingSystemDTO> findAll(Pageable pageable) {
        log.debug("Request to get all BillingSystems");
        return billingSystemRepository.findAll(pageable).map(billingSystemMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<BillingSystemDTO> findOne(Long id) {
        log.debug("Request to get BillingSystem : {}", id);
        return billingSystemRepository.findById(id).map(billingSystemMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete BillingSystem : {}", id);
        billingSystemRepository.deleteById(id);
    }
}
