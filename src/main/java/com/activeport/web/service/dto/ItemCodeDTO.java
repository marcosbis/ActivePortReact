package com.activeport.web.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.activeport.web.domain.ItemCode} entity.
 */
public class ItemCodeDTO implements Serializable {
    private Long id;

    private String name;

    private String code;

    private String codeRate;

    private String codeActivation;

    private String description;

    private Float localPriceRate;

    private Float localPriceMontlhy;

    private Float localPriceActivation;

    private String codeUpLift;

    private Float upLift;

    private Boolean useUpLift;

    private Boolean useLocalPrice;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCodeRate() {
        return codeRate;
    }

    public void setCodeRate(String codeRate) {
        this.codeRate = codeRate;
    }

    public String getCodeActivation() {
        return codeActivation;
    }

    public void setCodeActivation(String codeActivation) {
        this.codeActivation = codeActivation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getLocalPriceRate() {
        return localPriceRate;
    }

    public void setLocalPriceRate(Float localPriceRate) {
        this.localPriceRate = localPriceRate;
    }

    public Float getLocalPriceMontlhy() {
        return localPriceMontlhy;
    }

    public void setLocalPriceMontlhy(Float localPriceMontlhy) {
        this.localPriceMontlhy = localPriceMontlhy;
    }

    public Float getLocalPriceActivation() {
        return localPriceActivation;
    }

    public void setLocalPriceActivation(Float localPriceActivation) {
        this.localPriceActivation = localPriceActivation;
    }

    public String getCodeUpLift() {
        return codeUpLift;
    }

    public void setCodeUpLift(String codeUpLift) {
        this.codeUpLift = codeUpLift;
    }

    public Float getUpLift() {
        return upLift;
    }

    public void setUpLift(Float upLift) {
        this.upLift = upLift;
    }

    public Boolean isUseUpLift() {
        return useUpLift;
    }

    public void setUseUpLift(Boolean useUpLift) {
        this.useUpLift = useUpLift;
    }

    public Boolean isUseLocalPrice() {
        return useLocalPrice;
    }

    public void setUseLocalPrice(Boolean useLocalPrice) {
        this.useLocalPrice = useLocalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ItemCodeDTO)) {
            return false;
        }

        return id != null && id.equals(((ItemCodeDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ItemCodeDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", code='" + getCode() + "'" +
            ", codeRate='" + getCodeRate() + "'" +
            ", codeActivation='" + getCodeActivation() + "'" +
            ", description='" + getDescription() + "'" +
            ", localPriceRate=" + getLocalPriceRate() +
            ", localPriceMontlhy=" + getLocalPriceMontlhy() +
            ", localPriceActivation=" + getLocalPriceActivation() +
            ", codeUpLift='" + getCodeUpLift() + "'" +
            ", upLift=" + getUpLift() +
            ", useUpLift='" + isUseUpLift() + "'" +
            ", useLocalPrice='" + isUseLocalPrice() + "'" +
            "}";
    }
}
