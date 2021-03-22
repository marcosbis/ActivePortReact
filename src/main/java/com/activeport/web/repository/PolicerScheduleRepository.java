package com.activeport.web.repository;

import com.activeport.web.domain.PolicerSchedule;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the PolicerSchedule entity.
 */
@Repository
public interface PolicerScheduleRepository extends JpaRepository<PolicerSchedule, Long> {
    @Query(
        value = "select distinct policerSchedule from PolicerSchedule policerSchedule left join fetch policerSchedule.policerRanges",
        countQuery = "select count(distinct policerSchedule) from PolicerSchedule policerSchedule"
    )
    Page<PolicerSchedule> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct policerSchedule from PolicerSchedule policerSchedule left join fetch policerSchedule.policerRanges")
    List<PolicerSchedule> findAllWithEagerRelationships();

    @Query(
        "select policerSchedule from PolicerSchedule policerSchedule left join fetch policerSchedule.policerRanges where policerSchedule.id =:id"
    )
    Optional<PolicerSchedule> findOneWithEagerRelationships(@Param("id") Long id);
}
