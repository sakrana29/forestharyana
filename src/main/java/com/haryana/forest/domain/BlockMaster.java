package com.haryana.forest.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.Table;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A BlockMaster.
 */
@Table("blockMaster")
public class BlockMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private UUID id;

    @NotNull
    private String blockName;

    @NotNull
    private String rangeName;

    @NotNull
    private String rangeId;

    @NotNull
    private String divisionName;

    @NotNull
    private String divisionId;

    @NotNull
    private String circleName;

    @NotNull
    private String circleId;

    private String stateName;

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

    public String getBlockName() {
        return blockName;
    }

    public BlockMaster blockName(String blockName) {
        this.blockName = blockName;
        return this;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }

    public String getRangeName() {
        return rangeName;
    }

    public BlockMaster rangeName(String rangeName) {
        this.rangeName = rangeName;
        return this;
    }

    public void setRangeName(String rangeName) {
        this.rangeName = rangeName;
    }

    public String getRangeId() {
        return rangeId;
    }

    public BlockMaster rangeId(String rangeId) {
        this.rangeId = rangeId;
        return this;
    }

    public void setRangeId(String rangeId) {
        this.rangeId = rangeId;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public BlockMaster divisionName(String divisionName) {
        this.divisionName = divisionName;
        return this;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    public String getDivisionId() {
        return divisionId;
    }

    public BlockMaster divisionId(String divisionId) {
        this.divisionId = divisionId;
        return this;
    }

    public void setDivisionId(String divisionId) {
        this.divisionId = divisionId;
    }

    public String getCircleName() {
        return circleName;
    }

    public BlockMaster circleName(String circleName) {
        this.circleName = circleName;
        return this;
    }

    public void setCircleName(String circleName) {
        this.circleName = circleName;
    }

    public String getCircleId() {
        return circleId;
    }

    public BlockMaster circleId(String circleId) {
        this.circleId = circleId;
        return this;
    }

    public void setCircleId(String circleId) {
        this.circleId = circleId;
    }

    public String getStateName() {
        return stateName;
    }

    public BlockMaster stateName(String stateName) {
        this.stateName = stateName;
        return this;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getStateId() {
        return stateId;
    }

    public BlockMaster stateId(String stateId) {
        this.stateId = stateId;
        return this;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
    }

    public Boolean isIsActive() {
        return isActive;
    }

    public BlockMaster isActive(Boolean isActive) {
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
        BlockMaster blockMaster = (BlockMaster) o;
        if (blockMaster.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), blockMaster.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BlockMaster{" +
            "id=" + getId() +
            ", blockName='" + getBlockName() + "'" +
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
