package com.activeport.web.repository;

import com.activeport.web.domain.XeroAccount;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the XeroAccount entity.
 */
@SuppressWarnings("unused")
@Repository
public interface XeroAccountRepository extends JpaRepository<XeroAccount, Long> {}
