package com.activeport.web.service.mapper;

import com.activeport.web.domain.*;
import com.activeport.web.service.dto.NtuDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Ntu} and its DTO {@link NtuDTO}.
 */
@Mapper(componentModel = "spring", uses = { DeviceConfigurationMapper.class, NtuTypeMapper.class })
public interface NtuMapper extends EntityMapper<NtuDTO, Ntu> {
    @Mapping(source = "deviceConfiguration.id", target = "deviceConfigurationId")
    @Mapping(source = "deviceConfiguration.serialNumber", target = "deviceConfigurationSerialNumber")
    @Mapping(source = "ntutype.id", target = "ntutypeId")
    @Mapping(source = "ntutype.model", target = "ntutypeModel")
    @Mapping(source = "ntutype.id", target = "ntutypeId")
    @Mapping(source = "ntutype.model", target = "ntutypeModel")
    NtuDTO toDto(Ntu ntu);

    @Mapping(source = "deviceConfigurationId", target = "deviceConfiguration")
    @Mapping(target = "ports", ignore = true)
    @Mapping(target = "removePort", ignore = true)
    @Mapping(source = "ntutypeId", target = "ntutype")
    @Mapping(source = "ntutypeId", target = "ntutype")
    Ntu toEntity(NtuDTO ntuDTO);

    default Ntu fromId(Long id) {
        if (id == null) {
            return null;
        }
        Ntu ntu = new Ntu();
        ntu.setId(id);
        return ntu;
    }
}
