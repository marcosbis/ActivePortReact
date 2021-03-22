package com.activeport.web.service.mapper;

import com.activeport.web.domain.*;
import com.activeport.web.service.dto.PolicerScheduleDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link PolicerSchedule} and its DTO {@link PolicerScheduleDTO}.
 */
@Mapper(componentModel = "spring", uses = { NtuMapper.class, PolicerRangeMapper.class })
public interface PolicerScheduleMapper extends EntityMapper<PolicerScheduleDTO, PolicerSchedule> {
    @Mapping(source = "ntu.id", target = "ntuId")
    @Mapping(source = "ntu.name", target = "ntuName")
    PolicerScheduleDTO toDto(PolicerSchedule policerSchedule);

    @Mapping(source = "ntuId", target = "ntu")
    @Mapping(target = "removePolicerRange", ignore = true)
    PolicerSchedule toEntity(PolicerScheduleDTO policerScheduleDTO);

    default PolicerSchedule fromId(Long id) {
        if (id == null) {
            return null;
        }
        PolicerSchedule policerSchedule = new PolicerSchedule();
        policerSchedule.setId(id);
        return policerSchedule;
    }
}
