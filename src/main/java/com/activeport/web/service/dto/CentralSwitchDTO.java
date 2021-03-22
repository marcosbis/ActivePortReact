package com.activeport.web.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.activeport.web.domain.CentralSwitch} entity.
 */
public class CentralSwitchDTO implements Serializable {
    private Long id;

    private String name;

    private String hostId;

    private String description;

    private String serialNumber;

    private String ipAddress;

    private String companyName;

    private String hostName;

    private Boolean configBackup;

    private Integer poolVlanStart;

    private Integer poolVlanEnd;

    private String endpoint;

    private String restUsername;

    private String restPassword;

    private Boolean restEnabled;

    private Boolean autoRollback;

    private Boolean feign;

    private Long locationId;

    private String locationCode;

    private Long ntutypeId;

    private String ntutypeModel;

    private Long ntutypeId;

    private String ntutypeModel;

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

    public String getHostId() {
        return hostId;
    }

    public void setHostId(String hostId) {
        this.hostId = hostId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public Boolean isConfigBackup() {
        return configBackup;
    }

    public void setConfigBackup(Boolean configBackup) {
        this.configBackup = configBackup;
    }

    public Integer getPoolVlanStart() {
        return poolVlanStart;
    }

    public void setPoolVlanStart(Integer poolVlanStart) {
        this.poolVlanStart = poolVlanStart;
    }

    public Integer getPoolVlanEnd() {
        return poolVlanEnd;
    }

    public void setPoolVlanEnd(Integer poolVlanEnd) {
        this.poolVlanEnd = poolVlanEnd;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getRestUsername() {
        return restUsername;
    }

    public void setRestUsername(String restUsername) {
        this.restUsername = restUsername;
    }

    public String getRestPassword() {
        return restPassword;
    }

    public void setRestPassword(String restPassword) {
        this.restPassword = restPassword;
    }

    public Boolean isRestEnabled() {
        return restEnabled;
    }

    public void setRestEnabled(Boolean restEnabled) {
        this.restEnabled = restEnabled;
    }

    public Boolean isAutoRollback() {
        return autoRollback;
    }

    public void setAutoRollback(Boolean autoRollback) {
        this.autoRollback = autoRollback;
    }

    public Boolean isFeign() {
        return feign;
    }

    public void setFeign(Boolean feign) {
        this.feign = feign;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public Long getNtutypeId() {
        return ntutypeId;
    }

    public void setNtutypeId(Long ntuTypeId) {
        this.ntutypeId = ntuTypeId;
    }

    public String getNtutypeModel() {
        return ntutypeModel;
    }

    public void setNtutypeModel(String ntuTypeModel) {
        this.ntutypeModel = ntuTypeModel;
    }

    public Long getNtutypeId() {
        return ntutypeId;
    }

    public void setNtutypeId(Long ntuTypeId) {
        this.ntutypeId = ntuTypeId;
    }

    public String getNtutypeModel() {
        return ntutypeModel;
    }

    public void setNtutypeModel(String ntuTypeModel) {
        this.ntutypeModel = ntuTypeModel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CentralSwitchDTO)) {
            return false;
        }

        return id != null && id.equals(((CentralSwitchDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CentralSwitchDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", hostId='" + getHostId() + "'" +
            ", description='" + getDescription() + "'" +
            ", serialNumber='" + getSerialNumber() + "'" +
            ", ipAddress='" + getIpAddress() + "'" +
            ", companyName='" + getCompanyName() + "'" +
            ", hostName='" + getHostName() + "'" +
            ", configBackup='" + isConfigBackup() + "'" +
            ", poolVlanStart=" + getPoolVlanStart() +
            ", poolVlanEnd=" + getPoolVlanEnd() +
            ", endpoint='" + getEndpoint() + "'" +
            ", restUsername='" + getRestUsername() + "'" +
            ", restPassword='" + getRestPassword() + "'" +
            ", restEnabled='" + isRestEnabled() + "'" +
            ", autoRollback='" + isAutoRollback() + "'" +
            ", feign='" + isFeign() + "'" +
            ", locationId=" + getLocationId() +
            ", locationCode='" + getLocationCode() + "'" +
            ", ntutypeId=" + getNtutypeId() +
            ", ntutypeModel='" + getNtutypeModel() + "'" +
            ", ntutypeId=" + getNtutypeId() +
            ", ntutypeModel='" + getNtutypeModel() + "'" +
            "}";
    }
}
