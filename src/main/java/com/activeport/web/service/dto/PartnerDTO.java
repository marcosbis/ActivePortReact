package com.activeport.web.service.dto;

import com.activeport.web.domain.enumeration.AwsTypeEnum;
import com.activeport.web.domain.enumeration.ConnetionTypeEnum;
import com.activeport.web.domain.enumeration.PartnerTypeEnum;
import java.io.Serializable;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.activeport.web.domain.Partner} entity.
 */
public class PartnerDTO implements Serializable {
    private Long id;

    @NotNull
    @Size(min = 3, max = 30)
    private String name;

    private String email;

    private String description;

    @NotNull
    private PartnerTypeEnum type;

    private ConnetionTypeEnum connection;

    private AwsTypeEnum portType;

    private String port;

    private String market;

    private Integer locationId;

    private Boolean vxcpermitted;

    private String locationIx;

    private String vlanPort;

    private Long centralSwitchId;

    private String centralSwitchName;

    private Long providerCodeId;

    private String providerCodeName;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PartnerTypeEnum getType() {
        return type;
    }

    public void setType(PartnerTypeEnum type) {
        this.type = type;
    }

    public ConnetionTypeEnum getConnection() {
        return connection;
    }

    public void setConnection(ConnetionTypeEnum connection) {
        this.connection = connection;
    }

    public AwsTypeEnum getPortType() {
        return portType;
    }

    public void setPortType(AwsTypeEnum portType) {
        this.portType = portType;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public Boolean isVxcpermitted() {
        return vxcpermitted;
    }

    public void setVxcpermitted(Boolean vxcpermitted) {
        this.vxcpermitted = vxcpermitted;
    }

    public String getLocationIx() {
        return locationIx;
    }

    public void setLocationIx(String locationIx) {
        this.locationIx = locationIx;
    }

    public String getVlanPort() {
        return vlanPort;
    }

    public void setVlanPort(String vlanPort) {
        this.vlanPort = vlanPort;
    }

    public Long getCentralSwitchId() {
        return centralSwitchId;
    }

    public void setCentralSwitchId(Long centralSwitchId) {
        this.centralSwitchId = centralSwitchId;
    }

    public String getCentralSwitchName() {
        return centralSwitchName;
    }

    public void setCentralSwitchName(String centralSwitchName) {
        this.centralSwitchName = centralSwitchName;
    }

    public Long getProviderCodeId() {
        return providerCodeId;
    }

    public void setProviderCodeId(Long providerConfigurationId) {
        this.providerCodeId = providerConfigurationId;
    }

    public String getProviderCodeName() {
        return providerCodeName;
    }

    public void setProviderCodeName(String providerConfigurationName) {
        this.providerCodeName = providerConfigurationName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PartnerDTO)) {
            return false;
        }

        return id != null && id.equals(((PartnerDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PartnerDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", email='" + getEmail() + "'" +
            ", description='" + getDescription() + "'" +
            ", type='" + getType() + "'" +
            ", connection='" + getConnection() + "'" +
            ", portType='" + getPortType() + "'" +
            ", port='" + getPort() + "'" +
            ", market='" + getMarket() + "'" +
            ", locationId=" + getLocationId() +
            ", vxcpermitted='" + isVxcpermitted() + "'" +
            ", locationIx='" + getLocationIx() + "'" +
            ", vlanPort='" + getVlanPort() + "'" +
            ", centralSwitchId=" + getCentralSwitchId() +
            ", centralSwitchName='" + getCentralSwitchName() + "'" +
            ", providerCodeId=" + getProviderCodeId() +
            ", providerCodeName='" + getProviderCodeName() + "'" +
            "}";
    }
}
