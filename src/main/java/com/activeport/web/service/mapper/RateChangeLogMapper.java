package com.activeport.web.service.mapper;

import com.activeport.web.domain.*;
import com.activeport.web.service.dto.RateChangeLogDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link RateChangeLog} and its DTO {@link RateChangeLogDTO}.
 */
@Mapper(componentModel = "spring", uses = { UserServiceMapper.class })
public interface RateChangeLogMapper extends EntityMapper<RateChangeLogDTO, RateChangeLog> {
    @Mapping(source = "userService.id", target = "userServiceId")
    @Mapping(source = "userService.name", target = "userServiceName")
    RateChangeLogDTO toDto(RateChangeLog rateChangeLog);

    @Mapping(source = "userServiceId", target = "userService")
    RateChangeLog toEntity(RateChangeLogDTO rateChangeLogDTO);

    default RateChangeLog fromId(Long id) {
        if (id == null) {
            return null;
        }
        RateChangeLog rateChangeLog = new RateChangeLog();
        rateChangeLog.setId(id);
        return rateChangeLog;
    }
}
