package com.activeport.web.repository;

import com.activeport.web.domain.ServiceCommand;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ServiceCommand entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ServiceCommandRepository extends JpaRepository<ServiceCommand, Long> {}
