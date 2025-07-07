package com.richert.frameworklessrest;

import com.diffblue.cover.annotations.MethodsUnderTest;
import jakarta.json.JsonObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LabResultMapperTest {

    /**
     * Test {@link LabResultMapper#toEntity(LabResultDTO)}.
     *
     * <ul>
     *   <li>Then return PatientId is {@code 42}.
     * </ul>
     *
     * <p>Method under test: {@link LabResultMapper#toEntity(LabResultDTO)}
     */
    @Test
    @DisplayName("Test toEntity(LabResultDTO); then return PatientId is '42'")
    @MethodsUnderTest({"LabResult LabResultMapper.toEntity(LabResultDTO)"})
    void testToEntity_thenReturnPatientIdIs42() {
        // Arrange and Act
        LabResult actualToEntityResult =
                LabResultMapper.toEntity(new LabResultDTO("42", 10.0d, "Unit"));

        // Assert
        assertEquals("42", actualToEntityResult.getPatientId());
        assertEquals("Unit", actualToEntityResult.getUnit());
        assertEquals(10.0d, actualToEntityResult.getResult());
    }

    /**
     * Test {@link LabResultMapper#toEntity(LabResultDTO)}.
     *
     * <ul>
     *   <li>When {@code null}.
     *   <li>Then throw {@link IllegalArgumentException}.
     * </ul>
     *
     * <p>Method under test: {@link LabResultMapper#toEntity(LabResultDTO)}
     */
    @Test
    @DisplayName("Test toEntity(LabResultDTO); when 'null'; then throw IllegalArgumentException")
    @MethodsUnderTest({"LabResult LabResultMapper.toEntity(LabResultDTO)"})
    void testToEntity_whenNull_thenThrowIllegalArgumentException() {
        // Arrange, Act and Assert
        //noinspection DataFlowIssue
        assertThrows(IllegalArgumentException.class, () -> LabResultMapper.toEntity(null));
    }

    /**
     * Test {@link LabResultMapper#toJson(LabResult)}.
     *
     * <ul>
     *   <li>Then return size is five.
     * </ul>
     *
     * <p>Method under test: {@link LabResultMapper#toJson(LabResult)}
     */
    @Test
    @DisplayName("Test toJson(LabResult); then return size is five")
    @MethodsUnderTest({"JsonObject LabResultMapper.toJson(LabResult)"})
    void testToJson_thenReturnSizeIsFive() {
        // Arrange and Act
        JsonObject actualToJsonResult = LabResultMapper.toJson(new LabResult("42", 10.0d, "Unit"));

        // Assert
        assertEquals(5, actualToJsonResult.size());
        assertTrue(actualToJsonResult.containsKey("id"));
        assertTrue(actualToJsonResult.containsKey("patientId"));
        assertTrue(actualToJsonResult.containsKey("result"));
        assertTrue(actualToJsonResult.containsKey("timestamp"));
        assertTrue(actualToJsonResult.containsKey("unit"));
    }

    /**
     * Test {@link LabResultMapper#toJson(LabResult)}.
     *
     * <ul>
     *   <li>When {@code null}.
     *   <li>Then throw {@link IllegalArgumentException}.
     * </ul>
     *
     * <p>Method under test: {@link LabResultMapper#toJson(LabResult)}
     */
    @Test
    @DisplayName("Test toJson(LabResult); when 'null'; then throw IllegalArgumentException")
    @MethodsUnderTest({"JsonObject LabResultMapper.toJson(LabResult)"})
    void testToJson_whenNull_thenThrowIllegalArgumentException() {
        // Arrange, Act and Assert
        //noinspection DataFlowIssue
        assertThrows(IllegalArgumentException.class, () -> LabResultMapper.toJson(null));
    }
}
