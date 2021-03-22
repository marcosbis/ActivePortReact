package com.activeport.web.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A RealmIp.
 */
@Entity
@Table(name = "realm_ip")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RealmIp implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "subnet")
    private String subnet;

    @Column(name = "name")
    private String name;

    @Column(name = "desciption")
    private String desciption;

    @Column(name = "mask")
    private String mask;

    @Column(name = "subnet_size")
    private String subnetSize;

    @Column(name = "first_ip")
    private String firstIp;

    @Column(name = "last_ip")
    private String lastIp;

    @Column(name = "broadcast")
    private String broadcast;

    @Column(name = "cir")
    private String cir;

    @Column(name = "ipsec_gateway")
    private String ipsecGateway;

    @ManyToOne
    @JsonIgnoreProperties(value = "realmIps", allowSetters = true)
    private Location location;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubnet() {
        return subnet;
    }

    public RealmIp subnet(String subnet) {
        this.subnet = subnet;
        return this;
    }

    public void setSubnet(String subnet) {
        this.subnet = subnet;
    }

    public String getName() {
        return name;
    }

    public RealmIp name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesciption() {
        return desciption;
    }

    public RealmIp desciption(String desciption) {
        this.desciption = desciption;
        return this;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    public String getMask() {
        return mask;
    }

    public RealmIp mask(String mask) {
        this.mask = mask;
        return this;
    }

    public void setMask(String mask) {
        this.mask = mask;
    }

    public String getSubnetSize() {
        return subnetSize;
    }

    public RealmIp subnetSize(String subnetSize) {
        this.subnetSize = subnetSize;
        return this;
    }

    public void setSubnetSize(String subnetSize) {
        this.subnetSize = subnetSize;
    }

    public String getFirstIp() {
        return firstIp;
    }

    public RealmIp firstIp(String firstIp) {
        this.firstIp = firstIp;
        return this;
    }

    public void setFirstIp(String firstIp) {
        this.firstIp = firstIp;
    }

    public String getLastIp() {
        return lastIp;
    }

    public RealmIp lastIp(String lastIp) {
        this.lastIp = lastIp;
        return this;
    }

    public void setLastIp(String lastIp) {
        this.lastIp = lastIp;
    }

    public String getBroadcast() {
        return broadcast;
    }

    public RealmIp broadcast(String broadcast) {
        this.broadcast = broadcast;
        return this;
    }

    public void setBroadcast(String broadcast) {
        this.broadcast = broadcast;
    }

    public String getCir() {
        return cir;
    }

    public RealmIp cir(String cir) {
        this.cir = cir;
        return this;
    }

    public void setCir(String cir) {
        this.cir = cir;
    }

    public String getIpsecGateway() {
        return ipsecGateway;
    }

    public RealmIp ipsecGateway(String ipsecGateway) {
        this.ipsecGateway = ipsecGateway;
        return this;
    }

    public void setIpsecGateway(String ipsecGateway) {
        this.ipsecGateway = ipsecGateway;
    }

    public Location getLocation() {
        return location;
    }

    public RealmIp location(Location location) {
        this.location = location;
        return this;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RealmIp)) {
            return false;
        }
        return id != null && id.equals(((RealmIp) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RealmIp{" +
            "id=" + getId() +
            ", subnet='" + getSubnet() + "'" +
            ", name='" + getName() + "'" +
            ", desciption='" + getDesciption() + "'" +
            ", mask='" + getMask() + "'" +
            ", subnetSize='" + getSubnetSize() + "'" +
            ", firstIp='" + getFirstIp() + "'" +
            ", lastIp='" + getLastIp() + "'" +
            ", broadcast='" + getBroadcast() + "'" +
            ", cir='" + getCir() + "'" +
            ", ipsecGateway='" + getIpsecGateway() + "'" +
            "}";
    }
}
