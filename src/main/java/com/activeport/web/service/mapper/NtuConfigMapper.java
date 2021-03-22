package com.activeport.web.service.mapper;

import com.activeport.web.domain.*;
import com.activeport.web.service.dto.NtuConfigDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link NtuConfig} and its DTO {@link NtuConfigDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface NtuConfigMapper extends EntityMapper<NtuConfigDTO, NtuConfig> {
    default NtuConfig fromId(Long id) {
        if (id == null) {
            return null;
        }
        NtuConfig ntuConfig = new NtuConfig();
        ntuConfig.setId(id);
        return ntuConfig;
    }
}
