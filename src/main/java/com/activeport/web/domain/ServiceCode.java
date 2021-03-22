package com.activeport.web.domain;

import com.activeport.web.domain.enumeration.CreationTypeEnum;
import com.activeport.web.domain.enumeration.HostedTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ServiceCode.
 */
@Entity
@Table(name = "service_code")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ServiceCode implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Lob
    @Column(name = "command")
    private String command;

    @Column(name = "description")
    private String description;

    @Column(name = "enabled")
    private Boolean enabled;

    @Column(name = "service_url")
    private String serviceUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "hosted_type")
    private HostedTypeEnum hostedType;

    @Enumerated(EnumType.STRING)
    @Column(name = "creation_type")
    private CreationTypeEnum creationType;

    @Column(name = "tag")
    private String tag;

    @Column(name = "dto_class")
    private String dtoClass;

    @ManyToOne
    @JsonIgnoreProperties(value = "serviceCodes", allowSetters = true)
    private ProviderConfiguration providerType;

    @ManyToOne
    @JsonIgnoreProperties(value = "serviceCodes", allowSetters = true)
    private ServiceType serviceType;

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

    public ServiceCode name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCommand() {
        return command;
    }

    public ServiceCode command(String command) {
        this.command = command;
        return this;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getDescription() {
        return description;
    }

    public ServiceCode description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean isEnabled() {
        return enabled;
    }

    public ServiceCode enabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getServiceUrl() {
        return serviceUrl;
    }

    public ServiceCode serviceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
        return this;
    }

    public void setServiceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    public HostedTypeEnum getHostedType() {
        return hostedType;
    }

    public ServiceCode hostedType(HostedTypeEnum hostedType) {
        this.hostedType = hostedType;
        return this;
    }

    public void setHostedType(HostedTypeEnum hostedType) {
        this.hostedType = hostedType;
    }

    public CreationTypeEnum getCreationType() {
        return creationType;
    }

    public ServiceCode creationType(CreationTypeEnum creationType) {
        this.creationType = creationType;
        return this;
    }

    public void setCreationType(CreationTypeEnum creationType) {
        this.creationType = creationType;
    }

    public String getTag() {
        return tag;
    }

    public ServiceCode tag(String tag) {
        this.tag = tag;
        return this;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getDtoClass() {
        return dtoClass;
    }

    public ServiceCode dtoClass(String dtoClass) {
        this.dtoClass = dtoClass;
        return this;
    }

    public void setDtoClass(String dtoClass) {
        this.dtoClass = dtoClass;
    }

    public ProviderConfiguration getProviderType() {
        return providerType;
    }

    public ServiceCode providerType(ProviderConfiguration providerConfiguration) {
        this.providerType = providerConfiguration;
        return this;
    }

    public void setProviderType(ProviderConfiguration providerConfiguration) {
        this.providerType = providerConfiguration;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public ServiceCode serviceType(ServiceType serviceType) {
        this.serviceType = serviceType;
        return this;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ServiceCode)) {
            return false;
        }
        return id != null && id.equals(((ServiceCode) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ServiceCode{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", command='" + getCommand() + "'" +
            ", description='" + getDescription() + "'" +
            ", enabled='" + isEnabled() + "'" +
            ", serviceUrl='" + getServiceUrl() + "'" +
            ", hostedType='" + getHostedType() + "'" +
            ", creationType='" + getCreationType() + "'" +
            ", tag='" + getTag() + "'" +
            ", dtoClass='" + getDtoClass() + "'" +
            "}";
    }
}
