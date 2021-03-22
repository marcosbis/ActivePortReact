package com.activeport.web.repository;

import com.activeport.web.domain.Ntu;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Ntu entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NtuRepository extends JpaRepository<Ntu, Long> {}
