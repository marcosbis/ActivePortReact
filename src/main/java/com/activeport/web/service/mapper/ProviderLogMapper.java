package com.activeport.web.service.mapper;

import com.activeport.web.domain.*;
import com.activeport.web.service.dto.ProviderLogDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProviderLog} and its DTO {@link ProviderLogDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProviderLogMapper extends EntityMapper<ProviderLogDTO, ProviderLog> {
    default ProviderLog fromId(Long id) {
        if (id == null) {
            return null;
        }
        ProviderLog providerLog = new ProviderLog();
        providerLog.setId(id);
        return providerLog;
    }
}
