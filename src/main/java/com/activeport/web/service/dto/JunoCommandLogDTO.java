package com.activeport.web.service.dto;

import com.activeport.web.domain.enumeration.DeviceTargetTypeEnum;
import com.activeport.web.domain.enumeration.EventTypeEnum;
import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * A DTO for the {@link com.activeport.web.domain.JunoCommandLog} entity.
 */
public class JunoCommandLogDTO implements Serializable {
    private Long id;

    private Long ntuId;

    private Long switchId;

    private Long serviceId;

    private Long vxcId;

    private String deviceUrl;

    private String uuid;

    private String command;

    private String cmdResponse;

    private ZonedDateTime executedDate;

    private EventTypeEnum eventType;

    private String user;

    private String deviceName;

    private DeviceTargetTypeEnum targetType;

    private Boolean hasErrors;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNtuId() {
        return ntuId;
    }

    public void setNtuId(Long ntuId) {
        this.ntuId = ntuId;
    }

    public Long getSwitchId() {
        return switchId;
    }

    public void setSwitchId(Long switchId) {
        this.switchId = switchId;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public Long getVxcId() {
        return vxcId;
    }

    public void setVxcId(Long vxcId) {
        this.vxcId = vxcId;
    }

    public String getDeviceUrl() {
        return deviceUrl;
    }

    public void setDeviceUrl(String deviceUrl) {
        this.deviceUrl = deviceUrl;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getCmdResponse() {
        return cmdResponse;
    }

    public void setCmdResponse(String cmdResponse) {
        this.cmdResponse = cmdResponse;
    }

    public ZonedDateTime getExecutedDate() {
        return executedDate;
    }

    public void setExecutedDate(ZonedDateTime executedDate) {
        this.executedDate = executedDate;
    }

    public EventTypeEnum getEventType() {
        return eventType;
    }

    public void setEventType(EventTypeEnum eventType) {
        this.eventType = eventType;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public DeviceTargetTypeEnum getTargetType() {
        return targetType;
    }

    public void setTargetType(DeviceTargetTypeEnum targetType) {
        this.targetType = targetType;
    }

    public Boolean isHasErrors() {
        return hasErrors;
    }

    public void setHasErrors(Boolean hasErrors) {
        this.hasErrors = hasErrors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof JunoCommandLogDTO)) {
            return false;
        }

        return id != null && id.equals(((JunoCommandLogDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "JunoCommandLogDTO{" +
            "id=" + getId() +
            ", ntuId=" + getNtuId() +
            ", switchId=" + getSwitchId() +
            ", serviceId=" + getServiceId() +
            ", vxcId=" + getVxcId() +
            ", deviceUrl='" + getDeviceUrl() + "'" +
            ", uuid='" + getUuid() + "'" +
            ", command='" + getCommand() + "'" +
            ", cmdResponse='" + getCmdResponse() + "'" +
            ", executedDate='" + getExecutedDate() + "'" +
            ", eventType='" + getEventType() + "'" +
            ", user='" + getUser() + "'" +
            ", deviceName='" + getDeviceName() + "'" +
            ", targetType='" + getTargetType() + "'" +
            ", hasErrors='" + isHasErrors() + "'" +
            "}";
    }
}
