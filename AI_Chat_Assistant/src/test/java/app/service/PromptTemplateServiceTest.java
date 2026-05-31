package app.service;

import app.model.PromptTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class PromptTemplateServiceTest {
    private PromptTemplateService service;

    @BeforeEach
    void setUp() {
        service = new PromptTemplateService();
    }

    @Test
    void testGetCategoriesReturnsNonEmpty() {
        List<String> categories = service.getCategories();
        assertNotNull(categories);
        assertFalse(categories.isEmpty());
    }

    @Test
    void testCodingCategoryExists() {
        List<String> categories = service.getCategories();
        assertTrue(categories.contains("Coding"));
    }

    @Test
    void testGetTemplatesByCategoryReturnsList() {
        List<PromptTemplate> templates = service.getTemplatesByCategory("Coding");
        assertNotNull(templates);
        assertFalse(templates.isEmpty());
    }

    @Test
    void testInvalidCategoryReturnsEmptyList() {
        List<PromptTemplate> templates = service.getTemplatesByCategory("NonExistentCategory");
        assertNotNull(templates);
        assertTrue(templates.isEmpty());
    }

    @Test
    void testAllCategoriesHaveTemplates() {
        for (String category : service.getCategories()) {
            List<PromptTemplate> templates = service.getTemplatesByCategory(category);
            assertFalse(templates.isEmpty(), "Category '" + category + "' should have templates");
        }
    }
}
