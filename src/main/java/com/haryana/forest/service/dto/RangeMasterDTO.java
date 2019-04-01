package com.haryana.forest.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the RangeMaster entity.
 */
public class RangeMasterDTO implements Serializable {

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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getRangeName() {
        return rangeName;
    }

    public void setRangeName(String rangeName) {
        this.rangeName = rangeName;
    }

    public String getDivisionNmae() {
        return divisionNmae;
    }

    public void setDivisionNmae(String divisionNmae) {
        this.divisionNmae = divisionNmae;
    }

    public String getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(String divisionId) {
        this.divisionId = divisionId;
    }

    public String getCircleName() {
        return circleName;
    }

    public void setCircleName(String circleName) {
        this.circleName = circleName;
    }

    public String getCircleId() {
        return circleId;
    }

    public void setCircleId(String circleId) {
        this.circleId = circleId;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public Boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getStateId() {
        return stateId;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RangeMasterDTO rangeMasterDTO = (RangeMasterDTO) o;
        if (rangeMasterDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), rangeMasterDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RangeMasterDTO{" +
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
