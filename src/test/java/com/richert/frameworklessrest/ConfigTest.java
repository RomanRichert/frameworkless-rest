package com.richert.frameworklessrest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

class ConfigTest {
    /**
     * Test {@link Config#get(String)}.
     *
     * <p>Method under test: {@link Config#get(String)}
     */
    @Test
    @DisplayName("Test get(String)")
    @Tag("MaintainedBy")
    void testGet() {
        // Arrange, Act and Assert
        assertNull(Config.get("Key"));
    }
}
