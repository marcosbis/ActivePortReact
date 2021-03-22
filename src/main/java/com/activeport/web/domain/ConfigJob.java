package com.activeport.web.domain;

import com.activeport.web.domain.enumeration.EventTypeEnum;
import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ConfigJob.
 */
@Entity
@Table(name = "config_job")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ConfigJob implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "host_id")
    private String hostId;

    @Column(name = "status")
    private String status;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "message")
    private String message;

    @Lob
    @Column(name = "command")
    private String command;

    @Column(name = "executed")
    private ZonedDateTime executed;

    @Column(name = "executed_status")
    private String executedStatus;

    @Column(name = "executed_message")
    private String executedMessage;

    @Column(name = "ntu_id")
    private Long ntuId;

    @Enumerated(EnumType.STRING)
    @Column(name = "event_type")
    private EventTypeEnum eventType;

    @Column(name = "user")
    private String user;

    @Lob
    @Column(name = "error_message")
    private String errorMessage;

    @Column(name = "callback_url")
    private String callbackUrl;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHostId() {
        return hostId;
    }

    public ConfigJob hostId(String hostId) {
        this.hostId = hostId;
        return this;
    }

    public void setHostId(String hostId) {
        this.hostId = hostId;
    }

    public String getStatus() {
        return status;
    }

    public ConfigJob status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUuid() {
        return uuid;
    }

    public ConfigJob uuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getMessage() {
        return message;
    }

    public ConfigJob message(String message) {
        this.message = message;
        return this;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCommand() {
        return command;
    }

    public ConfigJob command(String command) {
        this.command = command;
        return this;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public ZonedDateTime getExecuted() {
        return executed;
    }

    public ConfigJob executed(ZonedDateTime executed) {
        this.executed = executed;
        return this;
    }

    public void setExecuted(ZonedDateTime executed) {
        this.executed = executed;
    }

    public String getExecutedStatus() {
        return executedStatus;
    }

    public ConfigJob executedStatus(String executedStatus) {
        this.executedStatus = executedStatus;
        return this;
    }

    public void setExecutedStatus(String executedStatus) {
        this.executedStatus = executedStatus;
    }

    public String getExecutedMessage() {
        return executedMessage;
    }

    public ConfigJob executedMessage(String executedMessage) {
        this.executedMessage = executedMessage;
        return this;
    }

    public void setExecutedMessage(String executedMessage) {
        this.executedMessage = executedMessage;
    }

    public Long getNtuId() {
        return ntuId;
    }

    public ConfigJob ntuId(Long ntuId) {
        this.ntuId = ntuId;
        return this;
    }

    public void setNtuId(Long ntuId) {
        this.ntuId = ntuId;
    }

    public EventTypeEnum getEventType() {
        return eventType;
    }

    public ConfigJob eventType(EventTypeEnum eventType) {
        this.eventType = eventType;
        return this;
    }

    public void setEventType(EventTypeEnum eventType) {
        this.eventType = eventType;
    }

    public String getUser() {
        return user;
    }

    public ConfigJob user(String user) {
        this.user = user;
        return this;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public ConfigJob errorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public ConfigJob callbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
        return this;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ConfigJob)) {
            return false;
        }
        return id != null && id.equals(((ConfigJob) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ConfigJob{" +
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
