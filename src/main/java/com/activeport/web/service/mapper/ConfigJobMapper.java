package com.activeport.web.service.mapper;

import com.activeport.web.domain.*;
import com.activeport.web.service.dto.ConfigJobDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ConfigJob} and its DTO {@link ConfigJobDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ConfigJobMapper extends EntityMapper<ConfigJobDTO, ConfigJob> {
    default ConfigJob fromId(Long id) {
        if (id == null) {
            return null;
        }
        ConfigJob configJob = new ConfigJob();
        configJob.setId(id);
        return configJob;
    }
}
