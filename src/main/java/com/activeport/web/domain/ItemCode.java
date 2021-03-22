package com.activeport.web.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ItemCode.
 */
@Entity
@Table(name = "item_code")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ItemCode implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @Column(name = "code_rate")
    private String codeRate;

    @Column(name = "code_activation")
    private String codeActivation;

    @Column(name = "description")
    private String description;

    @Column(name = "local_price_rate")
    private Float localPriceRate;

    @Column(name = "local_price_montlhy")
    private Float localPriceMontlhy;

    @Column(name = "local_price_activation")
    private Float localPriceActivation;

    @Column(name = "code_up_lift")
    private String codeUpLift;

    @Column(name = "up_lift")
    private Float upLift;

    @Column(name = "use_up_lift")
    private Boolean useUpLift;

    @Column(name = "use_local_price")
    private Boolean useLocalPrice;

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

    public ItemCode name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public ItemCode code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCodeRate() {
        return codeRate;
    }

    public ItemCode codeRate(String codeRate) {
        this.codeRate = codeRate;
        return this;
    }

    public void setCodeRate(String codeRate) {
        this.codeRate = codeRate;
    }

    public String getCodeActivation() {
        return codeActivation;
    }

    public ItemCode codeActivation(String codeActivation) {
        this.codeActivation = codeActivation;
        return this;
    }

    public void setCodeActivation(String codeActivation) {
        this.codeActivation = codeActivation;
    }

    public String getDescription() {
        return description;
    }

    public ItemCode description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getLocalPriceRate() {
        return localPriceRate;
    }

    public ItemCode localPriceRate(Float localPriceRate) {
        this.localPriceRate = localPriceRate;
        return this;
    }

    public void setLocalPriceRate(Float localPriceRate) {
        this.localPriceRate = localPriceRate;
    }

    public Float getLocalPriceMontlhy() {
        return localPriceMontlhy;
    }

    public ItemCode localPriceMontlhy(Float localPriceMontlhy) {
        this.localPriceMontlhy = localPriceMontlhy;
        return this;
    }

    public void setLocalPriceMontlhy(Float localPriceMontlhy) {
        this.localPriceMontlhy = localPriceMontlhy;
    }

    public Float getLocalPriceActivation() {
        return localPriceActivation;
    }

    public ItemCode localPriceActivation(Float localPriceActivation) {
        this.localPriceActivation = localPriceActivation;
        return this;
    }

    public void setLocalPriceActivation(Float localPriceActivation) {
        this.localPriceActivation = localPriceActivation;
    }

    public String getCodeUpLift() {
        return codeUpLift;
    }

    public ItemCode codeUpLift(String codeUpLift) {
        this.codeUpLift = codeUpLift;
        return this;
    }

    public void setCodeUpLift(String codeUpLift) {
        this.codeUpLift = codeUpLift;
    }

    public Float getUpLift() {
        return upLift;
    }

    public ItemCode upLift(Float upLift) {
        this.upLift = upLift;
        return this;
    }

    public void setUpLift(Float upLift) {
        this.upLift = upLift;
    }

    public Boolean isUseUpLift() {
        return useUpLift;
    }

    public ItemCode useUpLift(Boolean useUpLift) {
        this.useUpLift = useUpLift;
        return this;
    }

    public void setUseUpLift(Boolean useUpLift) {
        this.useUpLift = useUpLift;
    }

    public Boolean isUseLocalPrice() {
        return useLocalPrice;
    }

    public ItemCode useLocalPrice(Boolean useLocalPrice) {
        this.useLocalPrice = useLocalPrice;
        return this;
    }

    public void setUseLocalPrice(Boolean useLocalPrice) {
        this.useLocalPrice = useLocalPrice;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ItemCode)) {
            return false;
        }
        return id != null && id.equals(((ItemCode) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ItemCode{" +
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
