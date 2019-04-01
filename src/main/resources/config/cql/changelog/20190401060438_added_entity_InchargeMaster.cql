
CREATE TABLE IF NOT EXISTS inchargeMaster (
    id uuid,
    inchargeMasterType text,
    inchargeMasterTypeId text,
    nurseryName text,
    nurseryId text,
    inchargeName text,
    inchargeDesignation text,
    inchargeMobile text,
    chargeTakenFrom tuple<timestamp,varchar>,
    chargeReleaveDate tuple<timestamp,varchar>,
    isActive boolean,
    PRIMARY KEY(id)
);
