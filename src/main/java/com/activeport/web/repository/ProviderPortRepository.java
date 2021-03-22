package com.activeport.web.repository;

import com.activeport.web.domain.ProviderPort;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ProviderPort entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProviderPortRepository extends JpaRepository<ProviderPort, Long> {}
