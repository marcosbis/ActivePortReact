package com.activeport.web.repository;

import com.activeport.web.domain.NtuConfig;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the NtuConfig entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NtuConfigRepository extends JpaRepository<NtuConfig, Long> {}
