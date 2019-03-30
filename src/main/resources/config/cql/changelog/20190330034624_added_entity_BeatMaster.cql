
CREATE TABLE IF NOT EXISTS beatMaster (
    id uuid,
    beatName text,
    blockName text,
    blockId text,
    rangeName text,
    rangeId text,
    divisionName text,
    divisionId uuid,
    circleName text,
    circleId text,
    stateName text,
    stateId text,
    isActive boolean,
    PRIMARY KEY(id)
);
