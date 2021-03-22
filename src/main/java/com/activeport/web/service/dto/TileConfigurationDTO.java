package com.activeport.web.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Lob;

/**
 * A DTO for the {@link com.activeport.web.domain.TileConfiguration} entity.
 */
public class TileConfigurationDTO implements Serializable {
    private Long id;

    private String name;

    private String description;

    @Lob
    private String command;

    private Long serviceTypeId;

    private String serviceTypeCode;
    private Set<ServiceConfigurationDTO> services = new HashSet<>();

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

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public Long getServiceTypeId() {
        return serviceTypeId;
    }

    public void setServiceTypeId(Long serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }

    public String getServiceTypeCode() {
        return serviceTypeCode;
    }

    public void setServiceTypeCode(String serviceTypeCode) {
        this.serviceTypeCode = serviceTypeCode;
    }

    public Set<ServiceConfigurationDTO> getServices() {
        return services;
    }

    public void setServices(Set<ServiceConfigurationDTO> serviceConfigurations) {
        this.services = serviceConfigurations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TileConfigurationDTO)) {
            return false;
        }

        return id != null && id.equals(((TileConfigurationDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TileConfigurationDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", command='" + getCommand() + "'" +
            ", serviceTypeId=" + getServiceTypeId() +
            ", serviceTypeCode='" + getServiceTypeCode() + "'" +
            ", services='" + getServices() + "'" +
            "}";
    }
}
