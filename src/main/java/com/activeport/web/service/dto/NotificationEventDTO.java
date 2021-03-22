package com.activeport.web.service.dto;

import com.activeport.web.domain.enumeration.EventStatusEnum;
import com.activeport.web.domain.enumeration.EventTypeEnum;
import java.io.Serializable;
import javax.persistence.Lob;

/**
 * A DTO for the {@link com.activeport.web.domain.NotificationEvent} entity.
 */
public class NotificationEventDTO implements Serializable {
    private Long id;

    private String ntuName;

    private Long ntuId;

    private EventStatusEnum status;

    private EventTypeEnum type;

    private String message;

    private String serviceName;

    private Long serviceId;

    private Long vxcId;

    private String uuid;

    private String user;

    @Lob
    private String errorMessage;

    private Long jobId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNtuName() {
        return ntuName;
    }

    public void setNtuName(String ntuName) {
        this.ntuName = ntuName;
    }

    public Long getNtuId() {
        return ntuId;
    }

    public void setNtuId(Long ntuId) {
        this.ntuId = ntuId;
    }

    public EventStatusEnum getStatus() {
        return status;
    }

    public void setStatus(EventStatusEnum status) {
        this.status = status;
    }

    public EventTypeEnum getType() {
        return type;
    }

    public void setType(EventTypeEnum type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
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

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long configJobId) {
        this.jobId = configJobId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NotificationEventDTO)) {
            return false;
        }

        return id != null && id.equals(((NotificationEventDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NotificationEventDTO{" +
            "id=" + getId() +
            ", ntuName='" + getNtuName() + "'" +
            ", ntuId=" + getNtuId() +
            ", status='" + getStatus() + "'" +
            ", type='" + getType() + "'" +
            ", message='" + getMessage() + "'" +
            ", serviceName='" + getServiceName() + "'" +
            ", serviceId=" + getServiceId() +
            ", vxcId=" + getVxcId() +
            ", uuid='" + getUuid() + "'" +
            ", user='" + getUser() + "'" +
            ", errorMessage='" + getErrorMessage() + "'" +
            ", jobId=" + getJobId() +
            "}";
    }
}
