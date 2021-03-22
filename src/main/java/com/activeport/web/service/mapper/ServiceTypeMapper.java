package com.activeport.web.service.mapper;

import com.activeport.web.domain.*;
import com.activeport.web.service.dto.ServiceTypeDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ServiceType} and its DTO {@link ServiceTypeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ServiceTypeMapper extends EntityMapper<ServiceTypeDTO, ServiceType> {
    @Mapping(target = "providers", ignore = true)
    @Mapping(target = "removeProvider", ignore = true)
    ServiceType toEntity(ServiceTypeDTO serviceTypeDTO);

    default ServiceType fromId(Long id) {
        if (id == null) {
            return null;
        }
        ServiceType serviceType = new ServiceType();
        serviceType.setId(id);
        return serviceType;
    }
}
