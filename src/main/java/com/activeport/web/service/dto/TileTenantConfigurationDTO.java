package com.activeport.web.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.activeport.web.domain.TileTenantConfiguration} entity.
 */
public class TileTenantConfigurationDTO implements Serializable {
    private Long id;

    private String tenantId;

    private String orgId;

    private Long tileConfigurationId;

    private String tileConfigurationName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public Long getTileConfigurationId() {
        return tileConfigurationId;
    }

    public void setTileConfigurationId(Long tileConfigurationId) {
        this.tileConfigurationId = tileConfigurationId;
    }

    public String getTileConfigurationName() {
        return tileConfigurationName;
    }

    public void setTileConfigurationName(String tileConfigurationName) {
        this.tileConfigurationName = tileConfigurationName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TileTenantConfigurationDTO)) {
            return false;
        }

        return id != null && id.equals(((TileTenantConfigurationDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TileTenantConfigurationDTO{" +
            "id=" + getId() +
            ", tenantId='" + getTenantId() + "'" +
            ", orgId='" + getOrgId() + "'" +
            ", tileConfigurationId=" + getTileConfigurationId() +
            ", tileConfigurationName='" + getTileConfigurationName() + "'" +
            "}";
    }
}
