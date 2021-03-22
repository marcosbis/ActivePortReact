package com.activeport.web.repository;

import com.activeport.web.domain.ServiceCode;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ServiceCode entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ServiceCodeRepository extends JpaRepository<ServiceCode, Long> {}
