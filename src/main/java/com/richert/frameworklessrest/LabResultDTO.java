package com.richert.frameworklessrest;

class LabResultDTO {
    String patientId;
    double result;
    String unit;

    public LabResultDTO(String patientId, double result, String unit) {
        this.patientId = patientId;
        this.result = result;
        this.unit = unit;
    }

    public boolean isValid() {
        return patientId != null && !patientId.isEmpty() && unit != null && !unit.isEmpty();
    }
}