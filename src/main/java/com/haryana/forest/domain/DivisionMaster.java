package com.haryana.forest.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.Table;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DivisionMaster.
 */
@Table("divisionMaster")
public class DivisionMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private UUID id;

    @NotNull
    private String divisionName;

    @NotNull
    private String circleName;

    @NotNull
    private String circleId;

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

    public String getDivisionName() {
        return divisionName;
    }

    public DivisionMaster divisionName(String divisionName) {
        this.divisionName = divisionName;
        return this;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    public String getCircleName() {
        return circleName;
    }

    public DivisionMaster circleName(String circleName) {
        this.circleName = circleName;
        return this;
    }

    public void setCircleName(String circleName) {
        this.circleName = circleName;
    }

    public String getCircleId() {
        return circleId;
    }

    public DivisionMaster circleId(String circleId) {
        this.circleId = circleId;
        return this;
    }

    public void setCircleId(String circleId) {
        this.circleId = circleId;
    }

    public String getStateName() {
        return stateName;
    }

    public DivisionMaster stateName(String stateName) {
        this.stateName = stateName;
        return this;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getStateId() {
        return stateId;
    }

    public DivisionMaster stateId(String stateId) {
        this.stateId = stateId;
        return this;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
    }

    public Boolean isIsActive() {
        return isActive;
    }

    public DivisionMaster isActive(Boolean isActive) {
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
        DivisionMaster divisionMaster = (DivisionMaster) o;
        if (divisionMaster.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), divisionMaster.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DivisionMaster{" +
            "id=" + getId() +
            ", divisionName='" + getDivisionName() + "'" +
            ", circleName='" + getCircleName() + "'" +
            ", circleId='" + getCircleId() + "'" +
            ", stateName='" + getStateName() + "'" +
            ", stateId='" + getStateId() + "'" +
            ", isActive='" + isIsActive() + "'" +
            "}";
    }
}
