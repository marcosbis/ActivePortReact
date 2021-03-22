package com.activeport.web.service.mapper;

import com.activeport.web.domain.*;
import com.activeport.web.service.dto.NotificationEventDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link NotificationEvent} and its DTO {@link NotificationEventDTO}.
 */
@Mapper(componentModel = "spring", uses = { ConfigJobMapper.class })
public interface NotificationEventMapper extends EntityMapper<NotificationEventDTO, NotificationEvent> {
    @Mapping(source = "job.id", target = "jobId")
    NotificationEventDTO toDto(NotificationEvent notificationEvent);

    @Mapping(source = "jobId", target = "job")
    NotificationEvent toEntity(NotificationEventDTO notificationEventDTO);

    default NotificationEvent fromId(Long id) {
        if (id == null) {
            return null;
        }
        NotificationEvent notificationEvent = new NotificationEvent();
        notificationEvent.setId(id);
        return notificationEvent;
    }
}
