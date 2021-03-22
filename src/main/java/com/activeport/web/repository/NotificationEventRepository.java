package com.activeport.web.repository;

import com.activeport.web.domain.NotificationEvent;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the NotificationEvent entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NotificationEventRepository extends JpaRepository<NotificationEvent, Long> {}
