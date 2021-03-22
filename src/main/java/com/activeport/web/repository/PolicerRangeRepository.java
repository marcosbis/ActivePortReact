package com.activeport.web.repository;

import com.activeport.web.domain.PolicerRange;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the PolicerRange entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PolicerRangeRepository extends JpaRepository<PolicerRange, Long> {}
