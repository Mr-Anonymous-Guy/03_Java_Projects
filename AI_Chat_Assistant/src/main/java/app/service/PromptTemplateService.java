package app.service;

import app.model.PromptTemplate;
import java.util.List;

public class PromptTemplateService {
    private final PromptTemplateRepository repository;

    public PromptTemplateService() {
        this.repository = new PromptTemplateRepository();
    }

    public List<String> getCategories() {
        return repository.getAllCategories();
    }

    public List<PromptTemplate> getTemplatesByCategory(String category) {
        return repository.getTemplatesByCategory(category);
    }
}
