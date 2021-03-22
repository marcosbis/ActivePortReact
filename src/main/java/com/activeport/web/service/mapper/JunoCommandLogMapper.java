package com.activeport.web.service.mapper;

import com.activeport.web.domain.*;
import com.activeport.web.service.dto.JunoCommandLogDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link JunoCommandLog} and its DTO {@link JunoCommandLogDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface JunoCommandLogMapper extends EntityMapper<JunoCommandLogDTO, JunoCommandLog> {
    default JunoCommandLog fromId(Long id) {
        if (id == null) {
            return null;
        }
        JunoCommandLog junoCommandLog = new JunoCommandLog();
        junoCommandLog.setId(id);
        return junoCommandLog;
    }
}
