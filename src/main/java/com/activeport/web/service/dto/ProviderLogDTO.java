package com.activeport.web.service.dto;

import java.io.Serializable;
import javax.persistence.Lob;

/**
 * A DTO for the {@link com.activeport.web.domain.ProviderLog} entity.
 */
public class ProviderLogDTO implements Serializable {
    private Long id;

    private String orgId;

    private String tenantId;

    private Long serviceId;

    @Lob
    private String log;

    private String type;

    private String jobUid;

    private Long ntuId;

    @Lob
    private String request;

    @Lob
    private String responseLog;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getJobUid() {
        return jobUid;
    }

    public void setJobUid(String jobUid) {
        this.jobUid = jobUid;
    }

    public Long getNtuId() {
        return ntuId;
    }

    public void setNtuId(Long ntuId) {
        this.ntuId = ntuId;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getResponseLog() {
        return responseLog;
    }

    public void setResponseLog(String responseLog) {
        this.responseLog = responseLog;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProviderLogDTO)) {
            return false;
        }

        return id != null && id.equals(((ProviderLogDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProviderLogDTO{" +
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
