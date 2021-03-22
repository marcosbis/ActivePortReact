package com.activeport.web.repository;

import com.activeport.web.domain.ServiceConfiguration;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ServiceConfiguration entity.
 */
@Repository
public interface ServiceConfigurationRepository extends JpaRepository<ServiceConfiguration, Long> {
    @Query(
        value = "select distinct serviceConfiguration from ServiceConfiguration serviceConfiguration left join fetch serviceConfiguration.commands",
        countQuery = "select count(distinct serviceConfiguration) from ServiceConfiguration serviceConfiguration"
    )
    Page<ServiceConfiguration> findAllWithEagerRelationships(Pageable pageable);

    @Query(
        "select distinct serviceConfiguration from ServiceConfiguration serviceConfiguration left join fetch serviceConfiguration.commands"
    )
    List<ServiceConfiguration> findAllWithEagerRelationships();

    @Query(
        "select serviceConfiguration from ServiceConfiguration serviceConfiguration left join fetch serviceConfiguration.commands where serviceConfiguration.id =:id"
    )
    Optional<ServiceConfiguration> findOneWithEagerRelationships(@Param("id") Long id);
}
