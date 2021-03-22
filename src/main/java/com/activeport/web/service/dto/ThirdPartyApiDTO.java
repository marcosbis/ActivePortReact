package com.activeport.web.service.dto;

import com.activeport.web.domain.enumeration.ApiType;
import com.activeport.web.domain.enumeration.PortApiTypeEnum;
import java.io.Serializable;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.activeport.web.domain.ThirdPartyApi} entity.
 */
public class ThirdPartyApiDTO implements Serializable {
    private Long id;

    @NotNull
    @Size(min = 3, max = 30)
    private String name;

    private String description;

    @NotNull
    private ApiType api;

    private String stage;

    private String username;

    private String secret;

    private String privateKeyCert;

    private String privateKeyPassword;

    private String billingUid;

    private String productUid;

    private String endpoint;

    private String allowSharedPortsUid;

    private PortApiTypeEnum connectionType;

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

    public ApiType getApi() {
        return api;
    }

    public void setApi(ApiType api) {
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

    public String getProductUid() {
        return productUid;
    }

    public void setProductUid(String productUid) {
        this.productUid = productUid;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getAllowSharedPortsUid() {
        return allowSharedPortsUid;
    }

    public void setAllowSharedPortsUid(String allowSharedPortsUid) {
        this.allowSharedPortsUid = allowSharedPortsUid;
    }

    public PortApiTypeEnum getConnectionType() {
        return connectionType;
    }

    public void setConnectionType(PortApiTypeEnum connectionType) {
        this.connectionType = connectionType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ThirdPartyApiDTO)) {
            return false;
        }

        return id != null && id.equals(((ThirdPartyApiDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ThirdPartyApiDTO{" +
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
