package com.activeport.web.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A VntuDownlinkPort.
 */
@Entity
@Table(name = "vntu_downlink_port")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class VntuDownlinkPort implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "uid")
    private String uid;

    @Column(name = "description")
    private String description;

    @Column(name = "assigned_org_name")
    private String assignedOrgName;

    @Column(name = "assigned_tenant_name")
    private String assignedTenantName;

    @Column(name = "assigned_org_id")
    private String assignedOrgId;

    @Column(name = "assigned_tenant_id")
    private String assignedTenantId;

    @Column(name = "assigned_vntu_id")
    private Long assignedVntuId;

    @Column(name = "assigned_vntu_name")
    private String assignedVntuName;

    @ManyToOne
    @JsonIgnoreProperties(value = "vntuDownlinkPorts", allowSetters = true)
    private CentralSwitch centralSwitch;

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

    public VntuDownlinkPort name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public VntuDownlinkPort uid(String uid) {
        this.uid = uid;
        return this;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDescription() {
        return description;
    }

    public VntuDownlinkPort description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAssignedOrgName() {
        return assignedOrgName;
    }

    public VntuDownlinkPort assignedOrgName(String assignedOrgName) {
        this.assignedOrgName = assignedOrgName;
        return this;
    }

    public void setAssignedOrgName(String assignedOrgName) {
        this.assignedOrgName = assignedOrgName;
    }

    public String getAssignedTenantName() {
        return assignedTenantName;
    }

    public VntuDownlinkPort assignedTenantName(String assignedTenantName) {
        this.assignedTenantName = assignedTenantName;
        return this;
    }

    public void setAssignedTenantName(String assignedTenantName) {
        this.assignedTenantName = assignedTenantName;
    }

    public String getAssignedOrgId() {
        return assignedOrgId;
    }

    public VntuDownlinkPort assignedOrgId(String assignedOrgId) {
        this.assignedOrgId = assignedOrgId;
        return this;
    }

    public void setAssignedOrgId(String assignedOrgId) {
        this.assignedOrgId = assignedOrgId;
    }

    public String getAssignedTenantId() {
        return assignedTenantId;
    }

    public VntuDownlinkPort assignedTenantId(String assignedTenantId) {
        this.assignedTenantId = assignedTenantId;
        return this;
    }

    public void setAssignedTenantId(String assignedTenantId) {
        this.assignedTenantId = assignedTenantId;
    }

    public Long getAssignedVntuId() {
        return assignedVntuId;
    }

    public VntuDownlinkPort assignedVntuId(Long assignedVntuId) {
        this.assignedVntuId = assignedVntuId;
        return this;
    }

    public void setAssignedVntuId(Long assignedVntuId) {
        this.assignedVntuId = assignedVntuId;
    }

    public String getAssignedVntuName() {
        return assignedVntuName;
    }

    public VntuDownlinkPort assignedVntuName(String assignedVntuName) {
        this.assignedVntuName = assignedVntuName;
        return this;
    }

    public void setAssignedVntuName(String assignedVntuName) {
        this.assignedVntuName = assignedVntuName;
    }

    public CentralSwitch getCentralSwitch() {
        return centralSwitch;
    }

    public VntuDownlinkPort centralSwitch(CentralSwitch centralSwitch) {
        this.centralSwitch = centralSwitch;
        return this;
    }

    public void setCentralSwitch(CentralSwitch centralSwitch) {
        this.centralSwitch = centralSwitch;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VntuDownlinkPort)) {
            return false;
        }
        return id != null && id.equals(((VntuDownlinkPort) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "VntuDownlinkPort{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", uid='" + getUid() + "'" +
            ", description='" + getDescription() + "'" +
            ", assignedOrgName='" + getAssignedOrgName() + "'" +
            ", assignedTenantName='" + getAssignedTenantName() + "'" +
            ", assignedOrgId='" + getAssignedOrgId() + "'" +
            ", assignedTenantId='" + getAssignedTenantId() + "'" +
            ", assignedVntuId=" + getAssignedVntuId() +
            ", assignedVntuName='" + getAssignedVntuName() + "'" +
            "}";
    }
}
