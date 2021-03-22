package com.activeport.web.service.mapper;

import com.activeport.web.domain.*;
import com.activeport.web.service.dto.ServiceCodeDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ServiceCode} and its DTO {@link ServiceCodeDTO}.
 */
@Mapper(componentModel = "spring", uses = { ProviderConfigurationMapper.class, ServiceTypeMapper.class })
public interface ServiceCodeMapper extends EntityMapper<ServiceCodeDTO, ServiceCode> {
    @Mapping(source = "providerType.id", target = "providerTypeId")
    @Mapping(source = "providerType.name", target = "providerTypeName")
    @Mapping(source = "serviceType.id", target = "serviceTypeId")
    @Mapping(source = "serviceType.code", target = "serviceTypeCode")
    ServiceCodeDTO toDto(ServiceCode serviceCode);

    @Mapping(source = "providerTypeId", target = "providerType")
    @Mapping(source = "serviceTypeId", target = "serviceType")
    ServiceCode toEntity(ServiceCodeDTO serviceCodeDTO);

    default ServiceCode fromId(Long id) {
        if (id == null) {
            return null;
        }
        ServiceCode serviceCode = new ServiceCode();
        serviceCode.setId(id);
        return serviceCode;
    }
}
