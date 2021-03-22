package com.activeport.web.service.mapper;

import com.activeport.web.domain.*;
import com.activeport.web.service.dto.ServiceCommandDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ServiceCommand} and its DTO {@link ServiceCommandDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ServiceCommandMapper extends EntityMapper<ServiceCommandDTO, ServiceCommand> {
    @Mapping(target = "serviceConfigurations", ignore = true)
    @Mapping(target = "removeServiceConfiguration", ignore = true)
    ServiceCommand toEntity(ServiceCommandDTO serviceCommandDTO);

    default ServiceCommand fromId(Long id) {
        if (id == null) {
            return null;
        }
        ServiceCommand serviceCommand = new ServiceCommand();
        serviceCommand.setId(id);
        return serviceCommand;
    }
}
