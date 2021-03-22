package com.activeport.web.service.dto;

import com.activeport.web.domain.enumeration.ApiTypeEnum;
import com.activeport.web.domain.enumeration.PartnerTypeEnum;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.activeport.web.domain.ProviderConfiguration} entity.
 */
public class ProviderConfigurationDTO implements Serializable {
    private Long id;

    private String name;

    private String description;

    @NotNull
    private PartnerTypeEnum type;

    private ApiTypeEnum apiType;

    private Boolean hasPortId;

    private Set<ServiceTypeDTO> services = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PartnerTypeEnum getType() {
        return type;
    }

    public void setType(PartnerTypeEnum type) {
        this.type = type;
    }

    public ApiTypeEnum getApiType() {
        return apiType;
    }

    public void setApiType(ApiTypeEnum apiType) {
        this.apiType = apiType;
    }

    public Boolean isHasPortId() {
        return hasPortId;
    }

    public void setHasPortId(Boolean hasPortId) {
        this.hasPortId = hasPortId;
    }

    public Set<ServiceTypeDTO> getServices() {
        return services;
    }

    public void setServices(Set<ServiceTypeDTO> serviceTypes) {
        this.services = serviceTypes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProviderConfigurationDTO)) {
            return false;
        }

        return id != null && id.equals(((ProviderConfigurationDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProviderConfigurationDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", type='" + getType() + "'" +
            ", apiType='" + getApiType() + "'" +
            ", hasPortId='" + isHasPortId() + "'" +
            ", services='" + getServices() + "'" +
            "}";
    }
}
