package com.activeport.web.service.mapper;

import com.activeport.web.domain.*;
import com.activeport.web.service.dto.PolicerRangeDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link PolicerRange} and its DTO {@link PolicerRangeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PolicerRangeMapper extends EntityMapper<PolicerRangeDTO, PolicerRange> {
    @Mapping(target = "policerSchedules", ignore = true)
    @Mapping(target = "removePolicerSchedule", ignore = true)
    PolicerRange toEntity(PolicerRangeDTO policerRangeDTO);

    default PolicerRange fromId(Long id) {
        if (id == null) {
            return null;
        }
        PolicerRange policerRange = new PolicerRange();
        policerRange.setId(id);
        return policerRange;
    }
}
