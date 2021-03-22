package com.activeport.web.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ServiceType.
 */
@Entity
@Table(name = "service_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ServiceType implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "services")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnore
    private Set<ProviderConfiguration> providers = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public ServiceType code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public ServiceType name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public ServiceType description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<ProviderConfiguration> getProviders() {
        return providers;
    }

    public ServiceType providers(Set<ProviderConfiguration> providerConfigurations) {
        this.providers = providerConfigurations;
        return this;
    }

    public ServiceType addProvider(ProviderConfiguration providerConfiguration) {
        this.providers.add(providerConfiguration);
        providerConfiguration.getServices().add(this);
        return this;
    }

    public ServiceType removeProvider(ProviderConfiguration providerConfiguration) {
        this.providers.remove(providerConfiguration);
        providerConfiguration.getServices().remove(this);
        return this;
    }

    public void setProviders(Set<ProviderConfiguration> providerConfigurations) {
        this.providers = providerConfigurations;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ServiceType)) {
            return false;
        }
        return id != null && id.equals(((ServiceType) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ServiceType{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
