package com.activeport.web.service.dto;

import com.activeport.web.domain.enumeration.EntityTypeEnum;
import com.activeport.web.domain.enumeration.FilterCommandTypeEnum;
import com.activeport.web.domain.enumeration.NtuSerieEnum;
import com.activeport.web.domain.enumeration.OnEventTypeEnum;
import com.activeport.web.domain.enumeration.OsTypeEnum;
import com.activeport.web.domain.enumeration.ServiceTypeEnum;
import java.io.Serializable;
import javax.persistence.Lob;

/**
 * A DTO for the {@link com.activeport.web.domain.ServiceCommand} entity.
 */
public class ServiceCommandDTO implements Serializable {
    private Long id;

    private String name;

    @Lob
    private String command;

    private OnEventTypeEnum onEvent;

    private ServiceTypeEnum onService;

    private NtuSerieEnum deviceType;

    private Boolean enabled;

    private FilterCommandTypeEnum circuitType;

    private String tag;

    private OsTypeEnum osType;

    private EntityTypeEnum entryType;

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

    public OnEventTypeEnum getOnEvent() {
        return onEvent;
    }

    public void setOnEvent(OnEventTypeEnum onEvent) {
        this.onEvent = onEvent;
    }

    public ServiceTypeEnum getOnService() {
        return onService;
    }

    public void setOnService(ServiceTypeEnum onService) {
        this.onService = onService;
    }

    public NtuSerieEnum getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(NtuSerieEnum deviceType) {
        this.deviceType = deviceType;
    }

    public Boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public FilterCommandTypeEnum getCircuitType() {
        return circuitType;
    }

    public void setCircuitType(FilterCommandTypeEnum circuitType) {
        this.circuitType = circuitType;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public OsTypeEnum getOsType() {
        return osType;
    }

    public void setOsType(OsTypeEnum osType) {
        this.osType = osType;
    }

    public EntityTypeEnum getEntryType() {
        return entryType;
    }

    public void setEntryType(EntityTypeEnum entryType) {
        this.entryType = entryType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ServiceCommandDTO)) {
            return false;
        }

        return id != null && id.equals(((ServiceCommandDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ServiceCommandDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", command='" + getCommand() + "'" +
            ", onEvent='" + getOnEvent() + "'" +
            ", onService='" + getOnService() + "'" +
            ", deviceType='" + getDeviceType() + "'" +
            ", enabled='" + isEnabled() + "'" +
            ", circuitType='" + getCircuitType() + "'" +
            ", tag='" + getTag() + "'" +
            ", osType='" + getOsType() + "'" +
            ", entryType='" + getEntryType() + "'" +
            "}";
    }
}
