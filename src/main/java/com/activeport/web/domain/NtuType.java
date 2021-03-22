package com.activeport.web.domain;

import com.activeport.web.domain.enumeration.OsTypeEnum;
import com.activeport.web.domain.enumeration.PortServiceTypeEnum;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A NtuType.
 */
@Entity
@Table(name = "ntu_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class NtuType implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "model")
    private String model;

    @Column(name = "ethernet_ports")
    private Integer ethernetPorts;

    @Column(name = "sfp_ports")
    private Integer sfpPorts;

    @Column(name = "picture_content_type")
    private String pictureContentType;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "port_service_type")
    private PortServiceTypeEnum portServiceType;

    @Enumerated(EnumType.STRING)
    @Column(name = "os_type")
    private OsTypeEnum osType;

    @Column(name = "ether_prefix")
    private String etherPrefix;

    @Column(name = "sfp_prefix")
    private String sfpPrefix;

    @Column(name = "start_index")
    private Integer startIndex;

    @Lob
    @Column(name = "port_template")
    private String portTemplate;

    @OneToMany(mappedBy = "ntutype")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<CentralSwitch> switches = new HashSet<>();

    @OneToMany(mappedBy = "ntutype")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Ntu> ntus = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public NtuType model(String model) {
        this.model = model;
        return this;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getEthernetPorts() {
        return ethernetPorts;
    }

    public NtuType ethernetPorts(Integer ethernetPorts) {
        this.ethernetPorts = ethernetPorts;
        return this;
    }

    public void setEthernetPorts(Integer ethernetPorts) {
        this.ethernetPorts = ethernetPorts;
    }

    public Integer getSfpPorts() {
        return sfpPorts;
    }

    public NtuType sfpPorts(Integer sfpPorts) {
        this.sfpPorts = sfpPorts;
        return this;
    }

    public void setSfpPorts(Integer sfpPorts) {
        this.sfpPorts = sfpPorts;
    }

    public String getPictureContentType() {
        return pictureContentType;
    }

    public NtuType pictureContentType(String pictureContentType) {
        this.pictureContentType = pictureContentType;
        return this;
    }

    public void setPictureContentType(String pictureContentType) {
        this.pictureContentType = pictureContentType;
    }

    public String getDescription() {
        return description;
    }

    public NtuType description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PortServiceTypeEnum getPortServiceType() {
        return portServiceType;
    }

    public NtuType portServiceType(PortServiceTypeEnum portServiceType) {
        this.portServiceType = portServiceType;
        return this;
    }

    public void setPortServiceType(PortServiceTypeEnum portServiceType) {
        this.portServiceType = portServiceType;
    }

    public OsTypeEnum getOsType() {
        return osType;
    }

    public NtuType osType(OsTypeEnum osType) {
        this.osType = osType;
        return this;
    }

    public void setOsType(OsTypeEnum osType) {
        this.osType = osType;
    }

    public String getEtherPrefix() {
        return etherPrefix;
    }

    public NtuType etherPrefix(String etherPrefix) {
        this.etherPrefix = etherPrefix;
        return this;
    }

    public void setEtherPrefix(String etherPrefix) {
        this.etherPrefix = etherPrefix;
    }

    public String getSfpPrefix() {
        return sfpPrefix;
    }

    public NtuType sfpPrefix(String sfpPrefix) {
        this.sfpPrefix = sfpPrefix;
        return this;
    }

    public void setSfpPrefix(String sfpPrefix) {
        this.sfpPrefix = sfpPrefix;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public NtuType startIndex(Integer startIndex) {
        this.startIndex = startIndex;
        return this;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public String getPortTemplate() {
        return portTemplate;
    }

    public NtuType portTemplate(String portTemplate) {
        this.portTemplate = portTemplate;
        return this;
    }

    public void setPortTemplate(String portTemplate) {
        this.portTemplate = portTemplate;
    }

    public Set<CentralSwitch> getSwitches() {
        return switches;
    }

    public NtuType switches(Set<CentralSwitch> centralSwitches) {
        this.switches = centralSwitches;
        return this;
    }

    public NtuType addSwitch(CentralSwitch centralSwitch) {
        this.switches.add(centralSwitch);
        centralSwitch.setNtutype(this);
        return this;
    }

    public NtuType removeSwitch(CentralSwitch centralSwitch) {
        this.switches.remove(centralSwitch);
        centralSwitch.setNtutype(null);
        return this;
    }

    public void setSwitches(Set<CentralSwitch> centralSwitches) {
        this.switches = centralSwitches;
    }

    public Set<Ntu> getNtus() {
        return ntus;
    }

    public NtuType ntus(Set<Ntu> ntus) {
        this.ntus = ntus;
        return this;
    }

    public NtuType addNtu(Ntu ntu) {
        this.ntus.add(ntu);
        ntu.setNtutype(this);
        return this;
    }

    public NtuType removeNtu(Ntu ntu) {
        this.ntus.remove(ntu);
        ntu.setNtutype(null);
        return this;
    }

    public void setNtus(Set<Ntu> ntus) {
        this.ntus = ntus;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NtuType)) {
            return false;
        }
        return id != null && id.equals(((NtuType) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NtuType{" +
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
