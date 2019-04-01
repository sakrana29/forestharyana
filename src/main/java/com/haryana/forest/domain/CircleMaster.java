package com.haryana.forest.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.Table;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A CircleMaster.
 */
@Table("circleMaster")
public class CircleMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private UUID id;

    @NotNull
    private String circleName;

    @NotNull
    private String stateName;

    @NotNull
    private String stateId;

    @NotNull
    private Boolean isActive;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCircleName() {
        return circleName;
    }

    public CircleMaster circleName(String circleName) {
        this.circleName = circleName;
        return this;
    }

    public void setCircleName(String circleName) {
        this.circleName = circleName;
    }

    public String getStateName() {
        return stateName;
    }

    public CircleMaster stateName(String stateName) {
        this.stateName = stateName;
        return this;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getStateId() {
        return stateId;
    }

    public CircleMaster stateId(String stateId) {
        this.stateId = stateId;
        return this;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
    }

    public Boolean isIsActive() {
        return isActive;
    }

    public CircleMaster isActive(Boolean isActive) {
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
        CircleMaster circleMaster = (CircleMaster) o;
        if (circleMaster.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), circleMaster.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CircleMaster{" +
            "id=" + getId() +
            ", circleName='" + getCircleName() + "'" +
            ", stateName='" + getStateName() + "'" +
            ", stateId='" + getStateId() + "'" +
            ", isActive='" + isIsActive() + "'" +
            "}";
    }
}
