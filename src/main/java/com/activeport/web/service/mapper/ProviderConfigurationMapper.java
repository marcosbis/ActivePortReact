package com.activeport.web.service.mapper;

import com.activeport.web.domain.*;
import com.activeport.web.service.dto.ProviderConfigurationDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProviderConfiguration} and its DTO {@link ProviderConfigurationDTO}.
 */
@Mapper(componentModel = "spring", uses = { ServiceTypeMapper.class })
public interface ProviderConfigurationMapper extends EntityMapper<ProviderConfigurationDTO, ProviderConfiguration> {
    @Mapping(target = "removeServices", ignore = true)
    default ProviderConfiguration fromId(Long id) {
        if (id == null) {
            return null;
        }
        ProviderConfiguration providerConfiguration = new ProviderConfiguration();
        providerConfiguration.setId(id);
        return providerConfiguration;
    }
}
