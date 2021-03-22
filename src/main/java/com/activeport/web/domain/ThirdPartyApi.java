package com.activeport.web.domain;

import com.activeport.web.domain.enumeration.ApiType;
import com.activeport.web.domain.enumeration.PortApiTypeEnum;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ThirdPartyApi.
 */
@Entity
@Table(name = "third_party_api")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ThirdPartyApi implements Serializable {
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
    private ApiType api;

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

    @Column(name = "product_uid")
    private String productUid;

    @Column(name = "endpoint")
    private String endpoint;

    @Column(name = "allow_shared_ports_uid")
    private String allowSharedPortsUid;

    @Enumerated(EnumType.STRING)
    @Column(name = "connection_type")
    private PortApiTypeEnum connectionType;

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

    public ThirdPartyApi name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public ThirdPartyApi description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ApiType getApi() {
        return api;
    }

    public ThirdPartyApi api(ApiType api) {
        this.api = api;
        return this;
    }

    public void setApi(ApiType api) {
        this.api = api;
    }

    public String getStage() {
        return stage;
    }

    public ThirdPartyApi stage(String stage) {
        this.stage = stage;
        return this;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getUsername() {
        return username;
    }

    public ThirdPartyApi username(String username) {
        this.username = username;
        return this;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSecret() {
        return secret;
    }

    public ThirdPartyApi secret(String secret) {
        this.secret = secret;
        return this;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getPrivateKeyCert() {
        return privateKeyCert;
    }

    public ThirdPartyApi privateKeyCert(String privateKeyCert) {
        this.privateKeyCert = privateKeyCert;
        return this;
    }

    public void setPrivateKeyCert(String privateKeyCert) {
        this.privateKeyCert = privateKeyCert;
    }

    public String getPrivateKeyPassword() {
        return privateKeyPassword;
    }

    public ThirdPartyApi privateKeyPassword(String privateKeyPassword) {
        this.privateKeyPassword = privateKeyPassword;
        return this;
    }

    public void setPrivateKeyPassword(String privateKeyPassword) {
        this.privateKeyPassword = privateKeyPassword;
    }

    public String getBillingUid() {
        return billingUid;
    }

    public ThirdPartyApi billingUid(String billingUid) {
        this.billingUid = billingUid;
        return this;
    }

    public void setBillingUid(String billingUid) {
        this.billingUid = billingUid;
    }

    public String getProductUid() {
        return productUid;
    }

    public ThirdPartyApi productUid(String productUid) {
        this.productUid = productUid;
        return this;
    }

    public void setProductUid(String productUid) {
        this.productUid = productUid;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public ThirdPartyApi endpoint(String endpoint) {
        this.endpoint = endpoint;
        return this;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getAllowSharedPortsUid() {
        return allowSharedPortsUid;
    }

    public ThirdPartyApi allowSharedPortsUid(String allowSharedPortsUid) {
        this.allowSharedPortsUid = allowSharedPortsUid;
        return this;
    }

    public void setAllowSharedPortsUid(String allowSharedPortsUid) {
        this.allowSharedPortsUid = allowSharedPortsUid;
    }

    public PortApiTypeEnum getConnectionType() {
        return connectionType;
    }

    public ThirdPartyApi connectionType(PortApiTypeEnum connectionType) {
        this.connectionType = connectionType;
        return this;
    }

    public void setConnectionType(PortApiTypeEnum connectionType) {
        this.connectionType = connectionType;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ThirdPartyApi)) {
            return false;
        }
        return id != null && id.equals(((ThirdPartyApi) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ThirdPartyApi{" +
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
            ", productUid='" + getProductUid() + "'" +
            ", endpoint='" + getEndpoint() + "'" +
            ", allowSharedPortsUid='" + getAllowSharedPortsUid() + "'" +
            ", connectionType='" + getConnectionType() + "'" +
            "}";
    }
}
