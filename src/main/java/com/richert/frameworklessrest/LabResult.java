package com.richert.frameworklessrest;

import java.sql.Timestamp;
import java.util.UUID;

class LabResult {
    private final UUID id;
    private final String patientId;
    private final double result;
    private final String unit;
    private final Timestamp timestamp;

    public LabResult(String patientId, double result, String unit) {
        this.id = UUID.randomUUID();
        this.patientId = patientId;
        this.result = result;
        this.unit = unit;
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }

    public LabResult(UUID id, String patientId, double result, String unit, Timestamp timestamp) {
        this.id = id;
        this.patientId = patientId;
        this.result = result;
        this.unit = unit;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "LabResult{" +
                "patientId='" + patientId + '\'' +
                ", result=" + result +
                ", timestamp=" + timestamp +
                '}';
    }

    public UUID getId() {
        return id;
    }

    public String getPatientId() {
        return patientId;
    }

    public double getResult() {
        return result;
    }

    public String getUnit() {
        return unit;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }
}
