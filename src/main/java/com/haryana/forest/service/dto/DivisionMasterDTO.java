package com.haryana.forest.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the DivisionMaster entity.
 */
public class DivisionMasterDTO implements Serializable {

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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
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

    public String getStateId() {
        return stateId;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
    }

    public Boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DivisionMasterDTO divisionMasterDTO = (DivisionMasterDTO) o;
        if (divisionMasterDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), divisionMasterDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DivisionMasterDTO{" +
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
