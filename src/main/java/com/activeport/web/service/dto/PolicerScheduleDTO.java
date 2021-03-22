package com.activeport.web.service.dto;

import com.activeport.web.domain.enumeration.ScheduleDayEnum;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A DTO for the {@link com.activeport.web.domain.PolicerSchedule} entity.
 */
public class PolicerScheduleDTO implements Serializable {
    private Long id;

    private String name;

    private String description;

    private ScheduleDayEnum days;

    private Long ntuId;

    private String ntuName;
    private Set<PolicerRangeDTO> policerRanges = new HashSet<>();

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ScheduleDayEnum getDays() {
        return days;
    }

    public void setDays(ScheduleDayEnum days) {
        this.days = days;
    }

    public Long getNtuId() {
        return ntuId;
    }

    public void setNtuId(Long ntuId) {
        this.ntuId = ntuId;
    }

    public String getNtuName() {
        return ntuName;
    }

    public void setNtuName(String ntuName) {
        this.ntuName = ntuName;
    }

    public Set<PolicerRangeDTO> getPolicerRanges() {
        return policerRanges;
    }

    public void setPolicerRanges(Set<PolicerRangeDTO> policerRanges) {
        this.policerRanges = policerRanges;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PolicerScheduleDTO)) {
            return false;
        }

        return id != null && id.equals(((PolicerScheduleDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PolicerScheduleDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", days='" + getDays() + "'" +
            ", ntuId=" + getNtuId() +
            ", ntuName='" + getNtuName() + "'" +
            ", policerRanges='" + getPolicerRanges() + "'" +
            "}";
    }
}
