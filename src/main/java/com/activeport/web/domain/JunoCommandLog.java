package com.activeport.web.domain;

import com.activeport.web.domain.enumeration.DeviceTargetTypeEnum;
import com.activeport.web.domain.enumeration.EventTypeEnum;
import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A JunoCommandLog.
 */
@Entity
@Table(name = "juno_command_log")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class JunoCommandLog implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ntu_id")
    private Long ntuId;

    @Column(name = "switch_id")
    private Long switchId;

    @Column(name = "service_id")
    private Long serviceId;

    @Column(name = "vxc_id")
    private Long vxcId;

    @Column(name = "device_url")
    private String deviceUrl;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "command")
    private String command;

    @Column(name = "cmd_response")
    private String cmdResponse;

    @Column(name = "executed_date")
    private ZonedDateTime executedDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "event_type")
    private EventTypeEnum eventType;

    @Column(name = "user")
    private String user;

    @Column(name = "device_name")
    private String deviceName;

    @Enumerated(EnumType.STRING)
    @Column(name = "target_type")
    private DeviceTargetTypeEnum targetType;

    @Column(name = "has_errors")
    private Boolean hasErrors;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNtuId() {
        return ntuId;
    }

    public JunoCommandLog ntuId(Long ntuId) {
        this.ntuId = ntuId;
        return this;
    }

    public void setNtuId(Long ntuId) {
        this.ntuId = ntuId;
    }

    public Long getSwitchId() {
        return switchId;
    }

    public JunoCommandLog switchId(Long switchId) {
        this.switchId = switchId;
        return this;
    }

    public void setSwitchId(Long switchId) {
        this.switchId = switchId;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public JunoCommandLog serviceId(Long serviceId) {
        this.serviceId = serviceId;
        return this;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public Long getVxcId() {
        return vxcId;
    }

    public JunoCommandLog vxcId(Long vxcId) {
        this.vxcId = vxcId;
        return this;
    }

    public void setVxcId(Long vxcId) {
        this.vxcId = vxcId;
    }

    public String getDeviceUrl() {
        return deviceUrl;
    }

    public JunoCommandLog deviceUrl(String deviceUrl) {
        this.deviceUrl = deviceUrl;
        return this;
    }

    public void setDeviceUrl(String deviceUrl) {
        this.deviceUrl = deviceUrl;
    }

    public String getUuid() {
        return uuid;
    }

    public JunoCommandLog uuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCommand() {
        return command;
    }

    public JunoCommandLog command(String command) {
        this.command = command;
        return this;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getCmdResponse() {
        return cmdResponse;
    }

    public JunoCommandLog cmdResponse(String cmdResponse) {
        this.cmdResponse = cmdResponse;
        return this;
    }

    public void setCmdResponse(String cmdResponse) {
        this.cmdResponse = cmdResponse;
    }

    public ZonedDateTime getExecutedDate() {
        return executedDate;
    }

    public JunoCommandLog executedDate(ZonedDateTime executedDate) {
        this.executedDate = executedDate;
        return this;
    }

    public void setExecutedDate(ZonedDateTime executedDate) {
        this.executedDate = executedDate;
    }

    public EventTypeEnum getEventType() {
        return eventType;
    }

    public JunoCommandLog eventType(EventTypeEnum eventType) {
        this.eventType = eventType;
        return this;
    }

    public void setEventType(EventTypeEnum eventType) {
        this.eventType = eventType;
    }

    public String getUser() {
        return user;
    }

    public JunoCommandLog user(String user) {
        this.user = user;
        return this;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public JunoCommandLog deviceName(String deviceName) {
        this.deviceName = deviceName;
        return this;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public DeviceTargetTypeEnum getTargetType() {
        return targetType;
    }

    public JunoCommandLog targetType(DeviceTargetTypeEnum targetType) {
        this.targetType = targetType;
        return this;
    }

    public void setTargetType(DeviceTargetTypeEnum targetType) {
        this.targetType = targetType;
    }

    public Boolean isHasErrors() {
        return hasErrors;
    }

    public JunoCommandLog hasErrors(Boolean hasErrors) {
        this.hasErrors = hasErrors;
        return this;
    }

    public void setHasErrors(Boolean hasErrors) {
        this.hasErrors = hasErrors;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof JunoCommandLog)) {
            return false;
        }
        return id != null && id.equals(((JunoCommandLog) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "JunoCommandLog{" +
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
