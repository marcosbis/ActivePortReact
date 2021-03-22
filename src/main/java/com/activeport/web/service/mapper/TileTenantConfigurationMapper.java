package com.activeport.web.service.mapper;

import com.activeport.web.domain.*;
import com.activeport.web.service.dto.TileTenantConfigurationDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TileTenantConfiguration} and its DTO {@link TileTenantConfigurationDTO}.
 */
@Mapper(componentModel = "spring", uses = { TileConfigurationMapper.class })
public interface TileTenantConfigurationMapper extends EntityMapper<TileTenantConfigurationDTO, TileTenantConfiguration> {
    @Mapping(source = "tileConfiguration.id", target = "tileConfigurationId")
    @Mapping(source = "tileConfiguration.name", target = "tileConfigurationName")
    TileTenantConfigurationDTO toDto(TileTenantConfiguration tileTenantConfiguration);

    @Mapping(source = "tileConfigurationId", target = "tileConfiguration")
    TileTenantConfiguration toEntity(TileTenantConfigurationDTO tileTenantConfigurationDTO);

    default TileTenantConfiguration fromId(Long id) {
        if (id == null) {
            return null;
        }
        TileTenantConfiguration tileTenantConfiguration = new TileTenantConfiguration();
        tileTenantConfiguration.setId(id);
        return tileTenantConfiguration;
    }
}
