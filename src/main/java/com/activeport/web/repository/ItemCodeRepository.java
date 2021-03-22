package com.activeport.web.repository;

import com.activeport.web.domain.ItemCode;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ItemCode entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ItemCodeRepository extends JpaRepository<ItemCode, Long> {}
