package com.activeport.web.service.dto;

import com.activeport.web.domain.enumeration.EventTypeEnum;
import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.persistence.Lob;

/**
 * A DTO for the {@link com.activeport.web.domain.ConfigJob} entity.
 */
public class ConfigJobDTO implements Serializable {
    private Long id;

    private String hostId;

    private String status;

    private String uuid;

    private String message;

    @Lob
    private String command;

    private ZonedDateTime executed;

    private String executedStatus;

    private String executedMessage;

    private Long ntuId;

    private EventTypeEnum eventType;

    private String user;

    @Lob
    private String errorMessage;

    private String callbackUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHostId() {
        return hostId;
    }

    public void setHostId(String hostId) {
        this.hostId = hostId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public ZonedDateTime getExecuted() {
        return executed;
    }

    public void setExecuted(ZonedDateTime executed) {
        this.executed = executed;
    }

    public String getExecutedStatus() {
        return executedStatus;
    }

    public void setExecutedStatus(String executedStatus) {
        this.executedStatus = executedStatus;
    }

    public String getExecutedMessage() {
        return executedMessage;
    }

    public void setExecutedMessage(String executedMessage) {
        this.executedMessage = executedMessage;
    }

    public Long getNtuId() {
        return ntuId;
    }

    public void setNtuId(Long ntuId) {
        this.ntuId = ntuId;
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

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ConfigJobDTO)) {
            return false;
        }

        return id != null && id.equals(((ConfigJobDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ConfigJobDTO{" +
            "id=" + getId() +
            ", hostId='" + getHostId() + "'" +
            ", status='" + getStatus() + "'" +
            ", uuid='" + getUuid() + "'" +
            ", message='" + getMessage() + "'" +
            ", command='" + getCommand() + "'" +
            ", executed='" + getExecuted() + "'" +
            ", executedStatus='" + getExecutedStatus() + "'" +
            ", executedMessage='" + getExecutedMessage() + "'" +
            ", ntuId=" + getNtuId() +
            ", eventType='" + getEventType() + "'" +
            ", user='" + getUser() + "'" +
            ", errorMessage='" + getErrorMessage() + "'" +
            ", callbackUrl='" + getCallbackUrl() + "'" +
            "}";
    }
}
