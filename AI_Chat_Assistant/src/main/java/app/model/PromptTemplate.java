package app.model;

public class PromptTemplate {
    private String category;
    private String title;
    private String content;

    public PromptTemplate(String category, String title, String content) {
        this.category = category;
        this.title = title;
        this.content = content;
    }

    public String getCategory() { return category; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    
    @Override
    public String toString() {
        return title;
    }
}
