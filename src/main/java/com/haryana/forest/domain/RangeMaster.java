package com.haryana.forest.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.Table;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A RangeMaster.
 */
@Table("rangeMaster")
public class RangeMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private UUID id;

    @NotNull
    private String rangeName;

    @NotNull
    private String divisionNmae;

    @NotNull
    private String divisionId;

    @NotNull
    private String circleName;

    @NotNull
    private String circleId;

    @NotNull
    private String stateName;

    @NotNull
    private Boolean isActive;

    @NotNull
    private String stateId;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getRangeName() {
        return rangeName;
    }

    public RangeMaster rangeName(String rangeName) {
        this.rangeName = rangeName;
        return this;
    }

    public void setRangeName(String rangeName) {
        this.rangeName = rangeName;
    }

    public String getDivisionNmae() {
        return divisionNmae;
    }

    public RangeMaster divisionNmae(String divisionNmae) {
        this.divisionNmae = divisionNmae;
        return this;
    }

    public void setDivisionNmae(String divisionNmae) {
        this.divisionNmae = divisionNmae;
    }

    public String getDivisionId() {
        return divisionId;
    }

    public RangeMaster divisionId(String divisionId) {
        this.divisionId = divisionId;
        return this;
    }

    public void setDivisionId(String divisionId) {
        this.divisionId = divisionId;
    }

    public String getCircleName() {
        return circleName;
    }

    public RangeMaster circleName(String circleName) {
        this.circleName = circleName;
        return this;
    }

    public void setCircleName(String circleName) {
        this.circleName = circleName;
    }

    public String getCircleId() {
        return circleId;
    }

    public RangeMaster circleId(String circleId) {
        this.circleId = circleId;
        return this;
    }

    public void setCircleId(String circleId) {
        this.circleId = circleId;
    }

    public String getStateName() {
        return stateName;
    }

    public RangeMaster stateName(String stateName) {
        this.stateName = stateName;
        return this;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public Boolean isIsActive() {
        return isActive;
    }

    public RangeMaster isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getStateId() {
        return stateId;
    }

    public RangeMaster stateId(String stateId) {
        this.stateId = stateId;
        return this;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
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
        RangeMaster rangeMaster = (RangeMaster) o;
        if (rangeMaster.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), rangeMaster.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RangeMaster{" +
            "id=" + getId() +
            ", rangeName='" + getRangeName() + "'" +
            ", divisionNmae='" + getDivisionNmae() + "'" +
            ", divisionId='" + getDivisionId() + "'" +
            ", circleName='" + getCircleName() + "'" +
            ", circleId='" + getCircleId() + "'" +
            ", stateName='" + getStateName() + "'" +
            ", isActive='" + isIsActive() + "'" +
            ", stateId='" + getStateId() + "'" +
            "}";
    }
}
