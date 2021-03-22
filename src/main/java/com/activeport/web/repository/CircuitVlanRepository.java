package com.activeport.web.repository;

import com.activeport.web.domain.CircuitVlan;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CircuitVlan entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CircuitVlanRepository extends JpaRepository<CircuitVlan, Long> {}
