package com.activeport.web.service.dto;

import com.activeport.web.domain.enumeration.RangeTypeEnum;
import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * A DTO for the {@link com.activeport.web.domain.PolicerRange} entity.
 */
public class PolicerRangeDTO implements Serializable {
    private Long id;

    private String name;

    private Integer burst;

    private Integer bandwidth;

    private ZonedDateTime startPolicer;

    private ZonedDateTime endPolicer;

    private RangeTypeEnum rangeType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBurst() {
        return burst;
    }

    public void setBurst(Integer burst) {
        this.burst = burst;
    }

    public Integer getBandwidth() {
        return bandwidth;
    }

    public void setBandwidth(Integer bandwidth) {
        this.bandwidth = bandwidth;
    }

    public ZonedDateTime getStartPolicer() {
        return startPolicer;
    }

    public void setStartPolicer(ZonedDateTime startPolicer) {
        this.startPolicer = startPolicer;
    }

    public ZonedDateTime getEndPolicer() {
        return endPolicer;
    }

    public void setEndPolicer(ZonedDateTime endPolicer) {
        this.endPolicer = endPolicer;
    }

    public RangeTypeEnum getRangeType() {
        return rangeType;
    }

    public void setRangeType(RangeTypeEnum rangeType) {
        this.rangeType = rangeType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PolicerRangeDTO)) {
            return false;
        }

        return id != null && id.equals(((PolicerRangeDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PolicerRangeDTO{" +
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
