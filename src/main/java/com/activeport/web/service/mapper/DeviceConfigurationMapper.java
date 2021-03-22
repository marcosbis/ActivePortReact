package com.activeport.web.service.mapper;

import com.activeport.web.domain.*;
import com.activeport.web.service.dto.DeviceConfigurationDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DeviceConfiguration} and its DTO {@link DeviceConfigurationDTO}.
 */
@Mapper(componentModel = "spring", uses = { NtuTypeMapper.class, TemplateConfigurationMapper.class, RealmIpMapper.class })
public interface DeviceConfigurationMapper extends EntityMapper<DeviceConfigurationDTO, DeviceConfiguration> {
    @Mapping(source = "ntutype.id", target = "ntutypeId")
    @Mapping(source = "ntutype.model", target = "ntutypeModel")
    @Mapping(source = "configuration.id", target = "configurationId")
    @Mapping(source = "configuration.name", target = "configurationName")
    @Mapping(source = "realm.id", target = "realmId")
    @Mapping(source = "realm.subnet", target = "realmSubnet")
    DeviceConfigurationDTO toDto(DeviceConfiguration deviceConfiguration);

    @Mapping(source = "ntutypeId", target = "ntutype")
    @Mapping(source = "configurationId", target = "configuration")
    @Mapping(source = "realmId", target = "realm")
    DeviceConfiguration toEntity(DeviceConfigurationDTO deviceConfigurationDTO);

    default DeviceConfiguration fromId(Long id) {
        if (id == null) {
            return null;
        }
        DeviceConfiguration deviceConfiguration = new DeviceConfiguration();
        deviceConfiguration.setId(id);
        return deviceConfiguration;
    }
}
