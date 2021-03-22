package com.activeport.web.domain;

import com.activeport.web.domain.enumeration.ScheduleDayEnum;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A PolicerSchedule.
 */
@Entity
@Table(name = "policer_schedule")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PolicerSchedule implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "days")
    private ScheduleDayEnum days;

    @OneToOne
    @JoinColumn(unique = true)
    private Ntu ntu;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(
        name = "policer_schedule_policer_range",
        joinColumns = @JoinColumn(name = "policer_schedule_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "policer_range_id", referencedColumnName = "id")
    )
    private Set<PolicerRange> policerRanges = new HashSet<>();

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

    public PolicerSchedule name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public PolicerSchedule description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ScheduleDayEnum getDays() {
        return days;
    }

    public PolicerSchedule days(ScheduleDayEnum days) {
        this.days = days;
        return this;
    }

    public void setDays(ScheduleDayEnum days) {
        this.days = days;
    }

    public Ntu getNtu() {
        return ntu;
    }

    public PolicerSchedule ntu(Ntu ntu) {
        this.ntu = ntu;
        return this;
    }

    public void setNtu(Ntu ntu) {
        this.ntu = ntu;
    }

    public Set<PolicerRange> getPolicerRanges() {
        return policerRanges;
    }

    public PolicerSchedule policerRanges(Set<PolicerRange> policerRanges) {
        this.policerRanges = policerRanges;
        return this;
    }

    public PolicerSchedule addPolicerRange(PolicerRange policerRange) {
        this.policerRanges.add(policerRange);
        policerRange.getPolicerSchedules().add(this);
        return this;
    }

    public PolicerSchedule removePolicerRange(PolicerRange policerRange) {
        this.policerRanges.remove(policerRange);
        policerRange.getPolicerSchedules().remove(this);
        return this;
    }

    public void setPolicerRanges(Set<PolicerRange> policerRanges) {
        this.policerRanges = policerRanges;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PolicerSchedule)) {
            return false;
        }
        return id != null && id.equals(((PolicerSchedule) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PolicerSchedule{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", days='" + getDays() + "'" +
            "}";
    }
}
