
CREATE TABLE IF NOT EXISTS rangeMaster (
    id uuid,
    rangeName text,
    divisionNmae text,
    divisionId text,
    circleName text,
    circleId text,
    stateName text,
    isActive boolean,
    stateId text,
    PRIMARY KEY(id)
);
