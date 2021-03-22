package com.activeport.web.repository;

import com.activeport.web.domain.BillingSystem;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the BillingSystem entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BillingSystemRepository extends JpaRepository<BillingSystem, Long> {}
