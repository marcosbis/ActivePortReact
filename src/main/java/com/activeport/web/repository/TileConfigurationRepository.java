package com.activeport.web.repository;

import com.activeport.web.domain.TileConfiguration;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TileConfiguration entity.
 */
@Repository
public interface TileConfigurationRepository extends JpaRepository<TileConfiguration, Long> {
    @Query(
        value = "select distinct tileConfiguration from TileConfiguration tileConfiguration left join fetch tileConfiguration.services",
        countQuery = "select count(distinct tileConfiguration) from TileConfiguration tileConfiguration"
    )
    Page<TileConfiguration> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct tileConfiguration from TileConfiguration tileConfiguration left join fetch tileConfiguration.services")
    List<TileConfiguration> findAllWithEagerRelationships();

    @Query(
        "select tileConfiguration from TileConfiguration tileConfiguration left join fetch tileConfiguration.services where tileConfiguration.id =:id"
    )
    Optional<TileConfiguration> findOneWithEagerRelationships(@Param("id") Long id);
}
