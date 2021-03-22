package com.activeport.web.repository;

import com.activeport.web.domain.VntuDownlinkPort;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the VntuDownlinkPort entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VntuDownlinkPortRepository extends JpaRepository<VntuDownlinkPort, Long> {}
