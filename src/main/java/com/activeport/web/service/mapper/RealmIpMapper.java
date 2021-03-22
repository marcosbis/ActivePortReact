package com.activeport.web.service.mapper;

import com.activeport.web.domain.*;
import com.activeport.web.service.dto.RealmIpDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link RealmIp} and its DTO {@link RealmIpDTO}.
 */
@Mapper(componentModel = "spring", uses = { LocationMapper.class })
public interface RealmIpMapper extends EntityMapper<RealmIpDTO, RealmIp> {
    @Mapping(source = "location.id", target = "locationId")
    @Mapping(source = "location.code", target = "locationCode")
    RealmIpDTO toDto(RealmIp realmIp);

    @Mapping(source = "locationId", target = "location")
    RealmIp toEntity(RealmIpDTO realmIpDTO);

    default RealmIp fromId(Long id) {
        if (id == null) {
            return null;
        }
        RealmIp realmIp = new RealmIp();
        realmIp.setId(id);
        return realmIp;
    }
}
