package app.service;

import app.model.AssistantMode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SystemPromptManagerTest {
    private SystemPromptManager manager;

    @BeforeEach
    void setUp() {
        manager = new SystemPromptManager();
    }

    @Test
    void testAllModesHavePrompts() {
        for (AssistantMode mode : AssistantMode.values()) {
            String prompt = manager.getPromptForMode(mode);
            assertNotNull(prompt, "Prompt should not be null for mode: " + mode);
            assertFalse(prompt.isEmpty(), "Prompt should not be empty for mode: " + mode);
        }
    }

    @Test
    void testGeneralModePromptContent() {
        String prompt = manager.getPromptForMode(AssistantMode.GENERAL);
        assertTrue(prompt.toLowerCase().contains("general") || prompt.toLowerCase().contains("helpful"));
    }

    @Test
    void testCodingModePromptContent() {
        String prompt = manager.getPromptForMode(AssistantMode.CODING);
        assertTrue(prompt.toLowerCase().contains("code") || prompt.toLowerCase().contains("software"));
    }

    @Test
    void testDifferentModesReturnDifferentPrompts() {
        String general = manager.getPromptForMode(AssistantMode.GENERAL);
        String coding = manager.getPromptForMode(AssistantMode.CODING);
        assertNotEquals(general, coding);
    }
}
