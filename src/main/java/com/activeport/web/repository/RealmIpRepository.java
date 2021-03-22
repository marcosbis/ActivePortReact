package com.activeport.web.repository;

import com.activeport.web.domain.RealmIp;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the RealmIp entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RealmIpRepository extends JpaRepository<RealmIp, Long> {}
