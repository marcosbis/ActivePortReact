package com.activeport.web.repository;

import com.activeport.web.domain.ProviderLog;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ProviderLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProviderLogRepository extends JpaRepository<ProviderLog, Long> {}
