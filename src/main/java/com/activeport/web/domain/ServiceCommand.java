package com.activeport.web.domain;

import com.activeport.web.domain.enumeration.EntityTypeEnum;
import com.activeport.web.domain.enumeration.FilterCommandTypeEnum;
import com.activeport.web.domain.enumeration.NtuSerieEnum;
import com.activeport.web.domain.enumeration.OnEventTypeEnum;
import com.activeport.web.domain.enumeration.OsTypeEnum;
import com.activeport.web.domain.enumeration.ServiceTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ServiceCommand.
 */
@Entity
@Table(name = "service_command")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ServiceCommand implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Lob
    @Column(name = "command")
    private String command;

    @Enumerated(EnumType.STRING)
    @Column(name = "on_event")
    private OnEventTypeEnum onEvent;

    @Enumerated(EnumType.STRING)
    @Column(name = "on_service")
    private ServiceTypeEnum onService;

    @Enumerated(EnumType.STRING)
    @Column(name = "device_type")
    private NtuSerieEnum deviceType;

    @Column(name = "enabled")
    private Boolean enabled;

    @Enumerated(EnumType.STRING)
    @Column(name = "circuit_type")
    private FilterCommandTypeEnum circuitType;

    @Column(name = "tag")
    private String tag;

    @Enumerated(EnumType.STRING)
    @Column(name = "os_type")
    private OsTypeEnum osType;

    @Enumerated(EnumType.STRING)
    @Column(name = "entry_type")
    private EntityTypeEnum entryType;

    @ManyToMany(mappedBy = "commands")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnore
    private Set<ServiceConfiguration> serviceConfigurations = new HashSet<>();

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

    public ServiceCommand name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCommand() {
        return command;
    }

    public ServiceCommand command(String command) {
        this.command = command;
        return this;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public OnEventTypeEnum getOnEvent() {
        return onEvent;
    }

    public ServiceCommand onEvent(OnEventTypeEnum onEvent) {
        this.onEvent = onEvent;
        return this;
    }

    public void setOnEvent(OnEventTypeEnum onEvent) {
        this.onEvent = onEvent;
    }

    public ServiceTypeEnum getOnService() {
        return onService;
    }

    public ServiceCommand onService(ServiceTypeEnum onService) {
        this.onService = onService;
        return this;
    }

    public void setOnService(ServiceTypeEnum onService) {
        this.onService = onService;
    }

    public NtuSerieEnum getDeviceType() {
        return deviceType;
    }

    public ServiceCommand deviceType(NtuSerieEnum deviceType) {
        this.deviceType = deviceType;
        return this;
    }

    public void setDeviceType(NtuSerieEnum deviceType) {
        this.deviceType = deviceType;
    }

    public Boolean isEnabled() {
        return enabled;
    }

    public ServiceCommand enabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public FilterCommandTypeEnum getCircuitType() {
        return circuitType;
    }

    public ServiceCommand circuitType(FilterCommandTypeEnum circuitType) {
        this.circuitType = circuitType;
        return this;
    }

    public void setCircuitType(FilterCommandTypeEnum circuitType) {
        this.circuitType = circuitType;
    }

    public String getTag() {
        return tag;
    }

    public ServiceCommand tag(String tag) {
        this.tag = tag;
        return this;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public OsTypeEnum getOsType() {
        return osType;
    }

    public ServiceCommand osType(OsTypeEnum osType) {
        this.osType = osType;
        return this;
    }

    public void setOsType(OsTypeEnum osType) {
        this.osType = osType;
    }

    public EntityTypeEnum getEntryType() {
        return entryType;
    }

    public ServiceCommand entryType(EntityTypeEnum entryType) {
        this.entryType = entryType;
        return this;
    }

    public void setEntryType(EntityTypeEnum entryType) {
        this.entryType = entryType;
    }

    public Set<ServiceConfiguration> getServiceConfigurations() {
        return serviceConfigurations;
    }

    public ServiceCommand serviceConfigurations(Set<ServiceConfiguration> serviceConfigurations) {
        this.serviceConfigurations = serviceConfigurations;
        return this;
    }

    public ServiceCommand addServiceConfiguration(ServiceConfiguration serviceConfiguration) {
        this.serviceConfigurations.add(serviceConfiguration);
        serviceConfiguration.getCommands().add(this);
        return this;
    }

    public ServiceCommand removeServiceConfiguration(ServiceConfiguration serviceConfiguration) {
        this.serviceConfigurations.remove(serviceConfiguration);
        serviceConfiguration.getCommands().remove(this);
        return this;
    }

    public void setServiceConfigurations(Set<ServiceConfiguration> serviceConfigurations) {
        this.serviceConfigurations = serviceConfigurations;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ServiceCommand)) {
            return false;
        }
        return id != null && id.equals(((ServiceCommand) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ServiceCommand{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", command='" + getCommand() + "'" +
            ", onEvent='" + getOnEvent() + "'" +
            ", onService='" + getOnService() + "'" +
            ", deviceType='" + getDeviceType() + "'" +
            ", enabled='" + isEnabled() + "'" +
            ", circuitType='" + getCircuitType() + "'" +
            ", tag='" + getTag() + "'" +
            ", osType='" + getOsType() + "'" +
            ", entryType='" + getEntryType() + "'" +
            "}";
    }
}
