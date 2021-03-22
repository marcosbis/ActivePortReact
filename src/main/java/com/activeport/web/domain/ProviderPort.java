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
 * A ProviderPort.
 */
@Entity
@Table(name = "provider_port")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ProviderPort implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3, max = 30)
    @Column(name = "name", length = 30, nullable = false)
    private String name;

    @Column(name = "uid")
    private String uid;

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

    @Column(name = "port_id")
    private String portId;

    @Column(name = "market")
    private String market;

    @Column(name = "location_id")
    private Integer locationId;

    @ManyToOne
    @JsonIgnoreProperties(value = "providerPorts", allowSetters = true)
    private ThirdPartyApi thirdPartyApi;

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

    public ProviderPort name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public ProviderPort uid(String uid) {
        this.uid = uid;
        return this;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDescription() {
        return description;
    }

    public ProviderPort description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PartnerTypeEnum getType() {
        return type;
    }

    public ProviderPort type(PartnerTypeEnum type) {
        this.type = type;
        return this;
    }

    public void setType(PartnerTypeEnum type) {
        this.type = type;
    }

    public ConnetionTypeEnum getConnection() {
        return connection;
    }

    public ProviderPort connection(ConnetionTypeEnum connection) {
        this.connection = connection;
        return this;
    }

    public void setConnection(ConnetionTypeEnum connection) {
        this.connection = connection;
    }

    public AwsTypeEnum getPortType() {
        return portType;
    }

    public ProviderPort portType(AwsTypeEnum portType) {
        this.portType = portType;
        return this;
    }

    public void setPortType(AwsTypeEnum portType) {
        this.portType = portType;
    }

    public String getPortId() {
        return portId;
    }

    public ProviderPort portId(String portId) {
        this.portId = portId;
        return this;
    }

    public void setPortId(String portId) {
        this.portId = portId;
    }

    public String getMarket() {
        return market;
    }

    public ProviderPort market(String market) {
        this.market = market;
        return this;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public ProviderPort locationId(Integer locationId) {
        this.locationId = locationId;
        return this;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public ThirdPartyApi getThirdPartyApi() {
        return thirdPartyApi;
    }

    public ProviderPort thirdPartyApi(ThirdPartyApi thirdPartyApi) {
        this.thirdPartyApi = thirdPartyApi;
        return this;
    }

    public void setThirdPartyApi(ThirdPartyApi thirdPartyApi) {
        this.thirdPartyApi = thirdPartyApi;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProviderPort)) {
            return false;
        }
        return id != null && id.equals(((ProviderPort) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProviderPort{" +
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
            "}";
    }
}
