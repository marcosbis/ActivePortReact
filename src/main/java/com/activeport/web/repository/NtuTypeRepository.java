package com.activeport.web.repository;

import com.activeport.web.domain.NtuType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the NtuType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NtuTypeRepository extends JpaRepository<NtuType, Long> {}
