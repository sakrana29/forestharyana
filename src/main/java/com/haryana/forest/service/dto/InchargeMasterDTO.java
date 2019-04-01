package com.haryana.forest.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the InchargeMaster entity.
 */
public class InchargeMasterDTO implements Serializable {

    private UUID id;

    private String inchargeMasterType;

    @NotNull
    private String inchargeMasterTypeId;

    @NotNull
    private String nurseryName;

    @NotNull
    private String nurseryId;

    @NotNull
    private String inchargeName;

    @NotNull
    private String inchargeDesignation;

    @NotNull
    private String inchargeMobile;

    private ZonedDateTime chargeTakenFrom;

    private ZonedDateTime chargeReleaveDate;

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

    public String getInchargeMasterTypeId() {
        return inchargeMasterTypeId;
    }

    public void setInchargeMasterTypeId(String inchargeMasterTypeId) {
        this.inchargeMasterTypeId = inchargeMasterTypeId;
    }

    public String getNurseryName() {
        return nurseryName;
    }

    public void setNurseryName(String nurseryName) {
        this.nurseryName = nurseryName;
    }

    public String getNurseryId() {
        return nurseryId;
    }

    public void setNurseryId(String nurseryId) {
        this.nurseryId = nurseryId;
    }

    public String getInchargeName() {
        return inchargeName;
    }

    public void setInchargeName(String inchargeName) {
        this.inchargeName = inchargeName;
    }

    public String getInchargeDesignation() {
        return inchargeDesignation;
    }

    public void setInchargeDesignation(String inchargeDesignation) {
        this.inchargeDesignation = inchargeDesignation;
    }

    public String getInchargeMobile() {
        return inchargeMobile;
    }

    public void setInchargeMobile(String inchargeMobile) {
        this.inchargeMobile = inchargeMobile;
    }

    public ZonedDateTime getChargeTakenFrom() {
        return chargeTakenFrom;
    }

    public void setChargeTakenFrom(ZonedDateTime chargeTakenFrom) {
        this.chargeTakenFrom = chargeTakenFrom;
    }

    public ZonedDateTime getChargeReleaveDate() {
        return chargeReleaveDate;
    }

    public void setChargeReleaveDate(ZonedDateTime chargeReleaveDate) {
        this.chargeReleaveDate = chargeReleaveDate;
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

        InchargeMasterDTO inchargeMasterDTO = (InchargeMasterDTO) o;
        if (inchargeMasterDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), inchargeMasterDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "InchargeMasterDTO{" +
            "id=" + getId() +
            ", inchargeMasterType='" + getInchargeMasterType() + "'" +
            ", inchargeMasterTypeId='" + getInchargeMasterTypeId() + "'" +
            ", nurseryName='" + getNurseryName() + "'" +
            ", nurseryId='" + getNurseryId() + "'" +
            ", inchargeName='" + getInchargeName() + "'" +
            ", inchargeDesignation='" + getInchargeDesignation() + "'" +
            ", inchargeMobile='" + getInchargeMobile() + "'" +
            ", chargeTakenFrom='" + getChargeTakenFrom() + "'" +
            ", chargeReleaveDate='" + getChargeReleaveDate() + "'" +
            ", isActive='" + isIsActive() + "'" +
            "}";
    }
}
