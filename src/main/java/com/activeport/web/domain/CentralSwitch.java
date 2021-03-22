package com.activeport.web.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A CentralSwitch.
 */
@Entity
@Table(name = "central_switch")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CentralSwitch implements Serializable {
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

    @Column(name = "config_backup")
    private Boolean configBackup;

    @Column(name = "pool_vlan_start")
    private Integer poolVlanStart;

    @Column(name = "pool_vlan_end")
    private Integer poolVlanEnd;

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

    @Column(name = "feign")
    private Boolean feign;

    @ManyToOne
    @JsonIgnoreProperties(value = "centralSwitches", allowSetters = true)
    private Location location;

    @ManyToOne
    @JsonIgnoreProperties(value = "switches", allowSetters = true)
    private NtuType ntutype;

    @ManyToOne
    @JsonIgnoreProperties(value = "switches", allowSetters = true)
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

    public CentralSwitch name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHostId() {
        return hostId;
    }

    public CentralSwitch hostId(String hostId) {
        this.hostId = hostId;
        return this;
    }

    public void setHostId(String hostId) {
        this.hostId = hostId;
    }

    public String getDescription() {
        return description;
    }

    public CentralSwitch description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public CentralSwitch serialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
        return this;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public CentralSwitch ipAddress(String ipAddress) {
        this.ipAddress = ipAddress;
        return this;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getCompanyName() {
        return companyName;
    }

    public CentralSwitch companyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getHostName() {
        return hostName;
    }

    public CentralSwitch hostName(String hostName) {
        this.hostName = hostName;
        return this;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public Boolean isConfigBackup() {
        return configBackup;
    }

    public CentralSwitch configBackup(Boolean configBackup) {
        this.configBackup = configBackup;
        return this;
    }

    public void setConfigBackup(Boolean configBackup) {
        this.configBackup = configBackup;
    }

    public Integer getPoolVlanStart() {
        return poolVlanStart;
    }

    public CentralSwitch poolVlanStart(Integer poolVlanStart) {
        this.poolVlanStart = poolVlanStart;
        return this;
    }

    public void setPoolVlanStart(Integer poolVlanStart) {
        this.poolVlanStart = poolVlanStart;
    }

    public Integer getPoolVlanEnd() {
        return poolVlanEnd;
    }

    public CentralSwitch poolVlanEnd(Integer poolVlanEnd) {
        this.poolVlanEnd = poolVlanEnd;
        return this;
    }

    public void setPoolVlanEnd(Integer poolVlanEnd) {
        this.poolVlanEnd = poolVlanEnd;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public CentralSwitch endpoint(String endpoint) {
        this.endpoint = endpoint;
        return this;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getRestUsername() {
        return restUsername;
    }

    public CentralSwitch restUsername(String restUsername) {
        this.restUsername = restUsername;
        return this;
    }

    public void setRestUsername(String restUsername) {
        this.restUsername = restUsername;
    }

    public String getRestPassword() {
        return restPassword;
    }

    public CentralSwitch restPassword(String restPassword) {
        this.restPassword = restPassword;
        return this;
    }

    public void setRestPassword(String restPassword) {
        this.restPassword = restPassword;
    }

    public Boolean isRestEnabled() {
        return restEnabled;
    }

    public CentralSwitch restEnabled(Boolean restEnabled) {
        this.restEnabled = restEnabled;
        return this;
    }

    public void setRestEnabled(Boolean restEnabled) {
        this.restEnabled = restEnabled;
    }

    public Boolean isAutoRollback() {
        return autoRollback;
    }

    public CentralSwitch autoRollback(Boolean autoRollback) {
        this.autoRollback = autoRollback;
        return this;
    }

    public void setAutoRollback(Boolean autoRollback) {
        this.autoRollback = autoRollback;
    }

    public Boolean isFeign() {
        return feign;
    }

    public CentralSwitch feign(Boolean feign) {
        this.feign = feign;
        return this;
    }

    public void setFeign(Boolean feign) {
        this.feign = feign;
    }

    public Location getLocation() {
        return location;
    }

    public CentralSwitch location(Location location) {
        this.location = location;
        return this;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public NtuType getNtutype() {
        return ntutype;
    }

    public CentralSwitch ntutype(NtuType ntuType) {
        this.ntutype = ntuType;
        return this;
    }

    public void setNtutype(NtuType ntuType) {
        this.ntutype = ntuType;
    }

    public NtuType getNtutype() {
        return ntutype;
    }

    public CentralSwitch ntutype(NtuType ntuType) {
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
        if (!(o instanceof CentralSwitch)) {
            return false;
        }
        return id != null && id.equals(((CentralSwitch) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CentralSwitch{" +
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
            "}";
    }
}
