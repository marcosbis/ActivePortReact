package com.activeport.web.service.mapper;

import com.activeport.web.domain.*;
import com.activeport.web.service.dto.CircuitVlanDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CircuitVlan} and its DTO {@link CircuitVlanDTO}.
 */
@Mapper(componentModel = "spring", uses = { ServiceConfigurationMapper.class })
public interface CircuitVlanMapper extends EntityMapper<CircuitVlanDTO, CircuitVlan> {
    @Mapping(source = "serviceConfiguration.id", target = "serviceConfigurationId")
    @Mapping(source = "serviceConfiguration.name", target = "serviceConfigurationName")
    CircuitVlanDTO toDto(CircuitVlan circuitVlan);

    @Mapping(source = "serviceConfigurationId", target = "serviceConfiguration")
    CircuitVlan toEntity(CircuitVlanDTO circuitVlanDTO);

    default CircuitVlan fromId(Long id) {
        if (id == null) {
            return null;
        }
        CircuitVlan circuitVlan = new CircuitVlan();
        circuitVlan.setId(id);
        return circuitVlan;
    }
}
