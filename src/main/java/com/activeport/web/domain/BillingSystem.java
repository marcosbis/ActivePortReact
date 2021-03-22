package com.activeport.web.domain;

import com.activeport.web.domain.enumeration.BillingTypeEnum;
import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A BillingSystem.
 */
@Entity
@Table(name = "billing_system")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BillingSystem implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3, max = 30)
    @Column(name = "name", length = 30, nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "api", nullable = false)
    private BillingTypeEnum api;

    @Column(name = "stage")
    private String stage;

    @Column(name = "username")
    private String username;

    @Column(name = "secret")
    private String secret;

    @Column(name = "private_key_cert")
    private String privateKeyCert;

    @Column(name = "private_key_password")
    private String privateKeyPassword;

    @Column(name = "billing_uid")
    private String billingUid;

    @Column(name = "start_billing")
    private ZonedDateTime startBilling;

    @Column(name = "currency_code")
    private String currencyCode;

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

    public BillingSystem name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public BillingSystem description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BillingTypeEnum getApi() {
        return api;
    }

    public BillingSystem api(BillingTypeEnum api) {
        this.api = api;
        return this;
    }

    public void setApi(BillingTypeEnum api) {
        this.api = api;
    }

    public String getStage() {
        return stage;
    }

    public BillingSystem stage(String stage) {
        this.stage = stage;
        return this;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getUsername() {
        return username;
    }

    public BillingSystem username(String username) {
        this.username = username;
        return this;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSecret() {
        return secret;
    }

    public BillingSystem secret(String secret) {
        this.secret = secret;
        return this;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getPrivateKeyCert() {
        return privateKeyCert;
    }

    public BillingSystem privateKeyCert(String privateKeyCert) {
        this.privateKeyCert = privateKeyCert;
        return this;
    }

    public void setPrivateKeyCert(String privateKeyCert) {
        this.privateKeyCert = privateKeyCert;
    }

    public String getPrivateKeyPassword() {
        return privateKeyPassword;
    }

    public BillingSystem privateKeyPassword(String privateKeyPassword) {
        this.privateKeyPassword = privateKeyPassword;
        return this;
    }

    public void setPrivateKeyPassword(String privateKeyPassword) {
        this.privateKeyPassword = privateKeyPassword;
    }

    public String getBillingUid() {
        return billingUid;
    }

    public BillingSystem billingUid(String billingUid) {
        this.billingUid = billingUid;
        return this;
    }

    public void setBillingUid(String billingUid) {
        this.billingUid = billingUid;
    }

    public ZonedDateTime getStartBilling() {
        return startBilling;
    }

    public BillingSystem startBilling(ZonedDateTime startBilling) {
        this.startBilling = startBilling;
        return this;
    }

    public void setStartBilling(ZonedDateTime startBilling) {
        this.startBilling = startBilling;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public BillingSystem currencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
        return this;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BillingSystem)) {
            return false;
        }
        return id != null && id.equals(((BillingSystem) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BillingSystem{" +
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
