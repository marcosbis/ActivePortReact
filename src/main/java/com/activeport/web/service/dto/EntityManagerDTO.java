package com.activeport.web.service.dto;

import java.io.Serializable;
import java.util.UUID;

/**
 * A DTO for the {@link com.activeport.web.domain.EntityManager} entity.
 */
public class EntityManagerDTO implements Serializable {
    private Long id;

    private String name;

    private String description;

    private UUID uid;

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

    public UUID getUid() {
        return uid;
    }

    public void setUid(UUID uid) {
        this.uid = uid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EntityManagerDTO)) {
            return false;
        }

        return id != null && id.equals(((EntityManagerDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EntityManagerDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", uid='" + getUid() + "'" +
            "}";
    }
}
