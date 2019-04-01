package com.haryana.forest.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the CircleMaster entity.
 */
public class CircleMasterDTO implements Serializable {

    private UUID id;

    @NotNull
    private String circleName;

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

    public String getCircleName() {
        return circleName;
    }

    public void setCircleName(String circleName) {
        this.circleName = circleName;
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

        CircleMasterDTO circleMasterDTO = (CircleMasterDTO) o;
        if (circleMasterDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), circleMasterDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CircleMasterDTO{" +
            "id=" + getId() +
            ", circleName='" + getCircleName() + "'" +
            ", stateName='" + getStateName() + "'" +
            ", stateId='" + getStateId() + "'" +
            ", isActive='" + isIsActive() + "'" +
            "}";
    }
}
