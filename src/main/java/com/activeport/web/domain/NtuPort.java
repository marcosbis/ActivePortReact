package com.activeport.web.domain;

import com.activeport.web.domain.enumeration.PortTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A NtuPort.
 */
@Entity
@Table(name = "ntu_port")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class NtuPort implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "label")
    private String label;

    @Column(name = "description")
    private String description;

    @Column(name = "mac")
    private String mac;

    @Column(name = "port")
    private Integer port;

    @Enumerated(EnumType.STRING)
    @Column(name = "port_type")
    private PortTypeEnum portType;

    @Column(name = "trunk")
    private Boolean trunk;

    @Column(name = "jumbo")
    private Boolean jumbo;

    @Column(name = "port_speed")
    private String portSpeed;

    @Column(name = "internet_port")
    private Boolean internetPort;

    @Column(name = "uplink_port")
    private String uplinkPort;

    @OneToMany(mappedBy = "ntuPort")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<UserService> userServices = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "ports", allowSetters = true)
    private Ntu ntu;

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

    public NtuPort name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public NtuPort label(String label) {
        this.label = label;
        return this;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public NtuPort description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMac() {
        return mac;
    }

    public NtuPort mac(String mac) {
        this.mac = mac;
        return this;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public Integer getPort() {
        return port;
    }

    public NtuPort port(Integer port) {
        this.port = port;
        return this;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public PortTypeEnum getPortType() {
        return portType;
    }

    public NtuPort portType(PortTypeEnum portType) {
        this.portType = portType;
        return this;
    }

    public void setPortType(PortTypeEnum portType) {
        this.portType = portType;
    }

    public Boolean isTrunk() {
        return trunk;
    }

    public NtuPort trunk(Boolean trunk) {
        this.trunk = trunk;
        return this;
    }

    public void setTrunk(Boolean trunk) {
        this.trunk = trunk;
    }

    public Boolean isJumbo() {
        return jumbo;
    }

    public NtuPort jumbo(Boolean jumbo) {
        this.jumbo = jumbo;
        return this;
    }

    public void setJumbo(Boolean jumbo) {
        this.jumbo = jumbo;
    }

    public String getPortSpeed() {
        return portSpeed;
    }

    public NtuPort portSpeed(String portSpeed) {
        this.portSpeed = portSpeed;
        return this;
    }

    public void setPortSpeed(String portSpeed) {
        this.portSpeed = portSpeed;
    }

    public Boolean isInternetPort() {
        return internetPort;
    }

    public NtuPort internetPort(Boolean internetPort) {
        this.internetPort = internetPort;
        return this;
    }

    public void setInternetPort(Boolean internetPort) {
        this.internetPort = internetPort;
    }

    public String getUplinkPort() {
        return uplinkPort;
    }

    public NtuPort uplinkPort(String uplinkPort) {
        this.uplinkPort = uplinkPort;
        return this;
    }

    public void setUplinkPort(String uplinkPort) {
        this.uplinkPort = uplinkPort;
    }

    public Set<UserService> getUserServices() {
        return userServices;
    }

    public NtuPort userServices(Set<UserService> userServices) {
        this.userServices = userServices;
        return this;
    }

    public NtuPort addUserServices(UserService userService) {
        this.userServices.add(userService);
        userService.setNtuPort(this);
        return this;
    }

    public NtuPort removeUserServices(UserService userService) {
        this.userServices.remove(userService);
        userService.setNtuPort(null);
        return this;
    }

    public void setUserServices(Set<UserService> userServices) {
        this.userServices = userServices;
    }

    public Ntu getNtu() {
        return ntu;
    }

    public NtuPort ntu(Ntu ntu) {
        this.ntu = ntu;
        return this;
    }

    public void setNtu(Ntu ntu) {
        this.ntu = ntu;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NtuPort)) {
            return false;
        }
        return id != null && id.equals(((NtuPort) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NtuPort{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", label='" + getLabel() + "'" +
            ", description='" + getDescription() + "'" +
            ", mac='" + getMac() + "'" +
            ", port=" + getPort() +
            ", portType='" + getPortType() + "'" +
            ", trunk='" + isTrunk() + "'" +
            ", jumbo='" + isJumbo() + "'" +
            ", portSpeed='" + getPortSpeed() + "'" +
            ", internetPort='" + isInternetPort() + "'" +
            ", uplinkPort='" + getUplinkPort() + "'" +
            "}";
    }
}
