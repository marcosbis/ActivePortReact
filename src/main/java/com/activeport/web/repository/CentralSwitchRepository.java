package com.activeport.web.repository;

import com.activeport.web.domain.CentralSwitch;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CentralSwitch entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CentralSwitchRepository extends JpaRepository<CentralSwitch, Long> {}
