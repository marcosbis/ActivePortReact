package com.activeport.web.service.dto;

import com.activeport.web.domain.enumeration.PortTypeEnum;
import java.io.Serializable;

/**
 * A DTO for the {@link com.activeport.web.domain.NtuPort} entity.
 */
public class NtuPortDTO implements Serializable {
    private Long id;

    private String name;

    private String label;

    private String description;

    private String mac;

    private Integer port;

    private PortTypeEnum portType;

    private Boolean trunk;

    private Boolean jumbo;

    private String portSpeed;

    private Boolean internetPort;

    private String uplinkPort;

    private Long ntuId;

    private String ntuName;

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

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public PortTypeEnum getPortType() {
        return portType;
    }

    public void setPortType(PortTypeEnum portType) {
        this.portType = portType;
    }

    public Boolean isTrunk() {
        return trunk;
    }

    public void setTrunk(Boolean trunk) {
        this.trunk = trunk;
    }

    public Boolean isJumbo() {
        return jumbo;
    }

    public void setJumbo(Boolean jumbo) {
        this.jumbo = jumbo;
    }

    public String getPortSpeed() {
        return portSpeed;
    }

    public void setPortSpeed(String portSpeed) {
        this.portSpeed = portSpeed;
    }

    public Boolean isInternetPort() {
        return internetPort;
    }

    public void setInternetPort(Boolean internetPort) {
        this.internetPort = internetPort;
    }

    public String getUplinkPort() {
        return uplinkPort;
    }

    public void setUplinkPort(String uplinkPort) {
        this.uplinkPort = uplinkPort;
    }

    public Long getNtuId() {
        return ntuId;
    }

    public void setNtuId(Long ntuId) {
        this.ntuId = ntuId;
    }

    public String getNtuName() {
        return ntuName;
    }

    public void setNtuName(String ntuName) {
        this.ntuName = ntuName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NtuPortDTO)) {
            return false;
        }

        return id != null && id.equals(((NtuPortDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NtuPortDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", label='" + getLabel() + "'" +
            ", description='" + getDescription() + "'" +
            ", mac='" + getMac() + "'" +
            ", port=" + getPort() +
            ", portType='" + getPortType() + "'" +
            ", trunk='" + isTrunk() + "'" +
            ", jumbo='" + isJumbo() + "'" +
            ", portSpeed='" + getPortSpeed() + "'" +
            ", internetPort='" + isInternetPort() + "'" +
            ", uplinkPort='" + getUplinkPort() + "'" +
            ", ntuId=" + getNtuId() +
            ", ntuName='" + getNtuName() + "'" +
            "}";
    }
}
