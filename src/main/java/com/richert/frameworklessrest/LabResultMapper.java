package com.richert.frameworklessrest;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import org.jetbrains.annotations.NotNull;

class LabResultMapper {

    private LabResultMapper() {
    }

    public static LabResult toEntity(@NotNull LabResultDTO dto) {
        return new LabResult(dto.patientId, dto.result, dto.unit);
    }

    public static JsonObject toJson(@NotNull LabResult entity) {
        return Json.createObjectBuilder()
                .add("id", entity.getId().toString())
                .add("patientId", entity.getPatientId())
                .add("result", entity.getResult())
                .add("unit", entity.getUnit())
                .add("timestamp", entity.getTimestamp().toString()).build();
    }
}