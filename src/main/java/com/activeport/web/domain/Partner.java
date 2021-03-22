package com.activeport.web.domain;

import com.activeport.web.domain.enumeration.AwsTypeEnum;
import com.activeport.web.domain.enumeration.ConnetionTypeEnum;
import com.activeport.web.domain.enumeration.PartnerTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Partner.
 */
@Entity
@Table(name = "partner")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Partner implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3, max = 30)
    @Column(name = "name", length = 30, nullable = false)
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "description")
    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private PartnerTypeEnum type;

    @Enumerated(EnumType.STRING)
    @Column(name = "connection")
    private ConnetionTypeEnum connection;

    @Enumerated(EnumType.STRING)
    @Column(name = "port_type")
    private AwsTypeEnum portType;

    @Column(name = "port")
    private String port;

    @Column(name = "market")
    private String market;

    @Column(name = "location_id")
    private Integer locationId;

    @Column(name = "vxcpermitted")
    private Boolean vxcpermitted;

    @Column(name = "location_ix")
    private String locationIx;

    @Column(name = "vlan_port")
    private String vlanPort;

    @ManyToOne
    @JsonIgnoreProperties(value = "partners", allowSetters = true)
    private CentralSwitch centralSwitch;

    @ManyToOne
    @JsonIgnoreProperties(value = "partners", allowSetters = true)
    private ProviderConfiguration providerCode;

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

    public Partner name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public Partner email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public Partner description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PartnerTypeEnum getType() {
        return type;
    }

    public Partner type(PartnerTypeEnum type) {
        this.type = type;
        return this;
    }

    public void setType(PartnerTypeEnum type) {
        this.type = type;
    }

    public ConnetionTypeEnum getConnection() {
        return connection;
    }

    public Partner connection(ConnetionTypeEnum connection) {
        this.connection = connection;
        return this;
    }

    public void setConnection(ConnetionTypeEnum connection) {
        this.connection = connection;
    }

    public AwsTypeEnum getPortType() {
        return portType;
    }

    public Partner portType(AwsTypeEnum portType) {
        this.portType = portType;
        return this;
    }

    public void setPortType(AwsTypeEnum portType) {
        this.portType = portType;
    }

    public String getPort() {
        return port;
    }

    public Partner port(String port) {
        this.port = port;
        return this;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getMarket() {
        return market;
    }

    public Partner market(String market) {
        this.market = market;
        return this;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public Partner locationId(Integer locationId) {
        this.locationId = locationId;
        return this;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public Boolean isVxcpermitted() {
        return vxcpermitted;
    }

    public Partner vxcpermitted(Boolean vxcpermitted) {
        this.vxcpermitted = vxcpermitted;
        return this;
    }

    public void setVxcpermitted(Boolean vxcpermitted) {
        this.vxcpermitted = vxcpermitted;
    }

    public String getLocationIx() {
        return locationIx;
    }

    public Partner locationIx(String locationIx) {
        this.locationIx = locationIx;
        return this;
    }

    public void setLocationIx(String locationIx) {
        this.locationIx = locationIx;
    }

    public String getVlanPort() {
        return vlanPort;
    }

    public Partner vlanPort(String vlanPort) {
        this.vlanPort = vlanPort;
        return this;
    }

    public void setVlanPort(String vlanPort) {
        this.vlanPort = vlanPort;
    }

    public CentralSwitch getCentralSwitch() {
        return centralSwitch;
    }

    public Partner centralSwitch(CentralSwitch centralSwitch) {
        this.centralSwitch = centralSwitch;
        return this;
    }

    public void setCentralSwitch(CentralSwitch centralSwitch) {
        this.centralSwitch = centralSwitch;
    }

    public ProviderConfiguration getProviderCode() {
        return providerCode;
    }

    public Partner providerCode(ProviderConfiguration providerConfiguration) {
        this.providerCode = providerConfiguration;
        return this;
    }

    public void setProviderCode(ProviderConfiguration providerConfiguration) {
        this.providerCode = providerConfiguration;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Partner)) {
            return false;
        }
        return id != null && id.equals(((Partner) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Partner{" +
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
            "}";
    }
}
