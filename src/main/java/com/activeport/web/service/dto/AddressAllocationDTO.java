package com.activeport.web.service.dto;

import com.activeport.web.domain.enumeration.AllocationTypeEnum;
import com.activeport.web.domain.enumeration.NtuModeEnum;
import com.activeport.web.domain.enumeration.PortServiceTypeEnum;
import java.io.Serializable;

/**
 * A DTO for the {@link com.activeport.web.domain.AddressAllocation} entity.
 */
public class AddressAllocationDTO implements Serializable {
    private Long id;

    private String subnet;

    private String deviceName;

    private NtuModeEnum deviceMode;

    private Long deviceId;

    private PortServiceTypeEnum deviceType;

    private String description;

    private AllocationTypeEnum allocationType;

    private String serialNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubnet() {
        return subnet;
    }

    public void setSubnet(String subnet) {
        this.subnet = subnet;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public NtuModeEnum getDeviceMode() {
        return deviceMode;
    }

    public void setDeviceMode(NtuModeEnum deviceMode) {
        this.deviceMode = deviceMode;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public PortServiceTypeEnum getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(PortServiceTypeEnum deviceType) {
        this.deviceType = deviceType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AllocationTypeEnum getAllocationType() {
        return allocationType;
    }

    public void setAllocationType(AllocationTypeEnum allocationType) {
        this.allocationType = allocationType;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AddressAllocationDTO)) {
            return false;
        }

        return id != null && id.equals(((AddressAllocationDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AddressAllocationDTO{" +
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
