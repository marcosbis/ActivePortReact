package com.activeport.web.repository;

import com.activeport.web.domain.JunoCommandLog;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the JunoCommandLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface JunoCommandLogRepository extends JpaRepository<JunoCommandLog, Long> {}
