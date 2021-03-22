package com.activeport.web.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.activeport.web.domain.Organization} entity.
 */
public class OrganizationDTO implements Serializable {
    private Long id;

    private String name;

    private String hostId;

    private String description;

    private Boolean billing;

    private String timeZone;

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

    public String getHostId() {
        return hostId;
    }

    public void setHostId(String hostId) {
        this.hostId = hostId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean isBilling() {
        return billing;
    }

    public void setBilling(Boolean billing) {
        this.billing = billing;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrganizationDTO)) {
            return false;
        }

        return id != null && id.equals(((OrganizationDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OrganizationDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", hostId='" + getHostId() + "'" +
            ", description='" + getDescription() + "'" +
            ", billing='" + isBilling() + "'" +
            ", timeZone='" + getTimeZone() + "'" +
            "}";
    }
}
