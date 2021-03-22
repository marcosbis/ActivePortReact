package com.activeport.web.repository;

import com.activeport.web.domain.AddressAllocation;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the AddressAllocation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AddressAllocationRepository extends JpaRepository<AddressAllocation, Long> {}
