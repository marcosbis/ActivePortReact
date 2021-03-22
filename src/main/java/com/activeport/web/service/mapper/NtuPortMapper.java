package com.activeport.web.service.mapper;

import com.activeport.web.domain.*;
import com.activeport.web.service.dto.NtuPortDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link NtuPort} and its DTO {@link NtuPortDTO}.
 */
@Mapper(componentModel = "spring", uses = { NtuMapper.class })
public interface NtuPortMapper extends EntityMapper<NtuPortDTO, NtuPort> {
    @Mapping(source = "ntu.id", target = "ntuId")
    @Mapping(source = "ntu.name", target = "ntuName")
    NtuPortDTO toDto(NtuPort ntuPort);

    @Mapping(target = "userServices", ignore = true)
    @Mapping(target = "removeUserServices", ignore = true)
    @Mapping(source = "ntuId", target = "ntu")
    NtuPort toEntity(NtuPortDTO ntuPortDTO);

    default NtuPort fromId(Long id) {
        if (id == null) {
            return null;
        }
        NtuPort ntuPort = new NtuPort();
        ntuPort.setId(id);
        return ntuPort;
    }
}
