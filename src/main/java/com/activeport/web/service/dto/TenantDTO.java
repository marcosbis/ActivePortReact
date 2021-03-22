package com.activeport.web.service.dto;

import java.io.Serializable;
import java.time.Instant;

/**
 * A DTO for the {@link com.activeport.web.domain.Tenant} entity.
 */
public class TenantDTO implements Serializable {
    private Long id;

    private String name;

    private String description;

    private String tenantId;

    private Boolean disableAccess;

    private Integer ilmDays;

    private Instant slmDays;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public Boolean isDisableAccess() {
        return disableAccess;
    }

    public void setDisableAccess(Boolean disableAccess) {
        this.disableAccess = disableAccess;
    }

    public Integer getIlmDays() {
        return ilmDays;
    }

    public void setIlmDays(Integer ilmDays) {
        this.ilmDays = ilmDays;
    }

    public Instant getSlmDays() {
        return slmDays;
    }

    public void setSlmDays(Instant slmDays) {
        this.slmDays = slmDays;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TenantDTO)) {
            return false;
        }

        return id != null && id.equals(((TenantDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TenantDTO{" +
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
