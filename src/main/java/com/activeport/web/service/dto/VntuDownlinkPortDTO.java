package com.activeport.web.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.activeport.web.domain.VntuDownlinkPort} entity.
 */
public class VntuDownlinkPortDTO implements Serializable {
    private Long id;

    private String name;

    private String uid;

    private String description;

    private String assignedOrgName;

    private String assignedTenantName;

    private String assignedOrgId;

    private String assignedTenantId;

    private Long assignedVntuId;

    private String assignedVntuName;

    private Long centralSwitchId;

    private String centralSwitchName;

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

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAssignedOrgName() {
        return assignedOrgName;
    }

    public void setAssignedOrgName(String assignedOrgName) {
        this.assignedOrgName = assignedOrgName;
    }

    public String getAssignedTenantName() {
        return assignedTenantName;
    }

    public void setAssignedTenantName(String assignedTenantName) {
        this.assignedTenantName = assignedTenantName;
    }

    public String getAssignedOrgId() {
        return assignedOrgId;
    }

    public void setAssignedOrgId(String assignedOrgId) {
        this.assignedOrgId = assignedOrgId;
    }

    public String getAssignedTenantId() {
        return assignedTenantId;
    }

    public void setAssignedTenantId(String assignedTenantId) {
        this.assignedTenantId = assignedTenantId;
    }

    public Long getAssignedVntuId() {
        return assignedVntuId;
    }

    public void setAssignedVntuId(Long assignedVntuId) {
        this.assignedVntuId = assignedVntuId;
    }

    public String getAssignedVntuName() {
        return assignedVntuName;
    }

    public void setAssignedVntuName(String assignedVntuName) {
        this.assignedVntuName = assignedVntuName;
    }

    public Long getCentralSwitchId() {
        return centralSwitchId;
    }

    public void setCentralSwitchId(Long centralSwitchId) {
        this.centralSwitchId = centralSwitchId;
    }

    public String getCentralSwitchName() {
        return centralSwitchName;
    }

    public void setCentralSwitchName(String centralSwitchName) {
        this.centralSwitchName = centralSwitchName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VntuDownlinkPortDTO)) {
            return false;
        }

        return id != null && id.equals(((VntuDownlinkPortDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "VntuDownlinkPortDTO{" +
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
            ", centralSwitchId=" + getCentralSwitchId() +
            ", centralSwitchName='" + getCentralSwitchName() + "'" +
            "}";
    }
}
