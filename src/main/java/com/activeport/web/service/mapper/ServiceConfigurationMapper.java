package com.activeport.web.service.mapper;

import com.activeport.web.domain.*;
import com.activeport.web.service.dto.ServiceConfigurationDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ServiceConfiguration} and its DTO {@link ServiceConfigurationDTO}.
 */
@Mapper(
    componentModel = "spring",
    uses = { ServiceCodeMapper.class, PartnerMapper.class, ItemCodeMapper.class, ServiceCommandMapper.class }
)
public interface ServiceConfigurationMapper extends EntityMapper<ServiceConfigurationDTO, ServiceConfiguration> {
    @Mapping(source = "serviceCode.id", target = "serviceCodeId")
    @Mapping(source = "serviceCode.name", target = "serviceCodeName")
    @Mapping(source = "provider.id", target = "providerId")
    @Mapping(source = "provider.name", target = "providerName")
    @Mapping(source = "priceCode.id", target = "priceCodeId")
    @Mapping(source = "priceCode.name", target = "priceCodeName")
    ServiceConfigurationDTO toDto(ServiceConfiguration serviceConfiguration);

    @Mapping(source = "serviceCodeId", target = "serviceCode")
    @Mapping(source = "providerId", target = "provider")
    @Mapping(source = "priceCodeId", target = "priceCode")
    @Mapping(target = "removeCommands", ignore = true)
    @Mapping(target = "tileConfigurations", ignore = true)
    @Mapping(target = "removeTileConfiguration", ignore = true)
    ServiceConfiguration toEntity(ServiceConfigurationDTO serviceConfigurationDTO);

    default ServiceConfiguration fromId(Long id) {
        if (id == null) {
            return null;
        }
        ServiceConfiguration serviceConfiguration = new ServiceConfiguration();
        serviceConfiguration.setId(id);
        return serviceConfiguration;
    }
}
