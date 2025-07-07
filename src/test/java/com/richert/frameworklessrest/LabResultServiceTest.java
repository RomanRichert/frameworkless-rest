package com.richert.frameworklessrest;

import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

class LabResultServiceTest {

    /**
     * Test getters and setters.
     *
     * <p>Method under test: {@link LabResultService#getInstance()}
     */
    @Test
    @DisplayName("Test getters and setters")
    @MethodsUnderTest({"LabResultService LabResultService.getInstance()"})
    void testGettersAndSetters() {
        // Arrange and Act
        LabResultService actualInstance = LabResultService.getInstance();

        // Assert
        assertSame(actualInstance, LabResultService.getInstance());
    }
}
