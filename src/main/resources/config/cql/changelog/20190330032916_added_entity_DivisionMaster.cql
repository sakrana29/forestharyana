
CREATE TABLE IF NOT EXISTS divisionMaster (
    id uuid,
    divisionName text,
    circleName text,
    circleId text,
    stateName text,
    stateId text,
    isActive boolean,
    PRIMARY KEY(id)
);
