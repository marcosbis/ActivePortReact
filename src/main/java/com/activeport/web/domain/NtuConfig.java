package com.activeport.web.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A NtuConfig.
 */
@Entity
@Table(name = "ntu_config")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class NtuConfig implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "serial_number")
    private String serialNumber;

    @Column(name = "name")
    private String name;

    @Column(name = "firmware_version")
    private String firmwareVersion;

    @Column(name = "ntu_id")
    private Long ntuId;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public NtuConfig serialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
        return this;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getName() {
        return name;
    }

    public NtuConfig name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    public NtuConfig firmwareVersion(String firmwareVersion) {
        this.firmwareVersion = firmwareVersion;
        return this;
    }

    public void setFirmwareVersion(String firmwareVersion) {
        this.firmwareVersion = firmwareVersion;
    }

    public Long getNtuId() {
        return ntuId;
    }

    public NtuConfig ntuId(Long ntuId) {
        this.ntuId = ntuId;
        return this;
    }

    public void setNtuId(Long ntuId) {
        this.ntuId = ntuId;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NtuConfig)) {
            return false;
        }
        return id != null && id.equals(((NtuConfig) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NtuConfig{" +
            "id=" + getId() +
            ", serialNumber='" + getSerialNumber() + "'" +
            ", name='" + getName() + "'" +
            ", firmwareVersion='" + getFirmwareVersion() + "'" +
            ", ntuId=" + getNtuId() +
            "}";
    }
}
