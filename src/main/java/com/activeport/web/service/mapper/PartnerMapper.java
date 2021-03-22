package com.activeport.web.service.mapper;

import com.activeport.web.domain.*;
import com.activeport.web.service.dto.PartnerDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Partner} and its DTO {@link PartnerDTO}.
 */
@Mapper(componentModel = "spring", uses = { CentralSwitchMapper.class, ProviderConfigurationMapper.class })
public interface PartnerMapper extends EntityMapper<PartnerDTO, Partner> {
    @Mapping(source = "centralSwitch.id", target = "centralSwitchId")
    @Mapping(source = "centralSwitch.name", target = "centralSwitchName")
    @Mapping(source = "providerCode.id", target = "providerCodeId")
    @Mapping(source = "providerCode.name", target = "providerCodeName")
    PartnerDTO toDto(Partner partner);

    @Mapping(source = "centralSwitchId", target = "centralSwitch")
    @Mapping(source = "providerCodeId", target = "providerCode")
    Partner toEntity(PartnerDTO partnerDTO);

    default Partner fromId(Long id) {
        if (id == null) {
            return null;
        }
        Partner partner = new Partner();
        partner.setId(id);
        return partner;
    }
}
