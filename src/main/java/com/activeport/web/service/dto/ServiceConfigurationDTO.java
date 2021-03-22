package com.activeport.web.service.dto;

import com.activeport.web.domain.enumeration.TenantTypeEnum;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Lob;

/**
 * A DTO for the {@link com.activeport.web.domain.ServiceConfiguration} entity.
 */
public class ServiceConfigurationDTO implements Serializable {
    private Long id;

    private String name;

    private String description;

    private TenantTypeEnum tenantType;

    @Lob
    private String command;

    private String test;

    private Boolean useDefaultCommands;

    private Long serviceCodeId;

    private String serviceCodeName;

    private Long providerId;

    private String providerName;

    private Long priceCodeId;

    private String priceCodeName;
    private Set<ServiceCommandDTO> commands = new HashSet<>();

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

    public TenantTypeEnum getTenantType() {
        return tenantType;
    }

    public void setTenantType(TenantTypeEnum tenantType) {
        this.tenantType = tenantType;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public Boolean isUseDefaultCommands() {
        return useDefaultCommands;
    }

    public void setUseDefaultCommands(Boolean useDefaultCommands) {
        this.useDefaultCommands = useDefaultCommands;
    }

    public Long getServiceCodeId() {
        return serviceCodeId;
    }

    public void setServiceCodeId(Long serviceCodeId) {
        this.serviceCodeId = serviceCodeId;
    }

    public String getServiceCodeName() {
        return serviceCodeName;
    }

    public void setServiceCodeName(String serviceCodeName) {
        this.serviceCodeName = serviceCodeName;
    }

    public Long getProviderId() {
        return providerId;
    }

    public void setProviderId(Long partnerId) {
        this.providerId = partnerId;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String partnerName) {
        this.providerName = partnerName;
    }

    public Long getPriceCodeId() {
        return priceCodeId;
    }

    public void setPriceCodeId(Long itemCodeId) {
        this.priceCodeId = itemCodeId;
    }

    public String getPriceCodeName() {
        return priceCodeName;
    }

    public void setPriceCodeName(String itemCodeName) {
        this.priceCodeName = itemCodeName;
    }

    public Set<ServiceCommandDTO> getCommands() {
        return commands;
    }

    public void setCommands(Set<ServiceCommandDTO> serviceCommands) {
        this.commands = serviceCommands;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ServiceConfigurationDTO)) {
            return false;
        }

        return id != null && id.equals(((ServiceConfigurationDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ServiceConfigurationDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", tenantType='" + getTenantType() + "'" +
            ", command='" + getCommand() + "'" +
            ", test='" + getTest() + "'" +
            ", useDefaultCommands='" + isUseDefaultCommands() + "'" +
            ", serviceCodeId=" + getServiceCodeId() +
            ", serviceCodeName='" + getServiceCodeName() + "'" +
            ", providerId=" + getProviderId() +
            ", providerName='" + getProviderName() + "'" +
            ", priceCodeId=" + getPriceCodeId() +
            ", priceCodeName='" + getPriceCodeName() + "'" +
            ", commands='" + getCommands() + "'" +
            "}";
    }
}
