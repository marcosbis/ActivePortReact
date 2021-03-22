package com.activeport.web.service.mapper;

import com.activeport.web.domain.*;
import com.activeport.web.service.dto.VntuDownlinkPortDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link VntuDownlinkPort} and its DTO {@link VntuDownlinkPortDTO}.
 */
@Mapper(componentModel = "spring", uses = { CentralSwitchMapper.class })
public interface VntuDownlinkPortMapper extends EntityMapper<VntuDownlinkPortDTO, VntuDownlinkPort> {
    @Mapping(source = "centralSwitch.id", target = "centralSwitchId")
    @Mapping(source = "centralSwitch.name", target = "centralSwitchName")
    VntuDownlinkPortDTO toDto(VntuDownlinkPort vntuDownlinkPort);

    @Mapping(source = "centralSwitchId", target = "centralSwitch")
    VntuDownlinkPort toEntity(VntuDownlinkPortDTO vntuDownlinkPortDTO);

    default VntuDownlinkPort fromId(Long id) {
        if (id == null) {
            return null;
        }
        VntuDownlinkPort vntuDownlinkPort = new VntuDownlinkPort();
        vntuDownlinkPort.setId(id);
        return vntuDownlinkPort;
    }
}
