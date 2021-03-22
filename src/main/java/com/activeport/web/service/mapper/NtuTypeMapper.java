package com.activeport.web.service.mapper;

import com.activeport.web.domain.*;
import com.activeport.web.service.dto.NtuTypeDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link NtuType} and its DTO {@link NtuTypeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface NtuTypeMapper extends EntityMapper<NtuTypeDTO, NtuType> {
    @Mapping(target = "switches", ignore = true)
    @Mapping(target = "removeSwitch", ignore = true)
    @Mapping(target = "ntus", ignore = true)
    @Mapping(target = "removeNtu", ignore = true)
    NtuType toEntity(NtuTypeDTO ntuTypeDTO);

    default NtuType fromId(Long id) {
        if (id == null) {
            return null;
        }
        NtuType ntuType = new NtuType();
        ntuType.setId(id);
        return ntuType;
    }
}
