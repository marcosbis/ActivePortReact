package com.activeport.web.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TileConfiguration.
 */
@Entity
@Table(name = "tile_configuration")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TileConfiguration implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Lob
    @Column(name = "command")
    private String command;

    @ManyToOne
    @JsonIgnoreProperties(value = "tileConfigurations", allowSetters = true)
    private ServiceType serviceType;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(
        name = "tile_configuration_services",
        joinColumns = @JoinColumn(name = "tile_configuration_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "services_id", referencedColumnName = "id")
    )
    private Set<ServiceConfiguration> services = new HashSet<>();

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

    public TileConfiguration name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public TileConfiguration description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCommand() {
        return command;
    }

    public TileConfiguration command(String command) {
        this.command = command;
        return this;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public TileConfiguration serviceType(ServiceType serviceType) {
        this.serviceType = serviceType;
        return this;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public Set<ServiceConfiguration> getServices() {
        return services;
    }

    public TileConfiguration services(Set<ServiceConfiguration> serviceConfigurations) {
        this.services = serviceConfigurations;
        return this;
    }

    public TileConfiguration addServices(ServiceConfiguration serviceConfiguration) {
        this.services.add(serviceConfiguration);
        serviceConfiguration.getTileConfigurations().add(this);
        return this;
    }

    public TileConfiguration removeServices(ServiceConfiguration serviceConfiguration) {
        this.services.remove(serviceConfiguration);
        serviceConfiguration.getTileConfigurations().remove(this);
        return this;
    }

    public void setServices(Set<ServiceConfiguration> serviceConfigurations) {
        this.services = serviceConfigurations;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TileConfiguration)) {
            return false;
        }
        return id != null && id.equals(((TileConfiguration) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TileConfiguration{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", command='" + getCommand() + "'" +
            "}";
    }
}
