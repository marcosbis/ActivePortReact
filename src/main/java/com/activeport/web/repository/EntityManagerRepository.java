package com.activeport.web.repository;

import com.activeport.web.domain.EntityManager;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the EntityManager entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EntityManagerRepository extends JpaRepository<EntityManager, Long> {}
