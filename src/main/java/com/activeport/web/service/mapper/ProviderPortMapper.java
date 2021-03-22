package com.activeport.web.service.mapper;

import com.activeport.web.domain.*;
import com.activeport.web.service.dto.ProviderPortDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProviderPort} and its DTO {@link ProviderPortDTO}.
 */
@Mapper(componentModel = "spring", uses = { ThirdPartyApiMapper.class })
public interface ProviderPortMapper extends EntityMapper<ProviderPortDTO, ProviderPort> {
    @Mapping(source = "thirdPartyApi.id", target = "thirdPartyApiId")
    @Mapping(source = "thirdPartyApi.name", target = "thirdPartyApiName")
    ProviderPortDTO toDto(ProviderPort providerPort);

    @Mapping(source = "thirdPartyApiId", target = "thirdPartyApi")
    ProviderPort toEntity(ProviderPortDTO providerPortDTO);

    default ProviderPort fromId(Long id) {
        if (id == null) {
            return null;
        }
        ProviderPort providerPort = new ProviderPort();
        providerPort.setId(id);
        return providerPort;
    }
}
