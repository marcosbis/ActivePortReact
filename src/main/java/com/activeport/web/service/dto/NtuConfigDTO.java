package com.activeport.web.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.activeport.web.domain.NtuConfig} entity.
 */
public class NtuConfigDTO implements Serializable {
    private Long id;

    private String serialNumber;

    private String name;

    private String firmwareVersion;

    private Long ntuId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    public void setFirmwareVersion(String firmwareVersion) {
        this.firmwareVersion = firmwareVersion;
    }

    public Long getNtuId() {
        return ntuId;
    }

    public void setNtuId(Long ntuId) {
        this.ntuId = ntuId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NtuConfigDTO)) {
            return false;
        }

        return id != null && id.equals(((NtuConfigDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NtuConfigDTO{" +
            "id=" + getId() +
            ", serialNumber='" + getSerialNumber() + "'" +
            ", name='" + getName() + "'" +
            ", firmwareVersion='" + getFirmwareVersion() + "'" +
            ", ntuId=" + getNtuId() +
            "}";
    }
}
