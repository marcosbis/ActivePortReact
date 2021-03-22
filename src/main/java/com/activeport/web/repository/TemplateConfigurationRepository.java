package com.activeport.web.repository;

import com.activeport.web.domain.TemplateConfiguration;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TemplateConfiguration entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TemplateConfigurationRepository extends JpaRepository<TemplateConfiguration, Long> {}
