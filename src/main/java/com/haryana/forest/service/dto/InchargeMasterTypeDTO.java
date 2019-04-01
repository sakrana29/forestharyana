package com.haryana.forest.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the InchargeMasterType entity.
 */
public class InchargeMasterTypeDTO implements Serializable {

    private UUID id;

    @NotNull
    private String inchargeMasterType;

    @NotNull
    private Boolean isActive;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getInchargeMasterType() {
        return inchargeMasterType;
    }

    public void setInchargeMasterType(String inchargeMasterType) {
        this.inchargeMasterType = inchargeMasterType;
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

        InchargeMasterTypeDTO inchargeMasterTypeDTO = (InchargeMasterTypeDTO) o;
        if (inchargeMasterTypeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), inchargeMasterTypeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "InchargeMasterTypeDTO{" +
            "id=" + getId() +
            ", inchargeMasterType='" + getInchargeMasterType() + "'" +
            ", isActive='" + isIsActive() + "'" +
            "}";
    }
}
