package com.activeport.web.repository;

import com.activeport.web.domain.ThirdPartyApi;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ThirdPartyApi entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ThirdPartyApiRepository extends JpaRepository<ThirdPartyApi, Long> {}
