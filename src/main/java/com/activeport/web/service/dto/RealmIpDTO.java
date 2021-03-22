package com.activeport.web.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.activeport.web.domain.RealmIp} entity.
 */
public class RealmIpDTO implements Serializable {
    private Long id;

    private String subnet;

    private String name;

    private String desciption;

    private String mask;

    private String subnetSize;

    private String firstIp;

    private String lastIp;

    private String broadcast;

    private String cir;

    private String ipsecGateway;

    private Long locationId;

    private String locationCode;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    public String getMask() {
        return mask;
    }

    public void setMask(String mask) {
        this.mask = mask;
    }

    public String getSubnetSize() {
        return subnetSize;
    }

    public void setSubnetSize(String subnetSize) {
        this.subnetSize = subnetSize;
    }

    public String getFirstIp() {
        return firstIp;
    }

    public void setFirstIp(String firstIp) {
        this.firstIp = firstIp;
    }

    public String getLastIp() {
        return lastIp;
    }

    public void setLastIp(String lastIp) {
        this.lastIp = lastIp;
    }

    public String getBroadcast() {
        return broadcast;
    }

    public void setBroadcast(String broadcast) {
        this.broadcast = broadcast;
    }

    public String getCir() {
        return cir;
    }

    public void setCir(String cir) {
        this.cir = cir;
    }

    public String getIpsecGateway() {
        return ipsecGateway;
    }

    public void setIpsecGateway(String ipsecGateway) {
        this.ipsecGateway = ipsecGateway;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RealmIpDTO)) {
            return false;
        }

        return id != null && id.equals(((RealmIpDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RealmIpDTO{" +
            "id=" + getId() +
            ", subnet='" + getSubnet() + "'" +
            ", name='" + getName() + "'" +
            ", desciption='" + getDesciption() + "'" +
            ", mask='" + getMask() + "'" +
            ", subnetSize='" + getSubnetSize() + "'" +
            ", firstIp='" + getFirstIp() + "'" +
            ", lastIp='" + getLastIp() + "'" +
            ", broadcast='" + getBroadcast() + "'" +
            ", cir='" + getCir() + "'" +
            ", ipsecGateway='" + getIpsecGateway() + "'" +
            ", locationId=" + getLocationId() +
            ", locationCode='" + getLocationCode() + "'" +
            "}";
    }
}
