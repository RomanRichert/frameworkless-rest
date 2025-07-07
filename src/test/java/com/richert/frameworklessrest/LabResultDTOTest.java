package com.richert.frameworklessrest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LabResultDTOTest {
    /**
     * Test {@link LabResultDTO#LabResultDTO(String, double, String)}.
     *
     * <p>Method under test: {@link LabResultDTO#LabResultDTO(String, double, String)}
     */
    @Test
    @DisplayName("Test new LabResultDTO(String, double, String)")
    void testNewLabResultDTO() {
        // Arrange and Act
        LabResultDTO actualLabResultDTO = new LabResultDTO("42", 10.0d, "Unit");

        // Assert
        assertEquals("42", actualLabResultDTO.patientId);
        assertEquals("Unit", actualLabResultDTO.unit);
        assertEquals(10.0d, actualLabResultDTO.result);
        assertTrue(actualLabResultDTO.isValid());
    }

    /**
     * Test {@link LabResultDTO#isValid()}.
     *
     * <p>Method under test: {@link LabResultDTO#isValid()}
     */
    @Test
    @DisplayName("Test isValid()")
    void testIsValid() {
        // Arrange, Act and Assert
        assertFalse(new LabResultDTO("", 10.0d, null).isValid());
    }

    /**
     * Test {@link LabResultDTO#isValid()}.
     *
     * <ul>
     *   <li>Given {@link LabResultDTO#LabResultDTO(String, double, String)} with patientId is {@code
     *       42} and result is ten and unit is empty string.
     * </ul>
     *
     * <p>Method under test: {@link LabResultDTO#isValid()}
     */
    @Test
    @DisplayName(
            "Test isValid(); given LabResultDTO(String, double, String) with patientId is '42' and result is ten and unit is empty string")
    void testIsValid_givenLabResultDTOWithPatientIdIs42AndResultIsTenAndUnitIsEmptyString() {
        // Arrange, Act and Assert
        assertFalse(new LabResultDTO("42", 10.0d, "").isValid());
    }

    /**
     * Test {@link LabResultDTO#isValid()}.
     *
     * <ul>
     *   <li>Given {@link LabResultDTO#LabResultDTO(String, double, String)} with patientId is {@code
     *       42} and result is ten and unit is {@code null}.
     * </ul>
     *
     * <p>Method under test: {@link LabResultDTO#isValid()}
     */
    @Test
    @DisplayName(
            "Test isValid(); given LabResultDTO(String, double, String) with patientId is '42' and result is ten and unit is 'null'")
    void testIsValid_givenLabResultDTOWithPatientIdIs42AndResultIsTenAndUnitIsNull() {
        // Arrange, Act and Assert
        assertFalse(new LabResultDTO("42", 10.0d, null).isValid());
    }

    /**
     * Test {@link LabResultDTO#isValid()}.
     *
     * <ul>
     *   <li>Given {@link LabResultDTO#LabResultDTO(String, double, String)} with patientId is {@code
     *       null} and result is ten and unit is {@code null}.
     * </ul>
     *
     * <p>Method under test: {@link LabResultDTO#isValid()}
     */
    @Test
    @DisplayName(
            "Test isValid(); given LabResultDTO(String, double, String) with patientId is 'null' and result is ten and unit is 'null'")
    void testIsValid_givenLabResultDTOWithPatientIdIsNullAndResultIsTenAndUnitIsNull() {
        // Arrange, Act and Assert
        assertFalse(new LabResultDTO(null, 10.0d, null).isValid());
    }

    /**
     * Test {@link LabResultDTO#isValid()}.
     *
     * <ul>
     *   <li>Then return {@code true}.
     * </ul>
     *
     * <p>Method under test: {@link LabResultDTO#isValid()}
     */
    @Test
    @DisplayName("Test isValid(); then return 'true'")
    void testIsValid_thenReturnTrue() {
        // Arrange, Act and Assert
        assertTrue(new LabResultDTO("42", 10.0d, "Unit").isValid());
    }
}
