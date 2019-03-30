package com.haryana.forest.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.Table;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A BeatMaster.
 */
@Table("beatMaster")
public class BeatMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
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

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getBeatName() {
        return beatName;
    }

    public BeatMaster beatName(String beatName) {
        this.beatName = beatName;
        return this;
    }

    public void setBeatName(String beatName) {
        this.beatName = beatName;
    }

    public String getBlockName() {
        return blockName;
    }

    public BeatMaster blockName(String blockName) {
        this.blockName = blockName;
        return this;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }

    public String getBlockId() {
        return blockId;
    }

    public BeatMaster blockId(String blockId) {
        this.blockId = blockId;
        return this;
    }

    public void setBlockId(String blockId) {
        this.blockId = blockId;
    }

    public String getRangeName() {
        return rangeName;
    }

    public BeatMaster rangeName(String rangeName) {
        this.rangeName = rangeName;
        return this;
    }

    public void setRangeName(String rangeName) {
        this.rangeName = rangeName;
    }

    public String getRangeId() {
        return rangeId;
    }

    public BeatMaster rangeId(String rangeId) {
        this.rangeId = rangeId;
        return this;
    }

    public void setRangeId(String rangeId) {
        this.rangeId = rangeId;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public BeatMaster divisionName(String divisionName) {
        this.divisionName = divisionName;
        return this;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    public UUID getDivisionId() {
        return divisionId;
    }

    public BeatMaster divisionId(UUID divisionId) {
        this.divisionId = divisionId;
        return this;
    }

    public void setDivisionId(UUID divisionId) {
        this.divisionId = divisionId;
    }

    public String getCircleName() {
        return circleName;
    }

    public BeatMaster circleName(String circleName) {
        this.circleName = circleName;
        return this;
    }

    public void setCircleName(String circleName) {
        this.circleName = circleName;
    }

    public String getCircleId() {
        return circleId;
    }

    public BeatMaster circleId(String circleId) {
        this.circleId = circleId;
        return this;
    }

    public void setCircleId(String circleId) {
        this.circleId = circleId;
    }

    public String getStateName() {
        return stateName;
    }

    public BeatMaster stateName(String stateName) {
        this.stateName = stateName;
        return this;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getStateId() {
        return stateId;
    }

    public BeatMaster stateId(String stateId) {
        this.stateId = stateId;
        return this;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
    }

    public Boolean isIsActive() {
        return isActive;
    }

    public BeatMaster isActive(Boolean isActive) {
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
        BeatMaster beatMaster = (BeatMaster) o;
        if (beatMaster.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), beatMaster.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BeatMaster{" +
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
