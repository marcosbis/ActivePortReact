package com.activeport.web.service.mapper;

import com.activeport.web.domain.*;
import com.activeport.web.service.dto.ThirdPartyApiDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ThirdPartyApi} and its DTO {@link ThirdPartyApiDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ThirdPartyApiMapper extends EntityMapper<ThirdPartyApiDTO, ThirdPartyApi> {
    default ThirdPartyApi fromId(Long id) {
        if (id == null) {
            return null;
        }
        ThirdPartyApi thirdPartyApi = new ThirdPartyApi();
        thirdPartyApi.setId(id);
        return thirdPartyApi;
    }
}
