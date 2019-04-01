package com.haryana.forest.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the NurseryMaster entity.
 */
public class NurseryMasterDTO implements Serializable {

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

    public String getCircleId() {
        return circleId;
    }

    public void setCircleId(String circleId) {
        this.circleId = circleId;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    public String getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(String divisionId) {
        this.divisionId = divisionId;
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

    public String getBlockname() {
        return blockname;
    }

    public void setBlockname(String blockname) {
        this.blockname = blockname;
    }

    public UUID getBlockId() {
        return blockId;
    }

    public void setBlockId(UUID blockId) {
        this.blockId = blockId;
    }

    public String getBeatName() {
        return beatName;
    }

    public void setBeatName(String beatName) {
        this.beatName = beatName;
    }

    public String getBeatId() {
        return beatId;
    }

    public void setBeatId(String beatId) {
        this.beatId = beatId;
    }

    public String getNurseryName() {
        return nurseryName;
    }

    public void setNurseryName(String nurseryName) {
        this.nurseryName = nurseryName;
    }

    public String getNurseryAddress() {
        return nurseryAddress;
    }

    public void setNurseryAddress(String nurseryAddress) {
        this.nurseryAddress = nurseryAddress;
    }

    public ZonedDateTime getNurseryEstablishment() {
        return nurseryEstablishment;
    }

    public void setNurseryEstablishment(ZonedDateTime nurseryEstablishment) {
        this.nurseryEstablishment = nurseryEstablishment;
    }

    public Double getNurseryArea() {
        return nurseryArea;
    }

    public void setNurseryArea(Double nurseryArea) {
        this.nurseryArea = nurseryArea;
    }

    public String getSourceOfIrrigation() {
        return sourceOfIrrigation;
    }

    public void setSourceOfIrrigation(String sourceOfIrrigation) {
        this.sourceOfIrrigation = sourceOfIrrigation;
    }

    public String getSoilType() {
        return soilType;
    }

    public void setSoilType(String soilType) {
        this.soilType = soilType;
    }

    public String getOtherDetail() {
        return otherDetail;
    }

    public void setOtherDetail(String otherDetail) {
        this.otherDetail = otherDetail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        NurseryMasterDTO nurseryMasterDTO = (NurseryMasterDTO) o;
        if (nurseryMasterDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), nurseryMasterDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NurseryMasterDTO{" +
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
