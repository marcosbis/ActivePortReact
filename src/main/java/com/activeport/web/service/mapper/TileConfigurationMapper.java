package com.activeport.web.service.mapper;

import com.activeport.web.domain.*;
import com.activeport.web.service.dto.TileConfigurationDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TileConfiguration} and its DTO {@link TileConfigurationDTO}.
 */
@Mapper(componentModel = "spring", uses = { ServiceTypeMapper.class, ServiceConfigurationMapper.class })
public interface TileConfigurationMapper extends EntityMapper<TileConfigurationDTO, TileConfiguration> {
    @Mapping(source = "serviceType.id", target = "serviceTypeId")
    @Mapping(source = "serviceType.code", target = "serviceTypeCode")
    TileConfigurationDTO toDto(TileConfiguration tileConfiguration);

    @Mapping(source = "serviceTypeId", target = "serviceType")
    @Mapping(target = "removeServices", ignore = true)
    TileConfiguration toEntity(TileConfigurationDTO tileConfigurationDTO);

    default TileConfiguration fromId(Long id) {
        if (id == null) {
            return null;
        }
        TileConfiguration tileConfiguration = new TileConfiguration();
        tileConfiguration.setId(id);
        return tileConfiguration;
    }
}
