package com.activeport.web.repository;

import com.activeport.web.domain.RateChangeLog;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the RateChangeLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RateChangeLogRepository extends JpaRepository<RateChangeLog, Long> {}
