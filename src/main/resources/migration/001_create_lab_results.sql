CREATE TABLE IF NOT EXISTS lab_results
(
    id         UUID PRIMARY KEY,
    patient_id VARCHAR(255)     NOT NULL,
    result     DOUBLE PRECISION NOT NULL,
    unit       VARCHAR(50)      NOT NULL,
    timestamp  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
