package com.activeport.web.service.dto;

import com.activeport.web.domain.enumeration.CircuitTypeEnum;
import com.activeport.web.domain.enumeration.ConnectTypeEnum;
import com.activeport.web.domain.enumeration.FirewallStatusEnum;
import com.activeport.web.domain.enumeration.PartnerTypeEnum;
import com.activeport.web.domain.enumeration.ProvisioningStatusEnum;
import com.activeport.web.domain.enumeration.ServiceStateEnum;
import com.activeport.web.domain.enumeration.VXCTypeEnum;
import java.io.Serializable;

/**
 * A DTO for the {@link com.activeport.web.domain.UserService} entity.
 */
public class UserServiceDTO implements Serializable {
    private Long id;

    private ConnectTypeEnum type;

    private String name;

    private String description;

    private String serviceKey;

    private Integer rateLimit;

    private String price;

    private String uuid;

    private String productUid;

    private Integer reTaggedVlanId;

    private ProvisioningStatusEnum provisioningStatus;

    private Integer vlanIdA;

    private Integer vlanIdB;

    private Integer vlanIdS;

    private Long ntuId;

    private String userIp;

    private String firewallPrice;

    private FirewallStatusEnum firewallStatus;

    private ServiceStateEnum state;

    private String bEndProductUid;

    private PartnerTypeEnum partnerType;

    private CircuitTypeEnum circuitType;

    private String userSubnet;

    private String myGw;

    private String activePortGw;

    private String awsAuthKey;

    private String awsIp;

    private Integer asn;

    private Integer peerAsn;

    private VXCTypeEnum vxcType;

    private Long ntuPortId;

    private String ntuPortName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ConnectTypeEnum getType() {
        return type;
    }

    public void setType(ConnectTypeEnum type) {
        this.type = type;
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

    public String getServiceKey() {
        return serviceKey;
    }

    public void setServiceKey(String serviceKey) {
        this.serviceKey = serviceKey;
    }

    public Integer getRateLimit() {
        return rateLimit;
    }

    public void setRateLimit(Integer rateLimit) {
        this.rateLimit = rateLimit;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getProductUid() {
        return productUid;
    }

    public void setProductUid(String productUid) {
        this.productUid = productUid;
    }

    public Integer getReTaggedVlanId() {
        return reTaggedVlanId;
    }

    public void setReTaggedVlanId(Integer reTaggedVlanId) {
        this.reTaggedVlanId = reTaggedVlanId;
    }

    public ProvisioningStatusEnum getProvisioningStatus() {
        return provisioningStatus;
    }

    public void setProvisioningStatus(ProvisioningStatusEnum provisioningStatus) {
        this.provisioningStatus = provisioningStatus;
    }

    public Integer getVlanIdA() {
        return vlanIdA;
    }

    public void setVlanIdA(Integer vlanIdA) {
        this.vlanIdA = vlanIdA;
    }

    public Integer getVlanIdB() {
        return vlanIdB;
    }

    public void setVlanIdB(Integer vlanIdB) {
        this.vlanIdB = vlanIdB;
    }

    public Integer getVlanIdS() {
        return vlanIdS;
    }

    public void setVlanIdS(Integer vlanIdS) {
        this.vlanIdS = vlanIdS;
    }

    public Long getNtuId() {
        return ntuId;
    }

    public void setNtuId(Long ntuId) {
        this.ntuId = ntuId;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    public String getFirewallPrice() {
        return firewallPrice;
    }

    public void setFirewallPrice(String firewallPrice) {
        this.firewallPrice = firewallPrice;
    }

    public FirewallStatusEnum getFirewallStatus() {
        return firewallStatus;
    }

    public void setFirewallStatus(FirewallStatusEnum firewallStatus) {
        this.firewallStatus = firewallStatus;
    }

    public ServiceStateEnum getState() {
        return state;
    }

    public void setState(ServiceStateEnum state) {
        this.state = state;
    }

    public String getbEndProductUid() {
        return bEndProductUid;
    }

    public void setbEndProductUid(String bEndProductUid) {
        this.bEndProductUid = bEndProductUid;
    }

    public PartnerTypeEnum getPartnerType() {
        return partnerType;
    }

    public void setPartnerType(PartnerTypeEnum partnerType) {
        this.partnerType = partnerType;
    }

    public CircuitTypeEnum getCircuitType() {
        return circuitType;
    }

    public void setCircuitType(CircuitTypeEnum circuitType) {
        this.circuitType = circuitType;
    }

    public String getUserSubnet() {
        return userSubnet;
    }

    public void setUserSubnet(String userSubnet) {
        this.userSubnet = userSubnet;
    }

    public String getMyGw() {
        return myGw;
    }

    public void setMyGw(String myGw) {
        this.myGw = myGw;
    }

    public String getActivePortGw() {
        return activePortGw;
    }

    public void setActivePortGw(String activePortGw) {
        this.activePortGw = activePortGw;
    }

    public String getAwsAuthKey() {
        return awsAuthKey;
    }

    public void setAwsAuthKey(String awsAuthKey) {
        this.awsAuthKey = awsAuthKey;
    }

    public String getAwsIp() {
        return awsIp;
    }

    public void setAwsIp(String awsIp) {
        this.awsIp = awsIp;
    }

    public Integer getAsn() {
        return asn;
    }

    public void setAsn(Integer asn) {
        this.asn = asn;
    }

    public Integer getPeerAsn() {
        return peerAsn;
    }

    public void setPeerAsn(Integer peerAsn) {
        this.peerAsn = peerAsn;
    }

    public VXCTypeEnum getVxcType() {
        return vxcType;
    }

    public void setVxcType(VXCTypeEnum vxcType) {
        this.vxcType = vxcType;
    }

    public Long getNtuPortId() {
        return ntuPortId;
    }

    public void setNtuPortId(Long ntuPortId) {
        this.ntuPortId = ntuPortId;
    }

    public String getNtuPortName() {
        return ntuPortName;
    }

    public void setNtuPortName(String ntuPortName) {
        this.ntuPortName = ntuPortName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserServiceDTO)) {
            return false;
        }

        return id != null && id.equals(((UserServiceDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UserServiceDTO{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", serviceKey='" + getServiceKey() + "'" +
            ", rateLimit=" + getRateLimit() +
            ", price='" + getPrice() + "'" +
            ", uuid='" + getUuid() + "'" +
            ", productUid='" + getProductUid() + "'" +
            ", reTaggedVlanId=" + getReTaggedVlanId() +
            ", provisioningStatus='" + getProvisioningStatus() + "'" +
            ", vlanIdA=" + getVlanIdA() +
            ", vlanIdB=" + getVlanIdB() +
            ", vlanIdS=" + getVlanIdS() +
            ", ntuId=" + getNtuId() +
            ", userIp='" + getUserIp() + "'" +
            ", firewallPrice='" + getFirewallPrice() + "'" +
            ", firewallStatus='" + getFirewallStatus() + "'" +
            ", state='" + getState() + "'" +
            ", bEndProductUid='" + getbEndProductUid() + "'" +
            ", partnerType='" + getPartnerType() + "'" +
            ", circuitType='" + getCircuitType() + "'" +
            ", userSubnet='" + getUserSubnet() + "'" +
            ", myGw='" + getMyGw() + "'" +
            ", activePortGw='" + getActivePortGw() + "'" +
            ", awsAuthKey='" + getAwsAuthKey() + "'" +
            ", awsIp='" + getAwsIp() + "'" +
            ", asn=" + getAsn() +
            ", peerAsn=" + getPeerAsn() +
            ", vxcType='" + getVxcType() + "'" +
            ", ntuPortId=" + getNtuPortId() +
            ", ntuPortName='" + getNtuPortName() + "'" +
            "}";
    }
}
