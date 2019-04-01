package com.haryana.forest.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the StateMaster entity.
 */
public class StateMasterDTO implements Serializable {

    private UUID id;

    @NotNull
    private String stateName;

    @NotNull
    private Boolean isActive;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        StateMasterDTO stateMasterDTO = (StateMasterDTO) o;
        if (stateMasterDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), stateMasterDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "StateMasterDTO{" +
            "id=" + getId() +
            ", stateName='" + getStateName() + "'" +
            ", isActive='" + isIsActive() + "'" +
            "}";
    }
}
