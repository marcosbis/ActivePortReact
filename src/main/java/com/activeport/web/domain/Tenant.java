package com.activeport.web.domain;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Tenant.
 */
@Entity
@Table(name = "tenant")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Tenant implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "tenant_id")
    private String tenantId;

    @Column(name = "disable_access")
    private Boolean disableAccess;

    @Column(name = "ilm_days")
    private Integer ilmDays;

    @Column(name = "slm_days")
    private Instant slmDays;

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

    public Tenant name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public Tenant description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTenantId() {
        return tenantId;
    }

    public Tenant tenantId(String tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public Boolean isDisableAccess() {
        return disableAccess;
    }

    public Tenant disableAccess(Boolean disableAccess) {
        this.disableAccess = disableAccess;
        return this;
    }

    public void setDisableAccess(Boolean disableAccess) {
        this.disableAccess = disableAccess;
    }

    public Integer getIlmDays() {
        return ilmDays;
    }

    public Tenant ilmDays(Integer ilmDays) {
        this.ilmDays = ilmDays;
        return this;
    }

    public void setIlmDays(Integer ilmDays) {
        this.ilmDays = ilmDays;
    }

    public Instant getSlmDays() {
        return slmDays;
    }

    public Tenant slmDays(Instant slmDays) {
        this.slmDays = slmDays;
        return this;
    }

    public void setSlmDays(Instant slmDays) {
        this.slmDays = slmDays;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Tenant)) {
            return false;
        }
        return id != null && id.equals(((Tenant) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Tenant{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", tenantId='" + getTenantId() + "'" +
            ", disableAccess='" + isDisableAccess() + "'" +
            ", ilmDays=" + getIlmDays() +
            ", slmDays='" + getSlmDays() + "'" +
            "}";
    }
}
