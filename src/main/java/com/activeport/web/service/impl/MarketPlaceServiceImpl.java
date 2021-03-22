package com.activeport.web.service.impl;

import com.activeport.web.domain.MarketPlace;
import com.activeport.web.repository.MarketPlaceRepository;
import com.activeport.web.service.MarketPlaceService;
import com.activeport.web.service.dto.MarketPlaceDTO;
import com.activeport.web.service.mapper.MarketPlaceMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link MarketPlace}.
 */
@Service
@Transactional
public class MarketPlaceServiceImpl implements MarketPlaceService {
    private final Logger log = LoggerFactory.getLogger(MarketPlaceServiceImpl.class);

    private final MarketPlaceRepository marketPlaceRepository;

    private final MarketPlaceMapper marketPlaceMapper;

    public MarketPlaceServiceImpl(MarketPlaceRepository marketPlaceRepository, MarketPlaceMapper marketPlaceMapper) {
        this.marketPlaceRepository = marketPlaceRepository;
        this.marketPlaceMapper = marketPlaceMapper;
    }

    @Override
    public MarketPlaceDTO save(MarketPlaceDTO marketPlaceDTO) {
        log.debug("Request to save MarketPlace : {}", marketPlaceDTO);
        MarketPlace marketPlace = marketPlaceMapper.toEntity(marketPlaceDTO);
        marketPlace = marketPlaceRepository.save(marketPlace);
        return marketPlaceMapper.toDto(marketPlace);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<MarketPlaceDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MarketPlaces");
        return marketPlaceRepository.findAll(pageable).map(marketPlaceMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<MarketPlaceDTO> findOne(Long id) {
        log.debug("Request to get MarketPlace : {}", id);
        return marketPlaceRepository.findById(id).map(marketPlaceMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete MarketPlace : {}", id);
        marketPlaceRepository.deleteById(id);
    }
}
