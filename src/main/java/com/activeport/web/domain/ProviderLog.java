package com.activeport.web.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ProviderLog.
 */
@Entity
@Table(name = "provider_log")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ProviderLog implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "org_id")
    private String orgId;

    @Column(name = "tenant_id")
    private String tenantId;

    @Column(name = "service_id")
    private Long serviceId;

    @Lob
    @Column(name = "log")
    private String log;

    @Column(name = "type")
    private String type;

    @Column(name = "job_uid")
    private String jobUid;

    @Column(name = "ntu_id")
    private Long ntuId;

    @Lob
    @Column(name = "request")
    private String request;

    @Lob
    @Column(name = "response_log")
    private String responseLog;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrgId() {
        return orgId;
    }

    public ProviderLog orgId(String orgId) {
        this.orgId = orgId;
        return this;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public ProviderLog tenantId(String tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public ProviderLog serviceId(Long serviceId) {
        this.serviceId = serviceId;
        return this;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public String getLog() {
        return log;
    }

    public ProviderLog log(String log) {
        this.log = log;
        return this;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public String getType() {
        return type;
    }

    public ProviderLog type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getJobUid() {
        return jobUid;
    }

    public ProviderLog jobUid(String jobUid) {
        this.jobUid = jobUid;
        return this;
    }

    public void setJobUid(String jobUid) {
        this.jobUid = jobUid;
    }

    public Long getNtuId() {
        return ntuId;
    }

    public ProviderLog ntuId(Long ntuId) {
        this.ntuId = ntuId;
        return this;
    }

    public void setNtuId(Long ntuId) {
        this.ntuId = ntuId;
    }

    public String getRequest() {
        return request;
    }

    public ProviderLog request(String request) {
        this.request = request;
        return this;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getResponseLog() {
        return responseLog;
    }

    public ProviderLog responseLog(String responseLog) {
        this.responseLog = responseLog;
        return this;
    }

    public void setResponseLog(String responseLog) {
        this.responseLog = responseLog;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProviderLog)) {
            return false;
        }
        return id != null && id.equals(((ProviderLog) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProviderLog{" +
            "id=" + getId() +
            ", orgId='" + getOrgId() + "'" +
            ", tenantId='" + getTenantId() + "'" +
            ", serviceId=" + getServiceId() +
            ", log='" + getLog() + "'" +
            ", type='" + getType() + "'" +
            ", jobUid='" + getJobUid() + "'" +
            ", ntuId=" + getNtuId() +
            ", request='" + getRequest() + "'" +
            ", responseLog='" + getResponseLog() + "'" +
            "}";
    }
}
