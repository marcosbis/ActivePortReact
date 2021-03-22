package com.activeport.web.service.dto;

import com.activeport.web.domain.enumeration.BillingTypeEnum;
import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.activeport.web.domain.BillingSystem} entity.
 */
public class BillingSystemDTO implements Serializable {
    private Long id;

    @NotNull
    @Size(min = 3, max = 30)
    private String name;

    private String description;

    @NotNull
    private BillingTypeEnum api;

    private String stage;

    private String username;

    private String secret;

    private String privateKeyCert;

    private String privateKeyPassword;

    private String billingUid;

    private ZonedDateTime startBilling;

    private String currencyCode;

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

    public BillingTypeEnum getApi() {
        return api;
    }

    public void setApi(BillingTypeEnum api) {
        this.api = api;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getPrivateKeyCert() {
        return privateKeyCert;
    }

    public void setPrivateKeyCert(String privateKeyCert) {
        this.privateKeyCert = privateKeyCert;
    }

    public String getPrivateKeyPassword() {
        return privateKeyPassword;
    }

    public void setPrivateKeyPassword(String privateKeyPassword) {
        this.privateKeyPassword = privateKeyPassword;
    }

    public String getBillingUid() {
        return billingUid;
    }

    public void setBillingUid(String billingUid) {
        this.billingUid = billingUid;
    }

    public ZonedDateTime getStartBilling() {
        return startBilling;
    }

    public void setStartBilling(ZonedDateTime startBilling) {
        this.startBilling = startBilling;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BillingSystemDTO)) {
            return false;
        }

        return id != null && id.equals(((BillingSystemDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BillingSystemDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", api='" + getApi() + "'" +
            ", stage='" + getStage() + "'" +
            ", username='" + getUsername() + "'" +
            ", secret='" + getSecret() + "'" +
            ", privateKeyCert='" + getPrivateKeyCert() + "'" +
            ", privateKeyPassword='" + getPrivateKeyPassword() + "'" +
            ", billingUid='" + getBillingUid() + "'" +
            ", startBilling='" + getStartBilling() + "'" +
            ", currencyCode='" + getCurrencyCode() + "'" +
            "}";
    }
}
