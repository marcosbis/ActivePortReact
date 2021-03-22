package com.activeport.web.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.activeport.web.domain.RateChangeLog} entity.
 */
public class RateChangeLogDTO implements Serializable {
    private Long id;

    private Integer previuosRate;

    private Integer rate;

    private Long userServiceId;

    private String userServiceName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPreviuosRate() {
        return previuosRate;
    }

    public void setPreviuosRate(Integer previuosRate) {
        this.previuosRate = previuosRate;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public Long getUserServiceId() {
        return userServiceId;
    }

    public void setUserServiceId(Long userServiceId) {
        this.userServiceId = userServiceId;
    }

    public String getUserServiceName() {
        return userServiceName;
    }

    public void setUserServiceName(String userServiceName) {
        this.userServiceName = userServiceName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RateChangeLogDTO)) {
            return false;
        }

        return id != null && id.equals(((RateChangeLogDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RateChangeLogDTO{" +
            "id=" + getId() +
            ", previuosRate=" + getPreviuosRate() +
            ", rate=" + getRate() +
            ", userServiceId=" + getUserServiceId() +
            ", userServiceName='" + getUserServiceName() + "'" +
            "}";
    }
}
