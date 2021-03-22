package com.activeport.web.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A DTO for the {@link com.activeport.web.domain.UserRole} entity.
 */
public class UserRoleDTO implements Serializable {
    private Long id;

    private String name;

    private Integer priority;

    private String description;

    private Set<AuthorityDTO> authorities = new HashSet<>();

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

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<AuthorityDTO> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<AuthorityDTO> authorities) {
        this.authorities = authorities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserRoleDTO)) {
            return false;
        }

        return id != null && id.equals(((UserRoleDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UserRoleDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", priority=" + getPriority() +
            ", description='" + getDescription() + "'" +
            ", authorities='" + getAuthorities() + "'" +
            "}";
    }
}
