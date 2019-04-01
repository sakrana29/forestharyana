package com.haryana.forest.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.Table;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A InchargeMasterType.
 */
@Table("inchargeMasterType")
public class InchargeMasterType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private UUID id;

    @NotNull
    private String inchargeMasterType;

    @NotNull
    private Boolean isActive;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getInchargeMasterType() {
        return inchargeMasterType;
    }

    public InchargeMasterType inchargeMasterType(String inchargeMasterType) {
        this.inchargeMasterType = inchargeMasterType;
        return this;
    }

    public void setInchargeMasterType(String inchargeMasterType) {
        this.inchargeMasterType = inchargeMasterType;
    }

    public Boolean isIsActive() {
        return isActive;
    }

    public InchargeMasterType isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        InchargeMasterType inchargeMasterType = (InchargeMasterType) o;
        if (inchargeMasterType.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), inchargeMasterType.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "InchargeMasterType{" +
            "id=" + getId() +
            ", inchargeMasterType='" + getInchargeMasterType() + "'" +
            ", isActive='" + isIsActive() + "'" +
            "}";
    }
}
