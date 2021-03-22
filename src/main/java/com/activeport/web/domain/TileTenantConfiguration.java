package com.activeport.web.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TileTenantConfiguration.
 */
@Entity
@Table(name = "tile_tenant_configuration")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TileTenantConfiguration implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tenant_id")
    private String tenantId;

    @Column(name = "org_id")
    private String orgId;

    @ManyToOne
    @JsonIgnoreProperties(value = "tileTenantConfigurations", allowSetters = true)
    private TileConfiguration tileConfiguration;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenantId() {
        return tenantId;
    }

    public TileTenantConfiguration tenantId(String tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getOrgId() {
        return orgId;
    }

    public TileTenantConfiguration orgId(String orgId) {
        this.orgId = orgId;
        return this;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public TileConfiguration getTileConfiguration() {
        return tileConfiguration;
    }

    public TileTenantConfiguration tileConfiguration(TileConfiguration tileConfiguration) {
        this.tileConfiguration = tileConfiguration;
        return this;
    }

    public void setTileConfiguration(TileConfiguration tileConfiguration) {
        this.tileConfiguration = tileConfiguration;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TileTenantConfiguration)) {
            return false;
        }
        return id != null && id.equals(((TileTenantConfiguration) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TileTenantConfiguration{" +
            "id=" + getId() +
            ", tenantId='" + getTenantId() + "'" +
            ", orgId='" + getOrgId() + "'" +
            "}";
    }
}
