package com.activeport.web.service.dto;

import com.activeport.web.domain.enumeration.AddressSetupTypeEnum;
import com.activeport.web.domain.enumeration.NtuModeEnum;
import com.activeport.web.domain.enumeration.PortServiceTypeEnum;
import java.io.Serializable;

/**
 * A DTO for the {@link com.activeport.web.domain.DeviceConfiguration} entity.
 */
public class DeviceConfigurationDTO implements Serializable {
    private Long id;

    private String uid;

    private String description;

    private String serialNumber;

    private String hostName;

    private String loIp;

    private String firmwareVersion;

    private String endpoint;

    private String restUsername;

    private String restPassword;

    private Boolean restEnabled;

    private NtuModeEnum mode;

    private Integer defaultRate;

    private String subnet;

    private PortServiceTypeEnum deviceType;

    private AddressSetupTypeEnum addressSetupType;

    private Long ntutypeId;

    private String ntutypeModel;

    private Long configurationId;

    private String configurationName;

    private Long realmId;

    private String realmSubnet;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getLoIp() {
        return loIp;
    }

    public void setLoIp(String loIp) {
        this.loIp = loIp;
    }

    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    public void setFirmwareVersion(String firmwareVersion) {
        this.firmwareVersion = firmwareVersion;
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

    public NtuModeEnum getMode() {
        return mode;
    }

    public void setMode(NtuModeEnum mode) {
        this.mode = mode;
    }

    public Integer getDefaultRate() {
        return defaultRate;
    }

    public void setDefaultRate(Integer defaultRate) {
        this.defaultRate = defaultRate;
    }

    public String getSubnet() {
        return subnet;
    }

    public void setSubnet(String subnet) {
        this.subnet = subnet;
    }

    public PortServiceTypeEnum getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(PortServiceTypeEnum deviceType) {
        this.deviceType = deviceType;
    }

    public AddressSetupTypeEnum getAddressSetupType() {
        return addressSetupType;
    }

    public void setAddressSetupType(AddressSetupTypeEnum addressSetupType) {
        this.addressSetupType = addressSetupType;
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

    public Long getConfigurationId() {
        return configurationId;
    }

    public void setConfigurationId(Long templateConfigurationId) {
        this.configurationId = templateConfigurationId;
    }

    public String getConfigurationName() {
        return configurationName;
    }

    public void setConfigurationName(String templateConfigurationName) {
        this.configurationName = templateConfigurationName;
    }

    public Long getRealmId() {
        return realmId;
    }

    public void setRealmId(Long realmIpId) {
        this.realmId = realmIpId;
    }

    public String getRealmSubnet() {
        return realmSubnet;
    }

    public void setRealmSubnet(String realmIpSubnet) {
        this.realmSubnet = realmIpSubnet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DeviceConfigurationDTO)) {
            return false;
        }

        return id != null && id.equals(((DeviceConfigurationDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DeviceConfigurationDTO{" +
            "id=" + getId() +
            ", uid='" + getUid() + "'" +
            ", description='" + getDescription() + "'" +
            ", serialNumber='" + getSerialNumber() + "'" +
            ", hostName='" + getHostName() + "'" +
            ", loIp='" + getLoIp() + "'" +
            ", firmwareVersion='" + getFirmwareVersion() + "'" +
            ", endpoint='" + getEndpoint() + "'" +
            ", restUsername='" + getRestUsername() + "'" +
            ", restPassword='" + getRestPassword() + "'" +
            ", restEnabled='" + isRestEnabled() + "'" +
            ", mode='" + getMode() + "'" +
            ", defaultRate=" + getDefaultRate() +
            ", subnet='" + getSubnet() + "'" +
            ", deviceType='" + getDeviceType() + "'" +
            ", addressSetupType='" + getAddressSetupType() + "'" +
            ", ntutypeId=" + getNtutypeId() +
            ", ntutypeModel='" + getNtutypeModel() + "'" +
            ", configurationId=" + getConfigurationId() +
            ", configurationName='" + getConfigurationName() + "'" +
            ", realmId=" + getRealmId() +
            ", realmSubnet='" + getRealmSubnet() + "'" +
            "}";
    }
}
