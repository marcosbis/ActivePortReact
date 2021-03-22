package com.activeport.web.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.activeport.web.domain.XeroAccount} entity.
 */
public class XeroAccountDTO implements Serializable {
    private Long id;

    private String contactId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof XeroAccountDTO)) {
            return false;
        }

        return id != null && id.equals(((XeroAccountDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "XeroAccountDTO{" +
            "id=" + getId() +
            ", contactId='" + getContactId() + "'" +
            "}";
    }
}
