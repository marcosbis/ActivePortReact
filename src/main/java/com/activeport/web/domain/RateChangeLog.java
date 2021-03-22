package com.activeport.web.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A RateChangeLog.
 */
@Entity
@Table(name = "rate_change_log")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RateChangeLog implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "previuos_rate")
    private Integer previuosRate;

    @Column(name = "rate")
    private Integer rate;

    @ManyToOne
    @JsonIgnoreProperties(value = "rateChangeLogs", allowSetters = true)
    private UserService userService;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPreviuosRate() {
        return previuosRate;
    }

    public RateChangeLog previuosRate(Integer previuosRate) {
        this.previuosRate = previuosRate;
        return this;
    }

    public void setPreviuosRate(Integer previuosRate) {
        this.previuosRate = previuosRate;
    }

    public Integer getRate() {
        return rate;
    }

    public RateChangeLog rate(Integer rate) {
        this.rate = rate;
        return this;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public UserService getUserService() {
        return userService;
    }

    public RateChangeLog userService(UserService userService) {
        this.userService = userService;
        return this;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RateChangeLog)) {
            return false;
        }
        return id != null && id.equals(((RateChangeLog) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RateChangeLog{" +
            "id=" + getId() +
            ", previuosRate=" + getPreviuosRate() +
            ", rate=" + getRate() +
            "}";
    }
}
