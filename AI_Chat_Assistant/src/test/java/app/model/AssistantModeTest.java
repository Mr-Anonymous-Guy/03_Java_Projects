package app.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AssistantModeTest {
    @Test
    void testAllModesHaveDisplayNames() {
        for (AssistantMode mode : AssistantMode.values()) {
            assertNotNull(mode.getDisplayName());
            assertFalse(mode.getDisplayName().isEmpty());
        }
    }

    @Test
    void testModeCount() {
        assertEquals(11, AssistantMode.values().length, "Should have exactly 11 assistant modes");
    }

    @Test
    void testGeneralModeDisplayName() {
        assertEquals("General Chat", AssistantMode.GENERAL.getDisplayName());
    }

    @Test
    void testCodingModeDisplayName() {
        assertEquals("Coding Assistant", AssistantMode.CODING.getDisplayName());
    }
}
