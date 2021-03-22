package com.activeport.web.domain;

import com.activeport.web.domain.enumeration.RangeTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A PolicerRange.
 */
@Entity
@Table(name = "policer_range")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PolicerRange implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "burst")
    private Integer burst;

    @Column(name = "bandwidth")
    private Integer bandwidth;

    @Column(name = "start_policer")
    private ZonedDateTime startPolicer;

    @Column(name = "end_policer")
    private ZonedDateTime endPolicer;

    @Enumerated(EnumType.STRING)
    @Column(name = "range_type")
    private RangeTypeEnum rangeType;

    @ManyToMany(mappedBy = "policerRanges")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnore
    private Set<PolicerSchedule> policerSchedules = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public PolicerRange name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBurst() {
        return burst;
    }

    public PolicerRange burst(Integer burst) {
        this.burst = burst;
        return this;
    }

    public void setBurst(Integer burst) {
        this.burst = burst;
    }

    public Integer getBandwidth() {
        return bandwidth;
    }

    public PolicerRange bandwidth(Integer bandwidth) {
        this.bandwidth = bandwidth;
        return this;
    }

    public void setBandwidth(Integer bandwidth) {
        this.bandwidth = bandwidth;
    }

    public ZonedDateTime getStartPolicer() {
        return startPolicer;
    }

    public PolicerRange startPolicer(ZonedDateTime startPolicer) {
        this.startPolicer = startPolicer;
        return this;
    }

    public void setStartPolicer(ZonedDateTime startPolicer) {
        this.startPolicer = startPolicer;
    }

    public ZonedDateTime getEndPolicer() {
        return endPolicer;
    }

    public PolicerRange endPolicer(ZonedDateTime endPolicer) {
        this.endPolicer = endPolicer;
        return this;
    }

    public void setEndPolicer(ZonedDateTime endPolicer) {
        this.endPolicer = endPolicer;
    }

    public RangeTypeEnum getRangeType() {
        return rangeType;
    }

    public PolicerRange rangeType(RangeTypeEnum rangeType) {
        this.rangeType = rangeType;
        return this;
    }

    public void setRangeType(RangeTypeEnum rangeType) {
        this.rangeType = rangeType;
    }

    public Set<PolicerSchedule> getPolicerSchedules() {
        return policerSchedules;
    }

    public PolicerRange policerSchedules(Set<PolicerSchedule> policerSchedules) {
        this.policerSchedules = policerSchedules;
        return this;
    }

    public PolicerRange addPolicerSchedule(PolicerSchedule policerSchedule) {
        this.policerSchedules.add(policerSchedule);
        policerSchedule.getPolicerRanges().add(this);
        return this;
    }

    public PolicerRange removePolicerSchedule(PolicerSchedule policerSchedule) {
        this.policerSchedules.remove(policerSchedule);
        policerSchedule.getPolicerRanges().remove(this);
        return this;
    }

    public void setPolicerSchedules(Set<PolicerSchedule> policerSchedules) {
        this.policerSchedules = policerSchedules;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PolicerRange)) {
            return false;
        }
        return id != null && id.equals(((PolicerRange) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PolicerRange{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", burst=" + getBurst() +
            ", bandwidth=" + getBandwidth() +
            ", startPolicer='" + getStartPolicer() + "'" +
            ", endPolicer='" + getEndPolicer() + "'" +
            ", rangeType='" + getRangeType() + "'" +
            "}";
    }
}
