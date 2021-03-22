package com.activeport.web.service.mapper;

import com.activeport.web.domain.*;
import com.activeport.web.service.dto.CentralSwitchDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CentralSwitch} and its DTO {@link CentralSwitchDTO}.
 */
@Mapper(componentModel = "spring", uses = { LocationMapper.class, NtuTypeMapper.class })
public interface CentralSwitchMapper extends EntityMapper<CentralSwitchDTO, CentralSwitch> {
    @Mapping(source = "location.id", target = "locationId")
    @Mapping(source = "location.code", target = "locationCode")
    @Mapping(source = "ntutype.id", target = "ntutypeId")
    @Mapping(source = "ntutype.model", target = "ntutypeModel")
    @Mapping(source = "ntutype.id", target = "ntutypeId")
    @Mapping(source = "ntutype.model", target = "ntutypeModel")
    CentralSwitchDTO toDto(CentralSwitch centralSwitch);

    @Mapping(source = "locationId", target = "location")
    @Mapping(source = "ntutypeId", target = "ntutype")
    @Mapping(source = "ntutypeId", target = "ntutype")
    CentralSwitch toEntity(CentralSwitchDTO centralSwitchDTO);

    default CentralSwitch fromId(Long id) {
        if (id == null) {
            return null;
        }
        CentralSwitch centralSwitch = new CentralSwitch();
        centralSwitch.setId(id);
        return centralSwitch;
    }
}
