package com.activeport.web.service.dto;

import com.activeport.web.domain.enumeration.OsTypeEnum;
import com.activeport.web.domain.enumeration.PortServiceTypeEnum;
import java.io.Serializable;
import javax.persistence.Lob;

/**
 * A DTO for the {@link com.activeport.web.domain.NtuType} entity.
 */
public class NtuTypeDTO implements Serializable {
    private Long id;

    private String model;

    private Integer ethernetPorts;

    private Integer sfpPorts;

    private String pictureContentType;

    private String description;

    private PortServiceTypeEnum portServiceType;

    private OsTypeEnum osType;

    private String etherPrefix;

    private String sfpPrefix;

    private Integer startIndex;

    @Lob
    private String portTemplate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getEthernetPorts() {
        return ethernetPorts;
    }

    public void setEthernetPorts(Integer ethernetPorts) {
        this.ethernetPorts = ethernetPorts;
    }

    public Integer getSfpPorts() {
        return sfpPorts;
    }

    public void setSfpPorts(Integer sfpPorts) {
        this.sfpPorts = sfpPorts;
    }

    public String getPictureContentType() {
        return pictureContentType;
    }

    public void setPictureContentType(String pictureContentType) {
        this.pictureContentType = pictureContentType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PortServiceTypeEnum getPortServiceType() {
        return portServiceType;
    }

    public void setPortServiceType(PortServiceTypeEnum portServiceType) {
        this.portServiceType = portServiceType;
    }

    public OsTypeEnum getOsType() {
        return osType;
    }

    public void setOsType(OsTypeEnum osType) {
        this.osType = osType;
    }

    public String getEtherPrefix() {
        return etherPrefix;
    }

    public void setEtherPrefix(String etherPrefix) {
        this.etherPrefix = etherPrefix;
    }

    public String getSfpPrefix() {
        return sfpPrefix;
    }

    public void setSfpPrefix(String sfpPrefix) {
        this.sfpPrefix = sfpPrefix;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public String getPortTemplate() {
        return portTemplate;
    }

    public void setPortTemplate(String portTemplate) {
        this.portTemplate = portTemplate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NtuTypeDTO)) {
            return false;
        }

        return id != null && id.equals(((NtuTypeDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NtuTypeDTO{" +
            "id=" + getId() +
            ", model='" + getModel() + "'" +
            ", ethernetPorts=" + getEthernetPorts() +
            ", sfpPorts=" + getSfpPorts() +
            ", pictureContentType='" + getPictureContentType() + "'" +
            ", description='" + getDescription() + "'" +
            ", portServiceType='" + getPortServiceType() + "'" +
            ", osType='" + getOsType() + "'" +
            ", etherPrefix='" + getEtherPrefix() + "'" +
            ", sfpPrefix='" + getSfpPrefix() + "'" +
            ", startIndex=" + getStartIndex() +
            ", portTemplate='" + getPortTemplate() + "'" +
            "}";
    }
}
