package com.activeport.web.repository;

import com.activeport.web.domain.ConfigJob;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ConfigJob entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ConfigJobRepository extends JpaRepository<ConfigJob, Long> {}
