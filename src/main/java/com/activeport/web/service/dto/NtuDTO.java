package com.activeport.web.service.dto;

import com.activeport.web.domain.enumeration.NtuModeEnum;
import java.io.Serializable;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.activeport.web.domain.Ntu} entity.
 */
public class NtuDTO implements Serializable {
    private Long id;

    private String name;

    private String hostId;

    private String description;

    private String serialNumber;

    private String ipAddress;

    private String companyName;

    private String hostName;

    private String loIp;

    private String category;

    private String alarmEmailAddresses;

    private Boolean metricCollection;

    private Boolean securityCollection;

    private Boolean alarmCollection;

    private Boolean trendCollection;

    private Boolean syslogCollection;

    private Boolean configBackup;

    private Boolean updateOneconfig;

    private String firmwareVersion;

    private String runningConfig;

    private String configId;

    private String endpoint;

    private String restUsername;

    private String restPassword;

    private Boolean restEnabled;

    private Boolean autoRollback;

    private NtuModeEnum mode;

    private String timeZone;

    private Integer minRate;

    private Integer maxRate;

    private Integer defaultRate;

    private Boolean enableBod;

    @NotNull
    @Min(value = 5)
    @Max(value = 1000)
    private Integer burstTime;

    private String secondlinkPort;

    private Long deviceConfigurationId;

    private String deviceConfigurationSerialNumber;

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

    public String getLoIp() {
        return loIp;
    }

    public void setLoIp(String loIp) {
        this.loIp = loIp;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAlarmEmailAddresses() {
        return alarmEmailAddresses;
    }

    public void setAlarmEmailAddresses(String alarmEmailAddresses) {
        this.alarmEmailAddresses = alarmEmailAddresses;
    }

    public Boolean isMetricCollection() {
        return metricCollection;
    }

    public void setMetricCollection(Boolean metricCollection) {
        this.metricCollection = metricCollection;
    }

    public Boolean isSecurityCollection() {
        return securityCollection;
    }

    public void setSecurityCollection(Boolean securityCollection) {
        this.securityCollection = securityCollection;
    }

    public Boolean isAlarmCollection() {
        return alarmCollection;
    }

    public void setAlarmCollection(Boolean alarmCollection) {
        this.alarmCollection = alarmCollection;
    }

    public Boolean isTrendCollection() {
        return trendCollection;
    }

    public void setTrendCollection(Boolean trendCollection) {
        this.trendCollection = trendCollection;
    }

    public Boolean isSyslogCollection() {
        return syslogCollection;
    }

    public void setSyslogCollection(Boolean syslogCollection) {
        this.syslogCollection = syslogCollection;
    }

    public Boolean isConfigBackup() {
        return configBackup;
    }

    public void setConfigBackup(Boolean configBackup) {
        this.configBackup = configBackup;
    }

    public Boolean isUpdateOneconfig() {
        return updateOneconfig;
    }

    public void setUpdateOneconfig(Boolean updateOneconfig) {
        this.updateOneconfig = updateOneconfig;
    }

    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    public void setFirmwareVersion(String firmwareVersion) {
        this.firmwareVersion = firmwareVersion;
    }

    public String getRunningConfig() {
        return runningConfig;
    }

    public void setRunningConfig(String runningConfig) {
        this.runningConfig = runningConfig;
    }

    public String getConfigId() {
        return configId;
    }

    public void setConfigId(String configId) {
        this.configId = configId;
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

    public NtuModeEnum getMode() {
        return mode;
    }

    public void setMode(NtuModeEnum mode) {
        this.mode = mode;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public Integer getMinRate() {
        return minRate;
    }

    public void setMinRate(Integer minRate) {
        this.minRate = minRate;
    }

    public Integer getMaxRate() {
        return maxRate;
    }

    public void setMaxRate(Integer maxRate) {
        this.maxRate = maxRate;
    }

    public Integer getDefaultRate() {
        return defaultRate;
    }

    public void setDefaultRate(Integer defaultRate) {
        this.defaultRate = defaultRate;
    }

    public Boolean isEnableBod() {
        return enableBod;
    }

    public void setEnableBod(Boolean enableBod) {
        this.enableBod = enableBod;
    }

    public Integer getBurstTime() {
        return burstTime;
    }

    public void setBurstTime(Integer burstTime) {
        this.burstTime = burstTime;
    }

    public String getSecondlinkPort() {
        return secondlinkPort;
    }

    public void setSecondlinkPort(String secondlinkPort) {
        this.secondlinkPort = secondlinkPort;
    }

    public Long getDeviceConfigurationId() {
        return deviceConfigurationId;
    }

    public void setDeviceConfigurationId(Long deviceConfigurationId) {
        this.deviceConfigurationId = deviceConfigurationId;
    }

    public String getDeviceConfigurationSerialNumber() {
        return deviceConfigurationSerialNumber;
    }

    public void setDeviceConfigurationSerialNumber(String deviceConfigurationSerialNumber) {
        this.deviceConfigurationSerialNumber = deviceConfigurationSerialNumber;
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
        if (!(o instanceof NtuDTO)) {
            return false;
        }

        return id != null && id.equals(((NtuDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NtuDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", hostId='" + getHostId() + "'" +
            ", description='" + getDescription() + "'" +
            ", serialNumber='" + getSerialNumber() + "'" +
            ", ipAddress='" + getIpAddress() + "'" +
            ", companyName='" + getCompanyName() + "'" +
            ", hostName='" + getHostName() + "'" +
            ", loIp='" + getLoIp() + "'" +
            ", category='" + getCategory() + "'" +
            ", alarmEmailAddresses='" + getAlarmEmailAddresses() + "'" +
            ", metricCollection='" + isMetricCollection() + "'" +
            ", securityCollection='" + isSecurityCollection() + "'" +
            ", alarmCollection='" + isAlarmCollection() + "'" +
            ", trendCollection='" + isTrendCollection() + "'" +
            ", syslogCollection='" + isSyslogCollection() + "'" +
            ", configBackup='" + isConfigBackup() + "'" +
            ", updateOneconfig='" + isUpdateOneconfig() + "'" +
            ", firmwareVersion='" + getFirmwareVersion() + "'" +
            ", runningConfig='" + getRunningConfig() + "'" +
            ", configId='" + getConfigId() + "'" +
            ", endpoint='" + getEndpoint() + "'" +
            ", restUsername='" + getRestUsername() + "'" +
            ", restPassword='" + getRestPassword() + "'" +
            ", restEnabled='" + isRestEnabled() + "'" +
            ", autoRollback='" + isAutoRollback() + "'" +
            ", mode='" + getMode() + "'" +
            ", timeZone='" + getTimeZone() + "'" +
            ", minRate=" + getMinRate() +
            ", maxRate=" + getMaxRate() +
            ", defaultRate=" + getDefaultRate() +
            ", enableBod='" + isEnableBod() + "'" +
            ", burstTime=" + getBurstTime() +
            ", secondlinkPort='" + getSecondlinkPort() + "'" +
            ", deviceConfigurationId=" + getDeviceConfigurationId() +
            ", deviceConfigurationSerialNumber='" + getDeviceConfigurationSerialNumber() + "'" +
            ", ntutypeId=" + getNtutypeId() +
            ", ntutypeModel='" + getNtutypeModel() + "'" +
            ", ntutypeId=" + getNtutypeId() +
            ", ntutypeModel='" + getNtutypeModel() + "'" +
            "}";
    }
}
