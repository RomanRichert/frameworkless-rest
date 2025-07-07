package com.richert.frameworklessrest;

import com.sun.net.httpserver.HttpExchange;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class LabResultControllerTest {
    /**
     * Test {@link LabResultController#handle(HttpExchange)}.
     *
     * <p>Method under test: {@link LabResultController#handle(HttpExchange)}
     */
    @Test
    @DisplayName("Test handle(HttpExchange)")
    void testHandle() {
        // Arrange, Act and Assert
        //noinspection DataFlowIssue
        assertThrows(IllegalArgumentException.class, () -> new LabResultController().handle(null));
    }
}
