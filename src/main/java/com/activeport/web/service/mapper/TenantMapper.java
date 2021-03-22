package com.activeport.web.service.mapper;

import com.activeport.web.domain.*;
import com.activeport.web.service.dto.TenantDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Tenant} and its DTO {@link TenantDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TenantMapper extends EntityMapper<TenantDTO, Tenant> {
    default Tenant fromId(Long id) {
        if (id == null) {
            return null;
        }
        Tenant tenant = new Tenant();
        tenant.setId(id);
        return tenant;
    }
}
