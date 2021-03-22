package com.activeport.web.domain;

import com.activeport.web.domain.enumeration.TenantTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ServiceConfiguration.
 */
@Entity
@Table(name = "service_configuration")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ServiceConfiguration implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "tenant_type")
    private TenantTypeEnum tenantType;

    @Lob
    @Column(name = "command")
    private String command;

    @Column(name = "test")
    private String test;

    @Column(name = "use_default_commands")
    private Boolean useDefaultCommands;

    @ManyToOne
    @JsonIgnoreProperties(value = "serviceConfigurations", allowSetters = true)
    private ServiceCode serviceCode;

    @ManyToOne
    @JsonIgnoreProperties(value = "serviceConfigurations", allowSetters = true)
    private Partner provider;

    @ManyToOne
    @JsonIgnoreProperties(value = "serviceConfigurations", allowSetters = true)
    private ItemCode priceCode;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(
        name = "service_configuration_commands",
        joinColumns = @JoinColumn(name = "service_configuration_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "commands_id", referencedColumnName = "id")
    )
    private Set<ServiceCommand> commands = new HashSet<>();

    @ManyToMany(mappedBy = "services")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnore
    private Set<TileConfiguration> tileConfigurations = new HashSet<>();

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

    public ServiceConfiguration name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public ServiceConfiguration description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TenantTypeEnum getTenantType() {
        return tenantType;
    }

    public ServiceConfiguration tenantType(TenantTypeEnum tenantType) {
        this.tenantType = tenantType;
        return this;
    }

    public void setTenantType(TenantTypeEnum tenantType) {
        this.tenantType = tenantType;
    }

    public String getCommand() {
        return command;
    }

    public ServiceConfiguration command(String command) {
        this.command = command;
        return this;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getTest() {
        return test;
    }

    public ServiceConfiguration test(String test) {
        this.test = test;
        return this;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public Boolean isUseDefaultCommands() {
        return useDefaultCommands;
    }

    public ServiceConfiguration useDefaultCommands(Boolean useDefaultCommands) {
        this.useDefaultCommands = useDefaultCommands;
        return this;
    }

    public void setUseDefaultCommands(Boolean useDefaultCommands) {
        this.useDefaultCommands = useDefaultCommands;
    }

    public ServiceCode getServiceCode() {
        return serviceCode;
    }

    public ServiceConfiguration serviceCode(ServiceCode serviceCode) {
        this.serviceCode = serviceCode;
        return this;
    }

    public void setServiceCode(ServiceCode serviceCode) {
        this.serviceCode = serviceCode;
    }

    public Partner getProvider() {
        return provider;
    }

    public ServiceConfiguration provider(Partner partner) {
        this.provider = partner;
        return this;
    }

    public void setProvider(Partner partner) {
        this.provider = partner;
    }

    public ItemCode getPriceCode() {
        return priceCode;
    }

    public ServiceConfiguration priceCode(ItemCode itemCode) {
        this.priceCode = itemCode;
        return this;
    }

    public void setPriceCode(ItemCode itemCode) {
        this.priceCode = itemCode;
    }

    public Set<ServiceCommand> getCommands() {
        return commands;
    }

    public ServiceConfiguration commands(Set<ServiceCommand> serviceCommands) {
        this.commands = serviceCommands;
        return this;
    }

    public ServiceConfiguration addCommands(ServiceCommand serviceCommand) {
        this.commands.add(serviceCommand);
        serviceCommand.getServiceConfigurations().add(this);
        return this;
    }

    public ServiceConfiguration removeCommands(ServiceCommand serviceCommand) {
        this.commands.remove(serviceCommand);
        serviceCommand.getServiceConfigurations().remove(this);
        return this;
    }

    public void setCommands(Set<ServiceCommand> serviceCommands) {
        this.commands = serviceCommands;
    }

    public Set<TileConfiguration> getTileConfigurations() {
        return tileConfigurations;
    }

    public ServiceConfiguration tileConfigurations(Set<TileConfiguration> tileConfigurations) {
        this.tileConfigurations = tileConfigurations;
        return this;
    }

    public ServiceConfiguration addTileConfiguration(TileConfiguration tileConfiguration) {
        this.tileConfigurations.add(tileConfiguration);
        tileConfiguration.getServices().add(this);
        return this;
    }

    public ServiceConfiguration removeTileConfiguration(TileConfiguration tileConfiguration) {
        this.tileConfigurations.remove(tileConfiguration);
        tileConfiguration.getServices().remove(this);
        return this;
    }

    public void setTileConfigurations(Set<TileConfiguration> tileConfigurations) {
        this.tileConfigurations = tileConfigurations;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ServiceConfiguration)) {
            return false;
        }
        return id != null && id.equals(((ServiceConfiguration) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ServiceConfiguration{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", tenantType='" + getTenantType() + "'" +
            ", command='" + getCommand() + "'" +
            ", test='" + getTest() + "'" +
            ", useDefaultCommands='" + isUseDefaultCommands() + "'" +
            "}";
    }
}
