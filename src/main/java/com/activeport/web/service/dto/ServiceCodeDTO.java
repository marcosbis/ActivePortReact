package com.activeport.web.service.dto;

import com.activeport.web.domain.enumeration.CreationTypeEnum;
import com.activeport.web.domain.enumeration.HostedTypeEnum;
import java.io.Serializable;
import javax.persistence.Lob;

/**
 * A DTO for the {@link com.activeport.web.domain.ServiceCode} entity.
 */
public class ServiceCodeDTO implements Serializable {
    private Long id;

    private String name;

    @Lob
    private String command;

    private String description;

    private Boolean enabled;

    private String serviceUrl;

    private HostedTypeEnum hostedType;

    private CreationTypeEnum creationType;

    private String tag;

    private String dtoClass;

    private Long providerTypeId;

    private String providerTypeName;

    private Long serviceTypeId;

    private String serviceTypeCode;

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

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getServiceUrl() {
        return serviceUrl;
    }

    public void setServiceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    public HostedTypeEnum getHostedType() {
        return hostedType;
    }

    public void setHostedType(HostedTypeEnum hostedType) {
        this.hostedType = hostedType;
    }

    public CreationTypeEnum getCreationType() {
        return creationType;
    }

    public void setCreationType(CreationTypeEnum creationType) {
        this.creationType = creationType;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getDtoClass() {
        return dtoClass;
    }

    public void setDtoClass(String dtoClass) {
        this.dtoClass = dtoClass;
    }

    public Long getProviderTypeId() {
        return providerTypeId;
    }

    public void setProviderTypeId(Long providerConfigurationId) {
        this.providerTypeId = providerConfigurationId;
    }

    public String getProviderTypeName() {
        return providerTypeName;
    }

    public void setProviderTypeName(String providerConfigurationName) {
        this.providerTypeName = providerConfigurationName;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ServiceCodeDTO)) {
            return false;
        }

        return id != null && id.equals(((ServiceCodeDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ServiceCodeDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", command='" + getCommand() + "'" +
            ", description='" + getDescription() + "'" +
            ", enabled='" + isEnabled() + "'" +
            ", serviceUrl='" + getServiceUrl() + "'" +
            ", hostedType='" + getHostedType() + "'" +
            ", creationType='" + getCreationType() + "'" +
            ", tag='" + getTag() + "'" +
            ", dtoClass='" + getDtoClass() + "'" +
            ", providerTypeId=" + getProviderTypeId() +
            ", providerTypeName='" + getProviderTypeName() + "'" +
            ", serviceTypeId=" + getServiceTypeId() +
            ", serviceTypeCode='" + getServiceTypeCode() + "'" +
            "}";
    }
}
