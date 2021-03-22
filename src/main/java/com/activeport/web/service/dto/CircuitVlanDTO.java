package com.activeport.web.service.dto;

import com.activeport.web.domain.enumeration.ConnectTypeEnum;
import com.activeport.web.domain.enumeration.InternetTypeEnum;
import java.io.Serializable;

/**
 * A DTO for the {@link com.activeport.web.domain.CircuitVlan} entity.
 */
public class CircuitVlanDTO implements Serializable {
    private Long id;

    private String zone;

    private String serviceKey;

    private Integer vlanId;

    private String rd;

    private Long serviceId;

    private String tenantName;

    private Long childServiceId;

    private Long childNtuId;

    private String realmIp;

    private InternetTypeEnum internetType;

    private ConnectTypeEnum type;

    private Long serviceConfigurationId;

    private String serviceConfigurationName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getServiceKey() {
        return serviceKey;
    }

    public void setServiceKey(String serviceKey) {
        this.serviceKey = serviceKey;
    }

    public Integer getVlanId() {
        return vlanId;
    }

    public void setVlanId(Integer vlanId) {
        this.vlanId = vlanId;
    }

    public String getRd() {
        return rd;
    }

    public void setRd(String rd) {
        this.rd = rd;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public Long getChildServiceId() {
        return childServiceId;
    }

    public void setChildServiceId(Long childServiceId) {
        this.childServiceId = childServiceId;
    }

    public Long getChildNtuId() {
        return childNtuId;
    }

    public void setChildNtuId(Long childNtuId) {
        this.childNtuId = childNtuId;
    }

    public String getRealmIp() {
        return realmIp;
    }

    public void setRealmIp(String realmIp) {
        this.realmIp = realmIp;
    }

    public InternetTypeEnum getInternetType() {
        return internetType;
    }

    public void setInternetType(InternetTypeEnum internetType) {
        this.internetType = internetType;
    }

    public ConnectTypeEnum getType() {
        return type;
    }

    public void setType(ConnectTypeEnum type) {
        this.type = type;
    }

    public Long getServiceConfigurationId() {
        return serviceConfigurationId;
    }

    public void setServiceConfigurationId(Long serviceConfigurationId) {
        this.serviceConfigurationId = serviceConfigurationId;
    }

    public String getServiceConfigurationName() {
        return serviceConfigurationName;
    }

    public void setServiceConfigurationName(String serviceConfigurationName) {
        this.serviceConfigurationName = serviceConfigurationName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CircuitVlanDTO)) {
            return false;
        }

        return id != null && id.equals(((CircuitVlanDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CircuitVlanDTO{" +
            "id=" + getId() +
            ", zone='" + getZone() + "'" +
            ", serviceKey='" + getServiceKey() + "'" +
            ", vlanId=" + getVlanId() +
            ", rd='" + getRd() + "'" +
            ", serviceId=" + getServiceId() +
            ", tenantName='" + getTenantName() + "'" +
            ", childServiceId=" + getChildServiceId() +
            ", childNtuId=" + getChildNtuId() +
            ", realmIp='" + getRealmIp() + "'" +
            ", internetType='" + getInternetType() + "'" +
            ", type='" + getType() + "'" +
            ", serviceConfigurationId=" + getServiceConfigurationId() +
            ", serviceConfigurationName='" + getServiceConfigurationName() + "'" +
            "}";
    }
}
