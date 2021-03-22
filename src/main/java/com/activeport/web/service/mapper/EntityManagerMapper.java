package com.activeport.web.service.mapper;

import com.activeport.web.domain.*;
import com.activeport.web.service.dto.EntityManagerDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link EntityManager} and its DTO {@link EntityManagerDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EntityManagerMapper extends EntityMapper<EntityManagerDTO, EntityManager> {
    default EntityManager fromId(Long id) {
        if (id == null) {
            return null;
        }
        EntityManager entityManager = new EntityManager();
        entityManager.setId(id);
        return entityManager;
    }
}
