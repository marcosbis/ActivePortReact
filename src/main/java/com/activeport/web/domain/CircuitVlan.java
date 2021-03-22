package com.activeport.web.domain;

import com.activeport.web.domain.enumeration.ConnectTypeEnum;
import com.activeport.web.domain.enumeration.InternetTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A CircuitVlan.
 */
@Entity
@Table(name = "circuit_vlan")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CircuitVlan implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "zone")
    private String zone;

    @Column(name = "service_key")
    private String serviceKey;

    @Column(name = "vlan_id")
    private Integer vlanId;

    @Column(name = "rd")
    private String rd;

    @Column(name = "service_id")
    private Long serviceId;

    @Column(name = "tenant_name")
    private String tenantName;

    @Column(name = "child_service_id")
    private Long childServiceId;

    @Column(name = "child_ntu_id")
    private Long childNtuId;

    @Column(name = "realm_ip")
    private String realmIp;

    @Enumerated(EnumType.STRING)
    @Column(name = "internet_type")
    private InternetTypeEnum internetType;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private ConnectTypeEnum type;

    @ManyToOne
    @JsonIgnoreProperties(value = "circuitVlans", allowSetters = true)
    private ServiceConfiguration serviceConfiguration;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getZone() {
        return zone;
    }

    public CircuitVlan zone(String zone) {
        this.zone = zone;
        return this;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getServiceKey() {
        return serviceKey;
    }

    public CircuitVlan serviceKey(String serviceKey) {
        this.serviceKey = serviceKey;
        return this;
    }

    public void setServiceKey(String serviceKey) {
        this.serviceKey = serviceKey;
    }

    public Integer getVlanId() {
        return vlanId;
    }

    public CircuitVlan vlanId(Integer vlanId) {
        this.vlanId = vlanId;
        return this;
    }

    public void setVlanId(Integer vlanId) {
        this.vlanId = vlanId;
    }

    public String getRd() {
        return rd;
    }

    public CircuitVlan rd(String rd) {
        this.rd = rd;
        return this;
    }

    public void setRd(String rd) {
        this.rd = rd;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public CircuitVlan serviceId(Long serviceId) {
        this.serviceId = serviceId;
        return this;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public String getTenantName() {
        return tenantName;
    }

    public CircuitVlan tenantName(String tenantName) {
        this.tenantName = tenantName;
        return this;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public Long getChildServiceId() {
        return childServiceId;
    }

    public CircuitVlan childServiceId(Long childServiceId) {
        this.childServiceId = childServiceId;
        return this;
    }

    public void setChildServiceId(Long childServiceId) {
        this.childServiceId = childServiceId;
    }

    public Long getChildNtuId() {
        return childNtuId;
    }

    public CircuitVlan childNtuId(Long childNtuId) {
        this.childNtuId = childNtuId;
        return this;
    }

    public void setChildNtuId(Long childNtuId) {
        this.childNtuId = childNtuId;
    }

    public String getRealmIp() {
        return realmIp;
    }

    public CircuitVlan realmIp(String realmIp) {
        this.realmIp = realmIp;
        return this;
    }

    public void setRealmIp(String realmIp) {
        this.realmIp = realmIp;
    }

    public InternetTypeEnum getInternetType() {
        return internetType;
    }

    public CircuitVlan internetType(InternetTypeEnum internetType) {
        this.internetType = internetType;
        return this;
    }

    public void setInternetType(InternetTypeEnum internetType) {
        this.internetType = internetType;
    }

    public ConnectTypeEnum getType() {
        return type;
    }

    public CircuitVlan type(ConnectTypeEnum type) {
        this.type = type;
        return this;
    }

    public void setType(ConnectTypeEnum type) {
        this.type = type;
    }

    public ServiceConfiguration getServiceConfiguration() {
        return serviceConfiguration;
    }

    public CircuitVlan serviceConfiguration(ServiceConfiguration serviceConfiguration) {
        this.serviceConfiguration = serviceConfiguration;
        return this;
    }

    public void setServiceConfiguration(ServiceConfiguration serviceConfiguration) {
        this.serviceConfiguration = serviceConfiguration;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CircuitVlan)) {
            return false;
        }
        return id != null && id.equals(((CircuitVlan) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CircuitVlan{" +
            "id=" + getId() +
            ", zone='" + getZone() + "'" +
            ", serviceKey='" + getServiceKey() + "'" +
            ", vlanId=" + getVlanId() +
            ", rd='" + getRd() + "'" +
            ", serviceId=" + getServiceId() +
            ", tenantName='" + getTenantName() + "'" +
            ", childServiceId=" + getChildServiceId() +
            ", childNtuId=" + getChildNtuId() +
            ", realmIp='" + getRealmIp() + "'" +
            ", internetType='" + getInternetType() + "'" +
            ", type='" + getType() + "'" +
            "}";
    }
}
