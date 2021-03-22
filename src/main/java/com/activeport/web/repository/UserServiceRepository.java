package com.activeport.web.repository;

import com.activeport.web.domain.UserService;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the UserService entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserServiceRepository extends JpaRepository<UserService, Long> {}
