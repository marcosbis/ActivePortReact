package com.activeport.web.domain;

import com.activeport.web.domain.enumeration.EventStatusEnum;
import com.activeport.web.domain.enumeration.EventTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A NotificationEvent.
 */
@Entity
@Table(name = "notification_event")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class NotificationEvent implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ntu_name")
    private String ntuName;

    @Column(name = "ntu_id")
    private Long ntuId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private EventStatusEnum status;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private EventTypeEnum type;

    @Column(name = "message")
    private String message;

    @Column(name = "service_name")
    private String serviceName;

    @Column(name = "service_id")
    private Long serviceId;

    @Column(name = "vxc_id")
    private Long vxcId;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "user")
    private String user;

    @Lob
    @Column(name = "error_message")
    private String errorMessage;

    @ManyToOne
    @JsonIgnoreProperties(value = "notificationEvents", allowSetters = true)
    private ConfigJob job;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNtuName() {
        return ntuName;
    }

    public NotificationEvent ntuName(String ntuName) {
        this.ntuName = ntuName;
        return this;
    }

    public void setNtuName(String ntuName) {
        this.ntuName = ntuName;
    }

    public Long getNtuId() {
        return ntuId;
    }

    public NotificationEvent ntuId(Long ntuId) {
        this.ntuId = ntuId;
        return this;
    }

    public void setNtuId(Long ntuId) {
        this.ntuId = ntuId;
    }

    public EventStatusEnum getStatus() {
        return status;
    }

    public NotificationEvent status(EventStatusEnum status) {
        this.status = status;
        return this;
    }

    public void setStatus(EventStatusEnum status) {
        this.status = status;
    }

    public EventTypeEnum getType() {
        return type;
    }

    public NotificationEvent type(EventTypeEnum type) {
        this.type = type;
        return this;
    }

    public void setType(EventTypeEnum type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public NotificationEvent message(String message) {
        this.message = message;
        return this;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getServiceName() {
        return serviceName;
    }

    public NotificationEvent serviceName(String serviceName) {
        this.serviceName = serviceName;
        return this;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public NotificationEvent serviceId(Long serviceId) {
        this.serviceId = serviceId;
        return this;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public Long getVxcId() {
        return vxcId;
    }

    public NotificationEvent vxcId(Long vxcId) {
        this.vxcId = vxcId;
        return this;
    }

    public void setVxcId(Long vxcId) {
        this.vxcId = vxcId;
    }

    public String getUuid() {
        return uuid;
    }

    public NotificationEvent uuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUser() {
        return user;
    }

    public NotificationEvent user(String user) {
        this.user = user;
        return this;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public NotificationEvent errorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ConfigJob getJob() {
        return job;
    }

    public NotificationEvent job(ConfigJob configJob) {
        this.job = configJob;
        return this;
    }

    public void setJob(ConfigJob configJob) {
        this.job = configJob;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NotificationEvent)) {
            return false;
        }
        return id != null && id.equals(((NotificationEvent) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NotificationEvent{" +
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
            "}";
    }
}
