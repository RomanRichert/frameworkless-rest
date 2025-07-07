package com.richert.frameworklessrest;

import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

class LabResultRepositoryTest {
    /**
     * Test getters and setters.
     *
     * <p>Method under test: {@link LabResultRepository#getInstance()}
     */
    @Test
    @DisplayName("Test getters and setters")
    @MethodsUnderTest({"LabResultRepository LabResultRepository.getInstance()"})
    void testGettersAndSetters() {
        // Arrange and Act
        LabResultRepository actualInstance = LabResultRepository.getInstance();

        // Assert
        assertSame(actualInstance, LabResultRepository.getInstance());
    }
}
