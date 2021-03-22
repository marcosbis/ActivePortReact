package com.activeport.web.service.impl;

import com.activeport.web.domain.ItemCode;
import com.activeport.web.repository.ItemCodeRepository;
import com.activeport.web.service.ItemCodeService;
import com.activeport.web.service.dto.ItemCodeDTO;
import com.activeport.web.service.mapper.ItemCodeMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ItemCode}.
 */
@Service
@Transactional
public class ItemCodeServiceImpl implements ItemCodeService {
    private final Logger log = LoggerFactory.getLogger(ItemCodeServiceImpl.class);

    private final ItemCodeRepository itemCodeRepository;

    private final ItemCodeMapper itemCodeMapper;

    public ItemCodeServiceImpl(ItemCodeRepository itemCodeRepository, ItemCodeMapper itemCodeMapper) {
        this.itemCodeRepository = itemCodeRepository;
        this.itemCodeMapper = itemCodeMapper;
    }

    @Override
    public ItemCodeDTO save(ItemCodeDTO itemCodeDTO) {
        log.debug("Request to save ItemCode : {}", itemCodeDTO);
        ItemCode itemCode = itemCodeMapper.toEntity(itemCodeDTO);
        itemCode = itemCodeRepository.save(itemCode);
        return itemCodeMapper.toDto(itemCode);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ItemCodeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ItemCodes");
        return itemCodeRepository.findAll(pageable).map(itemCodeMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ItemCodeDTO> findOne(Long id) {
        log.debug("Request to get ItemCode : {}", id);
        return itemCodeRepository.findById(id).map(itemCodeMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ItemCode : {}", id);
        itemCodeRepository.deleteById(id);
    }
}
