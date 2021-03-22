package com.activeport.web.domain;

import com.activeport.web.domain.enumeration.CircuitTypeEnum;
import com.activeport.web.domain.enumeration.ConnectTypeEnum;
import com.activeport.web.domain.enumeration.FirewallStatusEnum;
import com.activeport.web.domain.enumeration.PartnerTypeEnum;
import com.activeport.web.domain.enumeration.ProvisioningStatusEnum;
import com.activeport.web.domain.enumeration.ServiceStateEnum;
import com.activeport.web.domain.enumeration.VXCTypeEnum;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A MarketPlace.
 */
@Entity
@Table(name = "market_place")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MarketPlace implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company_uid")
    private String companyUid;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "partner_uid")
    private String partnerUid;

    @Column(name = "account_id")
    private String accountId;

    @Column(name = "title")
    private String title;

    @Column(name = "location_id")
    private Integer locationId;

    @Column(name = "speed")
    private Integer speed;

    @Column(name = "jhi_rank")
    private Integer rank;

    @Column(name = "bandwidth")
    private Integer bandwidth;

    @Column(name = "location_name")
    private String locationName;

    @Column(name = "location_metro")
    private String locationMetro;

    @Column(name = "port_id")
    private Integer portId;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private ConnectTypeEnum type;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "service_key")
    private String serviceKey;

    @Column(name = "rate_limit")
    private Integer rateLimit;

    @Column(name = "price")
    private String price;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "product_uid")
    private String productUid;

    @Column(name = "re_tagged_vlan_id")
    private Integer reTaggedVlanId;

    @Enumerated(EnumType.STRING)
    @Column(name = "provisioning_status")
    private ProvisioningStatusEnum provisioningStatus;

    @Column(name = "vlan_id_a")
    private Integer vlanIdA;

    @Column(name = "vlan_id_b")
    private Integer vlanIdB;

    @Column(name = "vlan_id_s")
    private Integer vlanIdS;

    @Column(name = "ntu_id")
    private Long ntuId;

    @Column(name = "user_ip")
    private String userIp;

    @Column(name = "firewall_price")
    private String firewallPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "firewall_status")
    private FirewallStatusEnum firewallStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private ServiceStateEnum state;

    @Column(name = "b_end_product_uid")
    private String bEndProductUid;

    @Enumerated(EnumType.STRING)
    @Column(name = "partner_type")
    private PartnerTypeEnum partnerType;

    @Enumerated(EnumType.STRING)
    @Column(name = "circuit_type")
    private CircuitTypeEnum circuitType;

    @Column(name = "user_subnet")
    private String userSubnet;

    @Column(name = "my_gw")
    private String myGw;

    @Column(name = "active_port_gw")
    private String activePortGw;

    @Column(name = "aws_auth_key")
    private String awsAuthKey;

    @Column(name = "aws_ip")
    private String awsIp;

    @Column(name = "asn")
    private Integer asn;

    @Column(name = "peer_asn")
    private Integer peerAsn;

    @Enumerated(EnumType.STRING)
    @Column(name = "vxc_type")
    private VXCTypeEnum vxcType;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyUid() {
        return companyUid;
    }

    public MarketPlace companyUid(String companyUid) {
        this.companyUid = companyUid;
        return this;
    }

    public void setCompanyUid(String companyUid) {
        this.companyUid = companyUid;
    }

    public String getCompanyName() {
        return companyName;
    }

    public MarketPlace companyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPartnerUid() {
        return partnerUid;
    }

    public MarketPlace partnerUid(String partnerUid) {
        this.partnerUid = partnerUid;
        return this;
    }

    public void setPartnerUid(String partnerUid) {
        this.partnerUid = partnerUid;
    }

    public String getAccountId() {
        return accountId;
    }

    public MarketPlace accountId(String accountId) {
        this.accountId = accountId;
        return this;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getTitle() {
        return title;
    }

    public MarketPlace title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public MarketPlace locationId(Integer locationId) {
        this.locationId = locationId;
        return this;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public Integer getSpeed() {
        return speed;
    }

    public MarketPlace speed(Integer speed) {
        this.speed = speed;
        return this;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Integer getRank() {
        return rank;
    }

    public MarketPlace rank(Integer rank) {
        this.rank = rank;
        return this;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getBandwidth() {
        return bandwidth;
    }

    public MarketPlace bandwidth(Integer bandwidth) {
        this.bandwidth = bandwidth;
        return this;
    }

    public void setBandwidth(Integer bandwidth) {
        this.bandwidth = bandwidth;
    }

    public String getLocationName() {
        return locationName;
    }

    public MarketPlace locationName(String locationName) {
        this.locationName = locationName;
        return this;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationMetro() {
        return locationMetro;
    }

    public MarketPlace locationMetro(String locationMetro) {
        this.locationMetro = locationMetro;
        return this;
    }

    public void setLocationMetro(String locationMetro) {
        this.locationMetro = locationMetro;
    }

    public Integer getPortId() {
        return portId;
    }

    public MarketPlace portId(Integer portId) {
        this.portId = portId;
        return this;
    }

    public void setPortId(Integer portId) {
        this.portId = portId;
    }

    public ConnectTypeEnum getType() {
        return type;
    }

    public MarketPlace type(ConnectTypeEnum type) {
        this.type = type;
        return this;
    }

    public void setType(ConnectTypeEnum type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public MarketPlace name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public MarketPlace description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getServiceKey() {
        return serviceKey;
    }

    public MarketPlace serviceKey(String serviceKey) {
        this.serviceKey = serviceKey;
        return this;
    }

    public void setServiceKey(String serviceKey) {
        this.serviceKey = serviceKey;
    }

    public Integer getRateLimit() {
        return rateLimit;
    }

    public MarketPlace rateLimit(Integer rateLimit) {
        this.rateLimit = rateLimit;
        return this;
    }

    public void setRateLimit(Integer rateLimit) {
        this.rateLimit = rateLimit;
    }

    public String getPrice() {
        return price;
    }

    public MarketPlace price(String price) {
        this.price = price;
        return this;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUuid() {
        return uuid;
    }

    public MarketPlace uuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getProductUid() {
        return productUid;
    }

    public MarketPlace productUid(String productUid) {
        this.productUid = productUid;
        return this;
    }

    public void setProductUid(String productUid) {
        this.productUid = productUid;
    }

    public Integer getReTaggedVlanId() {
        return reTaggedVlanId;
    }

    public MarketPlace reTaggedVlanId(Integer reTaggedVlanId) {
        this.reTaggedVlanId = reTaggedVlanId;
        return this;
    }

    public void setReTaggedVlanId(Integer reTaggedVlanId) {
        this.reTaggedVlanId = reTaggedVlanId;
    }

    public ProvisioningStatusEnum getProvisioningStatus() {
        return provisioningStatus;
    }

    public MarketPlace provisioningStatus(ProvisioningStatusEnum provisioningStatus) {
        this.provisioningStatus = provisioningStatus;
        return this;
    }

    public void setProvisioningStatus(ProvisioningStatusEnum provisioningStatus) {
        this.provisioningStatus = provisioningStatus;
    }

    public Integer getVlanIdA() {
        return vlanIdA;
    }

    public MarketPlace vlanIdA(Integer vlanIdA) {
        this.vlanIdA = vlanIdA;
        return this;
    }

    public void setVlanIdA(Integer vlanIdA) {
        this.vlanIdA = vlanIdA;
    }

    public Integer getVlanIdB() {
        return vlanIdB;
    }

    public MarketPlace vlanIdB(Integer vlanIdB) {
        this.vlanIdB = vlanIdB;
        return this;
    }

    public void setVlanIdB(Integer vlanIdB) {
        this.vlanIdB = vlanIdB;
    }

    public Integer getVlanIdS() {
        return vlanIdS;
    }

    public MarketPlace vlanIdS(Integer vlanIdS) {
        this.vlanIdS = vlanIdS;
        return this;
    }

    public void setVlanIdS(Integer vlanIdS) {
        this.vlanIdS = vlanIdS;
    }

    public Long getNtuId() {
        return ntuId;
    }

    public MarketPlace ntuId(Long ntuId) {
        this.ntuId = ntuId;
        return this;
    }

    public void setNtuId(Long ntuId) {
        this.ntuId = ntuId;
    }

    public String getUserIp() {
        return userIp;
    }

    public MarketPlace userIp(String userIp) {
        this.userIp = userIp;
        return this;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    public String getFirewallPrice() {
        return firewallPrice;
    }

    public MarketPlace firewallPrice(String firewallPrice) {
        this.firewallPrice = firewallPrice;
        return this;
    }

    public void setFirewallPrice(String firewallPrice) {
        this.firewallPrice = firewallPrice;
    }

    public FirewallStatusEnum getFirewallStatus() {
        return firewallStatus;
    }

    public MarketPlace firewallStatus(FirewallStatusEnum firewallStatus) {
        this.firewallStatus = firewallStatus;
        return this;
    }

    public void setFirewallStatus(FirewallStatusEnum firewallStatus) {
        this.firewallStatus = firewallStatus;
    }

    public ServiceStateEnum getState() {
        return state;
    }

    public MarketPlace state(ServiceStateEnum state) {
        this.state = state;
        return this;
    }

    public void setState(ServiceStateEnum state) {
        this.state = state;
    }

    public String getbEndProductUid() {
        return bEndProductUid;
    }

    public MarketPlace bEndProductUid(String bEndProductUid) {
        this.bEndProductUid = bEndProductUid;
        return this;
    }

    public void setbEndProductUid(String bEndProductUid) {
        this.bEndProductUid = bEndProductUid;
    }

    public PartnerTypeEnum getPartnerType() {
        return partnerType;
    }

    public MarketPlace partnerType(PartnerTypeEnum partnerType) {
        this.partnerType = partnerType;
        return this;
    }

    public void setPartnerType(PartnerTypeEnum partnerType) {
        this.partnerType = partnerType;
    }

    public CircuitTypeEnum getCircuitType() {
        return circuitType;
    }

    public MarketPlace circuitType(CircuitTypeEnum circuitType) {
        this.circuitType = circuitType;
        return this;
    }

    public void setCircuitType(CircuitTypeEnum circuitType) {
        this.circuitType = circuitType;
    }

    public String getUserSubnet() {
        return userSubnet;
    }

    public MarketPlace userSubnet(String userSubnet) {
        this.userSubnet = userSubnet;
        return this;
    }

    public void setUserSubnet(String userSubnet) {
        this.userSubnet = userSubnet;
    }

    public String getMyGw() {
        return myGw;
    }

    public MarketPlace myGw(String myGw) {
        this.myGw = myGw;
        return this;
    }

    public void setMyGw(String myGw) {
        this.myGw = myGw;
    }

    public String getActivePortGw() {
        return activePortGw;
    }

    public MarketPlace activePortGw(String activePortGw) {
        this.activePortGw = activePortGw;
        return this;
    }

    public void setActivePortGw(String activePortGw) {
        this.activePortGw = activePortGw;
    }

    public String getAwsAuthKey() {
        return awsAuthKey;
    }

    public MarketPlace awsAuthKey(String awsAuthKey) {
        this.awsAuthKey = awsAuthKey;
        return this;
    }

    public void setAwsAuthKey(String awsAuthKey) {
        this.awsAuthKey = awsAuthKey;
    }

    public String getAwsIp() {
        return awsIp;
    }

    public MarketPlace awsIp(String awsIp) {
        this.awsIp = awsIp;
        return this;
    }

    public void setAwsIp(String awsIp) {
        this.awsIp = awsIp;
    }

    public Integer getAsn() {
        return asn;
    }

    public MarketPlace asn(Integer asn) {
        this.asn = asn;
        return this;
    }

    public void setAsn(Integer asn) {
        this.asn = asn;
    }

    public Integer getPeerAsn() {
        return peerAsn;
    }

    public MarketPlace peerAsn(Integer peerAsn) {
        this.peerAsn = peerAsn;
        return this;
    }

    public void setPeerAsn(Integer peerAsn) {
        this.peerAsn = peerAsn;
    }

    public VXCTypeEnum getVxcType() {
        return vxcType;
    }

    public MarketPlace vxcType(VXCTypeEnum vxcType) {
        this.vxcType = vxcType;
        return this;
    }

    public void setVxcType(VXCTypeEnum vxcType) {
        this.vxcType = vxcType;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MarketPlace)) {
            return false;
        }
        return id != null && id.equals(((MarketPlace) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MarketPlace{" +
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
