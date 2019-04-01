
CREATE TABLE IF NOT EXISTS nurseryMaster (
    id uuid,
    circleName text,
    circleId text,
    divisionName text,
    divisionId text,
    rangeName text,
    rangeId text,
    blockname text,
    blockId uuid,
    beatName text,
    beatId text,
    nurseryName text,
    nurseryAddress text,
    nurseryEstablishment tuple<timestamp,varchar>,
    nurseryArea double,
    sourceOfIrrigation text,
    soilType text,
    otherDetail text,
    PRIMARY KEY(id)
);
