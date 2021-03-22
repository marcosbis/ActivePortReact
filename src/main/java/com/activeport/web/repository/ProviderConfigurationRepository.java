package com.activeport.web.repository;

import com.activeport.web.domain.ProviderConfiguration;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ProviderConfiguration entity.
 */
@Repository
public interface ProviderConfigurationRepository extends JpaRepository<ProviderConfiguration, Long> {
    @Query(
        value = "select distinct providerConfiguration from ProviderConfiguration providerConfiguration left join fetch providerConfiguration.services",
        countQuery = "select count(distinct providerConfiguration) from ProviderConfiguration providerConfiguration"
    )
    Page<ProviderConfiguration> findAllWithEagerRelationships(Pageable pageable);

    @Query(
        "select distinct providerConfiguration from ProviderConfiguration providerConfiguration left join fetch providerConfiguration.services"
    )
    List<ProviderConfiguration> findAllWithEagerRelationships();

    @Query(
        "select providerConfiguration from ProviderConfiguration providerConfiguration left join fetch providerConfiguration.services where providerConfiguration.id =:id"
    )
    Optional<ProviderConfiguration> findOneWithEagerRelationships(@Param("id") Long id);
}
