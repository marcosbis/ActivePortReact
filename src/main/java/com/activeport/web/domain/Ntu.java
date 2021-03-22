package com.activeport.web.domain;

import com.activeport.web.domain.enumeration.NtuModeEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Ntu.
 */
@Entity
@Table(name = "ntu")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Ntu implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "host_id")
    private String hostId;

    @Column(name = "description")
    private String description;

    @Column(name = "serial_number")
    private String serialNumber;

    @Column(name = "ip_address")
    private String ipAddress;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "host_name")
    private String hostName;

    @Column(name = "lo_ip")
    private String loIp;

    @Column(name = "category")
    private String category;

    @Column(name = "alarm_email_addresses")
    private String alarmEmailAddresses;

    @Column(name = "metric_collection")
    private Boolean metricCollection;

    @Column(name = "security_collection")
    private Boolean securityCollection;

    @Column(name = "alarm_collection")
    private Boolean alarmCollection;

    @Column(name = "trend_collection")
    private Boolean trendCollection;

    @Column(name = "syslog_collection")
    private Boolean syslogCollection;

    @Column(name = "config_backup")
    private Boolean configBackup;

    @Column(name = "update_oneconfig")
    private Boolean updateOneconfig;

    @Column(name = "firmware_version")
    private String firmwareVersion;

    @Column(name = "running_config")
    private String runningConfig;

    @Column(name = "config_id")
    private String configId;

    @Column(name = "endpoint")
    private String endpoint;

    @Column(name = "rest_username")
    private String restUsername;

    @Column(name = "rest_password")
    private String restPassword;

    @Column(name = "rest_enabled")
    private Boolean restEnabled;

    @Column(name = "auto_rollback")
    private Boolean autoRollback;

    @Enumerated(EnumType.STRING)
    @Column(name = "mode")
    private NtuModeEnum mode;

    @Column(name = "time_zone")
    private String timeZone;

    @Column(name = "min_rate")
    private Integer minRate;

    @Column(name = "max_rate")
    private Integer maxRate;

    @Column(name = "default_rate")
    private Integer defaultRate;

    @Column(name = "enable_bod")
    private Boolean enableBod;

    @NotNull
    @Min(value = 5)
    @Max(value = 1000)
    @Column(name = "burst_time", nullable = false)
    private Integer burstTime;

    @Column(name = "secondlink_port")
    private String secondlinkPort;

    @OneToOne
    @JoinColumn(unique = true)
    private DeviceConfiguration deviceConfiguration;

    @OneToMany(mappedBy = "ntu")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<NtuPort> ports = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "ntus", allowSetters = true)
    private NtuType ntutype;

    @ManyToOne
    @JsonIgnoreProperties(value = "ntus", allowSetters = true)
    private NtuType ntutype;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Ntu name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHostId() {
        return hostId;
    }

    public Ntu hostId(String hostId) {
        this.hostId = hostId;
        return this;
    }

    public void setHostId(String hostId) {
        this.hostId = hostId;
    }

    public String getDescription() {
        return description;
    }

    public Ntu description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public Ntu serialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
        return this;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public Ntu ipAddress(String ipAddress) {
        this.ipAddress = ipAddress;
        return this;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getCompanyName() {
        return companyName;
    }

    public Ntu companyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getHostName() {
        return hostName;
    }

    public Ntu hostName(String hostName) {
        this.hostName = hostName;
        return this;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getLoIp() {
        return loIp;
    }

    public Ntu loIp(String loIp) {
        this.loIp = loIp;
        return this;
    }

    public void setLoIp(String loIp) {
        this.loIp = loIp;
    }

    public String getCategory() {
        return category;
    }

    public Ntu category(String category) {
        this.category = category;
        return this;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAlarmEmailAddresses() {
        return alarmEmailAddresses;
    }

    public Ntu alarmEmailAddresses(String alarmEmailAddresses) {
        this.alarmEmailAddresses = alarmEmailAddresses;
        return this;
    }

    public void setAlarmEmailAddresses(String alarmEmailAddresses) {
        this.alarmEmailAddresses = alarmEmailAddresses;
    }

    public Boolean isMetricCollection() {
        return metricCollection;
    }

    public Ntu metricCollection(Boolean metricCollection) {
        this.metricCollection = metricCollection;
        return this;
    }

    public void setMetricCollection(Boolean metricCollection) {
        this.metricCollection = metricCollection;
    }

    public Boolean isSecurityCollection() {
        return securityCollection;
    }

    public Ntu securityCollection(Boolean securityCollection) {
        this.securityCollection = securityCollection;
        return this;
    }

    public void setSecurityCollection(Boolean securityCollection) {
        this.securityCollection = securityCollection;
    }

    public Boolean isAlarmCollection() {
        return alarmCollection;
    }

    public Ntu alarmCollection(Boolean alarmCollection) {
        this.alarmCollection = alarmCollection;
        return this;
    }

    public void setAlarmCollection(Boolean alarmCollection) {
        this.alarmCollection = alarmCollection;
    }

    public Boolean isTrendCollection() {
        return trendCollection;
    }

    public Ntu trendCollection(Boolean trendCollection) {
        this.trendCollection = trendCollection;
        return this;
    }

    public void setTrendCollection(Boolean trendCollection) {
        this.trendCollection = trendCollection;
    }

    public Boolean isSyslogCollection() {
        return syslogCollection;
    }

    public Ntu syslogCollection(Boolean syslogCollection) {
        this.syslogCollection = syslogCollection;
        return this;
    }

    public void setSyslogCollection(Boolean syslogCollection) {
        this.syslogCollection = syslogCollection;
    }

    public Boolean isConfigBackup() {
        return configBackup;
    }

    public Ntu configBackup(Boolean configBackup) {
        this.configBackup = configBackup;
        return this;
    }

    public void setConfigBackup(Boolean configBackup) {
        this.configBackup = configBackup;
    }

    public Boolean isUpdateOneconfig() {
        return updateOneconfig;
    }

    public Ntu updateOneconfig(Boolean updateOneconfig) {
        this.updateOneconfig = updateOneconfig;
        return this;
    }

    public void setUpdateOneconfig(Boolean updateOneconfig) {
        this.updateOneconfig = updateOneconfig;
    }

    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    public Ntu firmwareVersion(String firmwareVersion) {
        this.firmwareVersion = firmwareVersion;
        return this;
    }

    public void setFirmwareVersion(String firmwareVersion) {
        this.firmwareVersion = firmwareVersion;
    }

    public String getRunningConfig() {
        return runningConfig;
    }

    public Ntu runningConfig(String runningConfig) {
        this.runningConfig = runningConfig;
        return this;
    }

    public void setRunningConfig(String runningConfig) {
        this.runningConfig = runningConfig;
    }

    public String getConfigId() {
        return configId;
    }

    public Ntu configId(String configId) {
        this.configId = configId;
        return this;
    }

    public void setConfigId(String configId) {
        this.configId = configId;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public Ntu endpoint(String endpoint) {
        this.endpoint = endpoint;
        return this;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getRestUsername() {
        return restUsername;
    }

    public Ntu restUsername(String restUsername) {
        this.restUsername = restUsername;
        return this;
    }

    public void setRestUsername(String restUsername) {
        this.restUsername = restUsername;
    }

    public String getRestPassword() {
        return restPassword;
    }

    public Ntu restPassword(String restPassword) {
        this.restPassword = restPassword;
        return this;
    }

    public void setRestPassword(String restPassword) {
        this.restPassword = restPassword;
    }

    public Boolean isRestEnabled() {
        return restEnabled;
    }

    public Ntu restEnabled(Boolean restEnabled) {
        this.restEnabled = restEnabled;
        return this;
    }

    public void setRestEnabled(Boolean restEnabled) {
        this.restEnabled = restEnabled;
    }

    public Boolean isAutoRollback() {
        return autoRollback;
    }

    public Ntu autoRollback(Boolean autoRollback) {
        this.autoRollback = autoRollback;
        return this;
    }

    public void setAutoRollback(Boolean autoRollback) {
        this.autoRollback = autoRollback;
    }

    public NtuModeEnum getMode() {
        return mode;
    }

    public Ntu mode(NtuModeEnum mode) {
        this.mode = mode;
        return this;
    }

    public void setMode(NtuModeEnum mode) {
        this.mode = mode;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public Ntu timeZone(String timeZone) {
        this.timeZone = timeZone;
        return this;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public Integer getMinRate() {
        return minRate;
    }

    public Ntu minRate(Integer minRate) {
        this.minRate = minRate;
        return this;
    }

    public void setMinRate(Integer minRate) {
        this.minRate = minRate;
    }

    public Integer getMaxRate() {
        return maxRate;
    }

    public Ntu maxRate(Integer maxRate) {
        this.maxRate = maxRate;
        return this;
    }

    public void setMaxRate(Integer maxRate) {
        this.maxRate = maxRate;
    }

    public Integer getDefaultRate() {
        return defaultRate;
    }

    public Ntu defaultRate(Integer defaultRate) {
        this.defaultRate = defaultRate;
        return this;
    }

    public void setDefaultRate(Integer defaultRate) {
        this.defaultRate = defaultRate;
    }

    public Boolean isEnableBod() {
        return enableBod;
    }

    public Ntu enableBod(Boolean enableBod) {
        this.enableBod = enableBod;
        return this;
    }

    public void setEnableBod(Boolean enableBod) {
        this.enableBod = enableBod;
    }

    public Integer getBurstTime() {
        return burstTime;
    }

    public Ntu burstTime(Integer burstTime) {
        this.burstTime = burstTime;
        return this;
    }

    public void setBurstTime(Integer burstTime) {
        this.burstTime = burstTime;
    }

    public String getSecondlinkPort() {
        return secondlinkPort;
    }

    public Ntu secondlinkPort(String secondlinkPort) {
        this.secondlinkPort = secondlinkPort;
        return this;
    }

    public void setSecondlinkPort(String secondlinkPort) {
        this.secondlinkPort = secondlinkPort;
    }

    public DeviceConfiguration getDeviceConfiguration() {
        return deviceConfiguration;
    }

    public Ntu deviceConfiguration(DeviceConfiguration deviceConfiguration) {
        this.deviceConfiguration = deviceConfiguration;
        return this;
    }

    public void setDeviceConfiguration(DeviceConfiguration deviceConfiguration) {
        this.deviceConfiguration = deviceConfiguration;
    }

    public Set<NtuPort> getPorts() {
        return ports;
    }

    public Ntu ports(Set<NtuPort> ntuPorts) {
        this.ports = ntuPorts;
        return this;
    }

    public Ntu addPort(NtuPort ntuPort) {
        this.ports.add(ntuPort);
        ntuPort.setNtu(this);
        return this;
    }

    public Ntu removePort(NtuPort ntuPort) {
        this.ports.remove(ntuPort);
        ntuPort.setNtu(null);
        return this;
    }

    public void setPorts(Set<NtuPort> ntuPorts) {
        this.ports = ntuPorts;
    }

    public NtuType getNtutype() {
        return ntutype;
    }

    public Ntu ntutype(NtuType ntuType) {
        this.ntutype = ntuType;
        return this;
    }

    public void setNtutype(NtuType ntuType) {
        this.ntutype = ntuType;
    }

    public NtuType getNtutype() {
        return ntutype;
    }

    public Ntu ntutype(NtuType ntuType) {
        this.ntutype = ntuType;
        return this;
    }

    public void setNtutype(NtuType ntuType) {
        this.ntutype = ntuType;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Ntu)) {
            return false;
        }
        return id != null && id.equals(((Ntu) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Ntu{" +
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
            "}";
    }
}
