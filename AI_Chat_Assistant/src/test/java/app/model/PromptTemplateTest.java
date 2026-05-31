package app.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PromptTemplateTest {
    @Test
    void testPromptTemplateCreation() {
        PromptTemplate pt = new PromptTemplate("Coding", "Code Review", "Review my code...");
        assertEquals("Coding", pt.getCategory());
        assertEquals("Code Review", pt.getTitle());
        assertEquals("Review my code...", pt.getContent());
    }

    @Test
    void testToStringReturnsTitle() {
        PromptTemplate pt = new PromptTemplate("AI", "RAG Architect", "Design a RAG pipeline...");
        assertEquals("RAG Architect", pt.toString());
    }
}
