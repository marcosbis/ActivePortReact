package com.activeport.web.domain;

import com.activeport.web.domain.enumeration.AddressSetupTypeEnum;
import com.activeport.web.domain.enumeration.NtuModeEnum;
import com.activeport.web.domain.enumeration.PortServiceTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A DeviceConfiguration.
 */
@Entity
@Table(name = "device_configuration")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DeviceConfiguration implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uid")
    private String uid;

    @Column(name = "description")
    private String description;

    @Column(name = "serial_number")
    private String serialNumber;

    @Column(name = "host_name")
    private String hostName;

    @Column(name = "lo_ip")
    private String loIp;

    @Column(name = "firmware_version")
    private String firmwareVersion;

    @Column(name = "endpoint")
    private String endpoint;

    @Column(name = "rest_username")
    private String restUsername;

    @Column(name = "rest_password")
    private String restPassword;

    @Column(name = "rest_enabled")
    private Boolean restEnabled;

    @Enumerated(EnumType.STRING)
    @Column(name = "mode")
    private NtuModeEnum mode;

    @Column(name = "default_rate")
    private Integer defaultRate;

    @Column(name = "subnet")
    private String subnet;

    @Enumerated(EnumType.STRING)
    @Column(name = "device_type")
    private PortServiceTypeEnum deviceType;

    @Enumerated(EnumType.STRING)
    @Column(name = "address_setup_type")
    private AddressSetupTypeEnum addressSetupType;

    @ManyToOne
    @JsonIgnoreProperties(value = "deviceConfigurations", allowSetters = true)
    private NtuType ntutype;

    @ManyToOne
    @JsonIgnoreProperties(value = "deviceConfigurations", allowSetters = true)
    private TemplateConfiguration configuration;

    @ManyToOne
    @JsonIgnoreProperties(value = "deviceConfigurations", allowSetters = true)
    private RealmIp realm;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public DeviceConfiguration uid(String uid) {
        this.uid = uid;
        return this;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDescription() {
        return description;
    }

    public DeviceConfiguration description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public DeviceConfiguration serialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
        return this;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getHostName() {
        return hostName;
    }

    public DeviceConfiguration hostName(String hostName) {
        this.hostName = hostName;
        return this;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getLoIp() {
        return loIp;
    }

    public DeviceConfiguration loIp(String loIp) {
        this.loIp = loIp;
        return this;
    }

    public void setLoIp(String loIp) {
        this.loIp = loIp;
    }

    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    public DeviceConfiguration firmwareVersion(String firmwareVersion) {
        this.firmwareVersion = firmwareVersion;
        return this;
    }

    public void setFirmwareVersion(String firmwareVersion) {
        this.firmwareVersion = firmwareVersion;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public DeviceConfiguration endpoint(String endpoint) {
        this.endpoint = endpoint;
        return this;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getRestUsername() {
        return restUsername;
    }

    public DeviceConfiguration restUsername(String restUsername) {
        this.restUsername = restUsername;
        return this;
    }

    public void setRestUsername(String restUsername) {
        this.restUsername = restUsername;
    }

    public String getRestPassword() {
        return restPassword;
    }

    public DeviceConfiguration restPassword(String restPassword) {
        this.restPassword = restPassword;
        return this;
    }

    public void setRestPassword(String restPassword) {
        this.restPassword = restPassword;
    }

    public Boolean isRestEnabled() {
        return restEnabled;
    }

    public DeviceConfiguration restEnabled(Boolean restEnabled) {
        this.restEnabled = restEnabled;
        return this;
    }

    public void setRestEnabled(Boolean restEnabled) {
        this.restEnabled = restEnabled;
    }

    public NtuModeEnum getMode() {
        return mode;
    }

    public DeviceConfiguration mode(NtuModeEnum mode) {
        this.mode = mode;
        return this;
    }

    public void setMode(NtuModeEnum mode) {
        this.mode = mode;
    }

    public Integer getDefaultRate() {
        return defaultRate;
    }

    public DeviceConfiguration defaultRate(Integer defaultRate) {
        this.defaultRate = defaultRate;
        return this;
    }

    public void setDefaultRate(Integer defaultRate) {
        this.defaultRate = defaultRate;
    }

    public String getSubnet() {
        return subnet;
    }

    public DeviceConfiguration subnet(String subnet) {
        this.subnet = subnet;
        return this;
    }

    public void setSubnet(String subnet) {
        this.subnet = subnet;
    }

    public PortServiceTypeEnum getDeviceType() {
        return deviceType;
    }

    public DeviceConfiguration deviceType(PortServiceTypeEnum deviceType) {
        this.deviceType = deviceType;
        return this;
    }

    public void setDeviceType(PortServiceTypeEnum deviceType) {
        this.deviceType = deviceType;
    }

    public AddressSetupTypeEnum getAddressSetupType() {
        return addressSetupType;
    }

    public DeviceConfiguration addressSetupType(AddressSetupTypeEnum addressSetupType) {
        this.addressSetupType = addressSetupType;
        return this;
    }

    public void setAddressSetupType(AddressSetupTypeEnum addressSetupType) {
        this.addressSetupType = addressSetupType;
    }

    public NtuType getNtutype() {
        return ntutype;
    }

    public DeviceConfiguration ntutype(NtuType ntuType) {
        this.ntutype = ntuType;
        return this;
    }

    public void setNtutype(NtuType ntuType) {
        this.ntutype = ntuType;
    }

    public TemplateConfiguration getConfiguration() {
        return configuration;
    }

    public DeviceConfiguration configuration(TemplateConfiguration templateConfiguration) {
        this.configuration = templateConfiguration;
        return this;
    }

    public void setConfiguration(TemplateConfiguration templateConfiguration) {
        this.configuration = templateConfiguration;
    }

    public RealmIp getRealm() {
        return realm;
    }

    public DeviceConfiguration realm(RealmIp realmIp) {
        this.realm = realmIp;
        return this;
    }

    public void setRealm(RealmIp realmIp) {
        this.realm = realmIp;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DeviceConfiguration)) {
            return false;
        }
        return id != null && id.equals(((DeviceConfiguration) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DeviceConfiguration{" +
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
            "}";
    }
}
