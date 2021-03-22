package com.activeport.web.repository;

import com.activeport.web.domain.NtuPort;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the NtuPort entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NtuPortRepository extends JpaRepository<NtuPort, Long> {}
