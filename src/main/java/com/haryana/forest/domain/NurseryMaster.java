package com.haryana.forest.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.Table;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * A NurseryMaster.
 */
@Table("nurseryMaster")
public class NurseryMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private UUID id;

    private String circleName;

    private String circleId;

    @NotNull
    private String divisionName;

    @NotNull
    private String divisionId;

    @NotNull
    private String rangeName;

    @NotNull
    private String rangeId;

    private String blockname;

    @NotNull
    private UUID blockId;

    @NotNull
    private String beatName;

    @NotNull
    private String beatId;

    @NotNull
    private String nurseryName;

    @NotNull
    private String nurseryAddress;

    private ZonedDateTime nurseryEstablishment;

    private Double nurseryArea;

    private String sourceOfIrrigation;

    private String soilType;

    private String otherDetail;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCircleName() {
        return circleName;
    }

    public NurseryMaster circleName(String circleName) {
        this.circleName = circleName;
        return this;
    }

    public void setCircleName(String circleName) {
        this.circleName = circleName;
    }

    public String getCircleId() {
        return circleId;
    }

    public NurseryMaster circleId(String circleId) {
        this.circleId = circleId;
        return this;
    }

    public void setCircleId(String circleId) {
        this.circleId = circleId;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public NurseryMaster divisionName(String divisionName) {
        this.divisionName = divisionName;
        return this;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    public String getDivisionId() {
        return divisionId;
    }

    public NurseryMaster divisionId(String divisionId) {
        this.divisionId = divisionId;
        return this;
    }

    public void setDivisionId(String divisionId) {
        this.divisionId = divisionId;
    }

    public String getRangeName() {
        return rangeName;
    }

    public NurseryMaster rangeName(String rangeName) {
        this.rangeName = rangeName;
        return this;
    }

    public void setRangeName(String rangeName) {
        this.rangeName = rangeName;
    }

    public String getRangeId() {
        return rangeId;
    }

    public NurseryMaster rangeId(String rangeId) {
        this.rangeId = rangeId;
        return this;
    }

    public void setRangeId(String rangeId) {
        this.rangeId = rangeId;
    }

    public String getBlockname() {
        return blockname;
    }

    public NurseryMaster blockname(String blockname) {
        this.blockname = blockname;
        return this;
    }

    public void setBlockname(String blockname) {
        this.blockname = blockname;
    }

    public UUID getBlockId() {
        return blockId;
    }

    public NurseryMaster blockId(UUID blockId) {
        this.blockId = blockId;
        return this;
    }

    public void setBlockId(UUID blockId) {
        this.blockId = blockId;
    }

    public String getBeatName() {
        return beatName;
    }

    public NurseryMaster beatName(String beatName) {
        this.beatName = beatName;
        return this;
    }

    public void setBeatName(String beatName) {
        this.beatName = beatName;
    }

    public String getBeatId() {
        return beatId;
    }

    public NurseryMaster beatId(String beatId) {
        this.beatId = beatId;
        return this;
    }

    public void setBeatId(String beatId) {
        this.beatId = beatId;
    }

    public String getNurseryName() {
        return nurseryName;
    }

    public NurseryMaster nurseryName(String nurseryName) {
        this.nurseryName = nurseryName;
        return this;
    }

    public void setNurseryName(String nurseryName) {
        this.nurseryName = nurseryName;
    }

    public String getNurseryAddress() {
        return nurseryAddress;
    }

    public NurseryMaster nurseryAddress(String nurseryAddress) {
        this.nurseryAddress = nurseryAddress;
        return this;
    }

    public void setNurseryAddress(String nurseryAddress) {
        this.nurseryAddress = nurseryAddress;
    }

    public ZonedDateTime getNurseryEstablishment() {
        return nurseryEstablishment;
    }

    public NurseryMaster nurseryEstablishment(ZonedDateTime nurseryEstablishment) {
        this.nurseryEstablishment = nurseryEstablishment;
        return this;
    }

    public void setNurseryEstablishment(ZonedDateTime nurseryEstablishment) {
        this.nurseryEstablishment = nurseryEstablishment;
    }

    public Double getNurseryArea() {
        return nurseryArea;
    }

    public NurseryMaster nurseryArea(Double nurseryArea) {
        this.nurseryArea = nurseryArea;
        return this;
    }

    public void setNurseryArea(Double nurseryArea) {
        this.nurseryArea = nurseryArea;
    }

    public String getSourceOfIrrigation() {
        return sourceOfIrrigation;
    }

    public NurseryMaster sourceOfIrrigation(String sourceOfIrrigation) {
        this.sourceOfIrrigation = sourceOfIrrigation;
        return this;
    }

    public void setSourceOfIrrigation(String sourceOfIrrigation) {
        this.sourceOfIrrigation = sourceOfIrrigation;
    }

    public String getSoilType() {
        return soilType;
    }

    public NurseryMaster soilType(String soilType) {
        this.soilType = soilType;
        return this;
    }

    public void setSoilType(String soilType) {
        this.soilType = soilType;
    }

    public String getOtherDetail() {
        return otherDetail;
    }

    public NurseryMaster otherDetail(String otherDetail) {
        this.otherDetail = otherDetail;
        return this;
    }

    public void setOtherDetail(String otherDetail) {
        this.otherDetail = otherDetail;
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
        NurseryMaster nurseryMaster = (NurseryMaster) o;
        if (nurseryMaster.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), nurseryMaster.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NurseryMaster{" +
            "id=" + getId() +
            ", circleName='" + getCircleName() + "'" +
            ", circleId='" + getCircleId() + "'" +
            ", divisionName='" + getDivisionName() + "'" +
            ", divisionId='" + getDivisionId() + "'" +
            ", rangeName='" + getRangeName() + "'" +
            ", rangeId='" + getRangeId() + "'" +
            ", blockname='" + getBlockname() + "'" +
            ", blockId='" + getBlockId() + "'" +
            ", beatName='" + getBeatName() + "'" +
            ", beatId='" + getBeatId() + "'" +
            ", nurseryName='" + getNurseryName() + "'" +
            ", nurseryAddress='" + getNurseryAddress() + "'" +
            ", nurseryEstablishment='" + getNurseryEstablishment() + "'" +
            ", nurseryArea=" + getNurseryArea() +
            ", sourceOfIrrigation='" + getSourceOfIrrigation() + "'" +
            ", soilType='" + getSoilType() + "'" +
            ", otherDetail='" + getOtherDetail() + "'" +
            "}";
    }
}
