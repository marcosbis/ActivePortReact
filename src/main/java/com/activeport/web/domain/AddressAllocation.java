package com.activeport.web.domain;

import com.activeport.web.domain.enumeration.AllocationTypeEnum;
import com.activeport.web.domain.enumeration.NtuModeEnum;
import com.activeport.web.domain.enumeration.PortServiceTypeEnum;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A AddressAllocation.
 */
@Entity
@Table(name = "address_allocation")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AddressAllocation implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "subnet")
    private String subnet;

    @Column(name = "device_name")
    private String deviceName;

    @Enumerated(EnumType.STRING)
    @Column(name = "device_mode")
    private NtuModeEnum deviceMode;

    @Column(name = "device_id")
    private Long deviceId;

    @Enumerated(EnumType.STRING)
    @Column(name = "device_type")
    private PortServiceTypeEnum deviceType;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "allocation_type")
    private AllocationTypeEnum allocationType;

    @Column(name = "serial_number")
    private String serialNumber;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubnet() {
        return subnet;
    }

    public AddressAllocation subnet(String subnet) {
        this.subnet = subnet;
        return this;
    }

    public void setSubnet(String subnet) {
        this.subnet = subnet;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public AddressAllocation deviceName(String deviceName) {
        this.deviceName = deviceName;
        return this;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public NtuModeEnum getDeviceMode() {
        return deviceMode;
    }

    public AddressAllocation deviceMode(NtuModeEnum deviceMode) {
        this.deviceMode = deviceMode;
        return this;
    }

    public void setDeviceMode(NtuModeEnum deviceMode) {
        this.deviceMode = deviceMode;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public AddressAllocation deviceId(Long deviceId) {
        this.deviceId = deviceId;
        return this;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public PortServiceTypeEnum getDeviceType() {
        return deviceType;
    }

    public AddressAllocation deviceType(PortServiceTypeEnum deviceType) {
        this.deviceType = deviceType;
        return this;
    }

    public void setDeviceType(PortServiceTypeEnum deviceType) {
        this.deviceType = deviceType;
    }

    public String getDescription() {
        return description;
    }

    public AddressAllocation description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AllocationTypeEnum getAllocationType() {
        return allocationType;
    }

    public AddressAllocation allocationType(AllocationTypeEnum allocationType) {
        this.allocationType = allocationType;
        return this;
    }

    public void setAllocationType(AllocationTypeEnum allocationType) {
        this.allocationType = allocationType;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public AddressAllocation serialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
        return this;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AddressAllocation)) {
            return false;
        }
        return id != null && id.equals(((AddressAllocation) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AddressAllocation{" +
            "id=" + getId() +
            ", subnet='" + getSubnet() + "'" +
            ", deviceName='" + getDeviceName() + "'" +
            ", deviceMode='" + getDeviceMode() + "'" +
            ", deviceId=" + getDeviceId() +
            ", deviceType='" + getDeviceType() + "'" +
            ", description='" + getDescription() + "'" +
            ", allocationType='" + getAllocationType() + "'" +
            ", serialNumber='" + getSerialNumber() + "'" +
            "}";
    }
}
