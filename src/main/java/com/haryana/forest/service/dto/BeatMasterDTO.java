package com.haryana.forest.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the BeatMaster entity.
 */
public class BeatMasterDTO implements Serializable {

    private UUID id;

    @NotNull
    private String beatName;

    @NotNull
    private String blockName;

    @NotNull
    private String blockId;

    private String rangeName;

    private String rangeId;

    private String divisionName;

    private UUID divisionId;

    private String circleName;

    private String circleId;

    private String stateName;

    private String stateId;

    private Boolean isActive;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getBeatName() {
        return beatName;
    }

    public void setBeatName(String beatName) {
        this.beatName = beatName;
    }

    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }

    public String getBlockId() {
        return blockId;
    }

    public void setBlockId(String blockId) {
        this.blockId = blockId;
    }

    public String getRangeName() {
        return rangeName;
    }

    public void setRangeName(String rangeName) {
        this.rangeName = rangeName;
    }

    public String getRangeId() {
        return rangeId;
    }

    public void setRangeId(String rangeId) {
        this.rangeId = rangeId;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    public UUID getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(UUID divisionId) {
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

        BeatMasterDTO beatMasterDTO = (BeatMasterDTO) o;
        if (beatMasterDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), beatMasterDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BeatMasterDTO{" +
            "id=" + getId() +
            ", beatName='" + getBeatName() + "'" +
            ", blockName='" + getBlockName() + "'" +
            ", blockId='" + getBlockId() + "'" +
            ", rangeName='" + getRangeName() + "'" +
            ", rangeId='" + getRangeId() + "'" +
            ", divisionName='" + getDivisionName() + "'" +
            ", divisionId='" + getDivisionId() + "'" +
            ", circleName='" + getCircleName() + "'" +
            ", circleId='" + getCircleId() + "'" +
            ", stateName='" + getStateName() + "'" +
            ", stateId='" + getStateId() + "'" +
            ", isActive='" + isIsActive() + "'" +
            "}";
    }
}
