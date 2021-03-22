package com.activeport.web.service.dto;

import com.activeport.web.domain.enumeration.AwsTypeEnum;
import com.activeport.web.domain.enumeration.ConnetionTypeEnum;
import com.activeport.web.domain.enumeration.PartnerTypeEnum;
import java.io.Serializable;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.activeport.web.domain.ProviderPort} entity.
 */
public class ProviderPortDTO implements Serializable {
    private Long id;

    @NotNull
    @Size(min = 3, max = 30)
    private String name;

    private String uid;

    private String description;

    @NotNull
    private PartnerTypeEnum type;

    private ConnetionTypeEnum connection;

    private AwsTypeEnum portType;

    private String portId;

    private String market;

    private Integer locationId;

    private Long thirdPartyApiId;

    private String thirdPartyApiName;

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

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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

    public String getPortId() {
        return portId;
    }

    public void setPortId(String portId) {
        this.portId = portId;
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

    public Long getThirdPartyApiId() {
        return thirdPartyApiId;
    }

    public void setThirdPartyApiId(Long thirdPartyApiId) {
        this.thirdPartyApiId = thirdPartyApiId;
    }

    public String getThirdPartyApiName() {
        return thirdPartyApiName;
    }

    public void setThirdPartyApiName(String thirdPartyApiName) {
        this.thirdPartyApiName = thirdPartyApiName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProviderPortDTO)) {
            return false;
        }

        return id != null && id.equals(((ProviderPortDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProviderPortDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", uid='" + getUid() + "'" +
            ", description='" + getDescription() + "'" +
            ", type='" + getType() + "'" +
            ", connection='" + getConnection() + "'" +
            ", portType='" + getPortType() + "'" +
            ", portId='" + getPortId() + "'" +
            ", market='" + getMarket() + "'" +
            ", locationId=" + getLocationId() +
            ", thirdPartyApiId=" + getThirdPartyApiId() +
            ", thirdPartyApiName='" + getThirdPartyApiName() + "'" +
            "}";
    }
}
