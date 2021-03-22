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
 * A DTO for the {@link com.activeport.web.domain.MarketPlace} entity.
 */
public class MarketPlaceDTO implements Serializable {
    private Long id;

    private String companyUid;

    private String companyName;

    private String partnerUid;

    private String accountId;

    private String title;

    private Integer locationId;

    private Integer speed;

    private Integer rank;

    private Integer bandwidth;

    private String locationName;

    private String locationMetro;

    private Integer portId;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyUid() {
        return companyUid;
    }

    public void setCompanyUid(String companyUid) {
        this.companyUid = companyUid;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPartnerUid() {
        return partnerUid;
    }

    public void setPartnerUid(String partnerUid) {
        this.partnerUid = partnerUid;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getBandwidth() {
        return bandwidth;
    }

    public void setBandwidth(Integer bandwidth) {
        this.bandwidth = bandwidth;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationMetro() {
        return locationMetro;
    }

    public void setLocationMetro(String locationMetro) {
        this.locationMetro = locationMetro;
    }

    public Integer getPortId() {
        return portId;
    }

    public void setPortId(Integer portId) {
        this.portId = portId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MarketPlaceDTO)) {
            return false;
        }

        return id != null && id.equals(((MarketPlaceDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MarketPlaceDTO{" +
            "id=" + getId() +
            ", companyUid='" + getCompanyUid() + "'" +
            ", companyName='" + getCompanyName() + "'" +
            ", partnerUid='" + getPartnerUid() + "'" +
            ", accountId='" + getAccountId() + "'" +
            ", title='" + getTitle() + "'" +
            ", locationId=" + getLocationId() +
            ", speed=" + getSpeed() +
            ", rank=" + getRank() +
            ", bandwidth=" + getBandwidth() +
            ", locationName='" + getLocationName() + "'" +
            ", locationMetro='" + getLocationMetro() + "'" +
            ", portId=" + getPortId() +
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
            "}";
    }
}
