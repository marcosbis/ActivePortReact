package com.activeport.web.service.mapper;

import com.activeport.web.domain.*;
import com.activeport.web.service.dto.TemplateConfigurationDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TemplateConfiguration} and its DTO {@link TemplateConfigurationDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TemplateConfigurationMapper extends EntityMapper<TemplateConfigurationDTO, TemplateConfiguration> {
    default TemplateConfiguration fromId(Long id) {
        if (id == null) {
            return null;
        }
        TemplateConfiguration templateConfiguration = new TemplateConfiguration();
        templateConfiguration.setId(id);
        return templateConfiguration;
    }
}
