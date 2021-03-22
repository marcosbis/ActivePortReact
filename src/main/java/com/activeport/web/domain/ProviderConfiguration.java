package com.activeport.web.domain;

import com.activeport.web.domain.enumeration.ApiTypeEnum;
import com.activeport.web.domain.enumeration.PartnerTypeEnum;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ProviderConfiguration.
 */
@Entity
@Table(name = "provider_configuration")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ProviderConfiguration implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private PartnerTypeEnum type;

    @Enumerated(EnumType.STRING)
    @Column(name = "api_type")
    private ApiTypeEnum apiType;

    @Column(name = "has_port_id")
    private Boolean hasPortId;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(
        name = "provider_configuration_services",
        joinColumns = @JoinColumn(name = "provider_configuration_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "services_id", referencedColumnName = "id")
    )
    private Set<ServiceType> services = new HashSet<>();

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

    public ProviderConfiguration name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public ProviderConfiguration description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PartnerTypeEnum getType() {
        return type;
    }

    public ProviderConfiguration type(PartnerTypeEnum type) {
        this.type = type;
        return this;
    }

    public void setType(PartnerTypeEnum type) {
        this.type = type;
    }

    public ApiTypeEnum getApiType() {
        return apiType;
    }

    public ProviderConfiguration apiType(ApiTypeEnum apiType) {
        this.apiType = apiType;
        return this;
    }

    public void setApiType(ApiTypeEnum apiType) {
        this.apiType = apiType;
    }

    public Boolean isHasPortId() {
        return hasPortId;
    }

    public ProviderConfiguration hasPortId(Boolean hasPortId) {
        this.hasPortId = hasPortId;
        return this;
    }

    public void setHasPortId(Boolean hasPortId) {
        this.hasPortId = hasPortId;
    }

    public Set<ServiceType> getServices() {
        return services;
    }

    public ProviderConfiguration services(Set<ServiceType> serviceTypes) {
        this.services = serviceTypes;
        return this;
    }

    public ProviderConfiguration addServices(ServiceType serviceType) {
        this.services.add(serviceType);
        serviceType.getProviders().add(this);
        return this;
    }

    public ProviderConfiguration removeServices(ServiceType serviceType) {
        this.services.remove(serviceType);
        serviceType.getProviders().remove(this);
        return this;
    }

    public void setServices(Set<ServiceType> serviceTypes) {
        this.services = serviceTypes;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProviderConfiguration)) {
            return false;
        }
        return id != null && id.equals(((ProviderConfiguration) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProviderConfiguration{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", type='" + getType() + "'" +
            ", apiType='" + getApiType() + "'" +
            ", hasPortId='" + isHasPortId() + "'" +
            "}";
    }
}
