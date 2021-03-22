package com.activeport.web.repository;

import com.activeport.web.domain.MarketPlace;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the MarketPlace entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MarketPlaceRepository extends JpaRepository<MarketPlace, Long> {}
