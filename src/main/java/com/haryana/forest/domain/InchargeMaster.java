package com.haryana.forest.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.Table;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * A InchargeMaster.
 */
@Table("inchargeMaster")
public class InchargeMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
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

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getInchargeMasterType() {
        return inchargeMasterType;
    }

    public InchargeMaster inchargeMasterType(String inchargeMasterType) {
        this.inchargeMasterType = inchargeMasterType;
        return this;
    }

    public void setInchargeMasterType(String inchargeMasterType) {
        this.inchargeMasterType = inchargeMasterType;
    }

    public String getInchargeMasterTypeId() {
        return inchargeMasterTypeId;
    }

    public InchargeMaster inchargeMasterTypeId(String inchargeMasterTypeId) {
        this.inchargeMasterTypeId = inchargeMasterTypeId;
        return this;
    }

    public void setInchargeMasterTypeId(String inchargeMasterTypeId) {
        this.inchargeMasterTypeId = inchargeMasterTypeId;
    }

    public String getNurseryName() {
        return nurseryName;
    }

    public InchargeMaster nurseryName(String nurseryName) {
        this.nurseryName = nurseryName;
        return this;
    }

    public void setNurseryName(String nurseryName) {
        this.nurseryName = nurseryName;
    }

    public String getNurseryId() {
        return nurseryId;
    }

    public InchargeMaster nurseryId(String nurseryId) {
        this.nurseryId = nurseryId;
        return this;
    }

    public void setNurseryId(String nurseryId) {
        this.nurseryId = nurseryId;
    }

    public String getInchargeName() {
        return inchargeName;
    }

    public InchargeMaster inchargeName(String inchargeName) {
        this.inchargeName = inchargeName;
        return this;
    }

    public void setInchargeName(String inchargeName) {
        this.inchargeName = inchargeName;
    }

    public String getInchargeDesignation() {
        return inchargeDesignation;
    }

    public InchargeMaster inchargeDesignation(String inchargeDesignation) {
        this.inchargeDesignation = inchargeDesignation;
        return this;
    }

    public void setInchargeDesignation(String inchargeDesignation) {
        this.inchargeDesignation = inchargeDesignation;
    }

    public String getInchargeMobile() {
        return inchargeMobile;
    }

    public InchargeMaster inchargeMobile(String inchargeMobile) {
        this.inchargeMobile = inchargeMobile;
        return this;
    }

    public void setInchargeMobile(String inchargeMobile) {
        this.inchargeMobile = inchargeMobile;
    }

    public ZonedDateTime getChargeTakenFrom() {
        return chargeTakenFrom;
    }

    public InchargeMaster chargeTakenFrom(ZonedDateTime chargeTakenFrom) {
        this.chargeTakenFrom = chargeTakenFrom;
        return this;
    }

    public void setChargeTakenFrom(ZonedDateTime chargeTakenFrom) {
        this.chargeTakenFrom = chargeTakenFrom;
    }

    public ZonedDateTime getChargeReleaveDate() {
        return chargeReleaveDate;
    }

    public InchargeMaster chargeReleaveDate(ZonedDateTime chargeReleaveDate) {
        this.chargeReleaveDate = chargeReleaveDate;
        return this;
    }

    public void setChargeReleaveDate(ZonedDateTime chargeReleaveDate) {
        this.chargeReleaveDate = chargeReleaveDate;
    }

    public Boolean isIsActive() {
        return isActive;
    }

    public InchargeMaster isActive(Boolean isActive) {
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
        InchargeMaster inchargeMaster = (InchargeMaster) o;
        if (inchargeMaster.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), inchargeMaster.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "InchargeMaster{" +
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
