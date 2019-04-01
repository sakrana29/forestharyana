
CREATE TABLE IF NOT EXISTS blockMaster (
    id uuid,
    blockName text,
    rangeName text,
    rangeId text,
    divisionName text,
    divisionId text,
    circleName text,
    circleId text,
    stateName text,
    stateId text,
    isActive boolean,
    PRIMARY KEY(id)
);
