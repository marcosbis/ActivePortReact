package com.activeport.web.repository;

import com.activeport.web.domain.DeviceConfiguration;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the DeviceConfiguration entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DeviceConfigurationRepository extends JpaRepository<DeviceConfiguration, Long> {}
