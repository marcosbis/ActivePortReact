package com.activeport.web.service.impl;

import com.activeport.web.domain.NotificationEvent;
import com.activeport.web.repository.NotificationEventRepository;
import com.activeport.web.service.NotificationEventService;
import com.activeport.web.service.dto.NotificationEventDTO;
import com.activeport.web.service.mapper.NotificationEventMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link NotificationEvent}.
 */
@Service
@Transactional
public class NotificationEventServiceImpl implements NotificationEventService {
    private final Logger log = LoggerFactory.getLogger(NotificationEventServiceImpl.class);

    private final NotificationEventRepository notificationEventRepository;

    private final NotificationEventMapper notificationEventMapper;

    public NotificationEventServiceImpl(
        NotificationEventRepository notificationEventRepository,
        NotificationEventMapper notificationEventMapper
    ) {
        this.notificationEventRepository = notificationEventRepository;
        this.notificationEventMapper = notificationEventMapper;
    }

    @Override
    public NotificationEventDTO save(NotificationEventDTO notificationEventDTO) {
        log.debug("Request to save NotificationEvent : {}", notificationEventDTO);
        NotificationEvent notificationEvent = notificationEventMapper.toEntity(notificationEventDTO);
        notificationEvent = notificationEventRepository.save(notificationEvent);
        return notificationEventMapper.toDto(notificationEvent);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<NotificationEventDTO> findAll(Pageable pageable) {
        log.debug("Request to get all NotificationEvents");
        return notificationEventRepository.findAll(pageable).map(notificationEventMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<NotificationEventDTO> findOne(Long id) {
        log.debug("Request to get NotificationEvent : {}", id);
        return notificationEventRepository.findById(id).map(notificationEventMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete NotificationEvent : {}", id);
        notificationEventRepository.deleteById(id);
    }
}
