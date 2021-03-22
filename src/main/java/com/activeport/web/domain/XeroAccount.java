package com.activeport.web.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A XeroAccount.
 */
@Entity
@Table(name = "xero_account")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class XeroAccount implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "contact_id")
    private String contactId;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContactId() {
        return contactId;
    }

    public XeroAccount contactId(String contactId) {
        this.contactId = contactId;
        return this;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof XeroAccount)) {
            return false;
        }
        return id != null && id.equals(((XeroAccount) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "XeroAccount{" +
            "id=" + getId() +
            ", contactId='" + getContactId() + "'" +
            "}";
    }
}
