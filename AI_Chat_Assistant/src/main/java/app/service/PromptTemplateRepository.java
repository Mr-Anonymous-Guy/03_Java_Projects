package app.service;

import app.model.PromptTemplate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PromptTemplateRepository {
    private final List<PromptTemplate> templates;

    public PromptTemplateRepository() {
        templates = new ArrayList<>();
        seedData();
    }

    private void seedData() {
        // Coding
        templates.add(new PromptTemplate("Coding", "Code Review", "Please review the following code and suggest improvements for performance and readability:\n\n[Insert Code Here]"));
        templates.add(new PromptTemplate("Coding", "Bug Investigation", "I am getting the following error. Can you help me find the root cause and a solution?\n\n[Insert Error Log Here]"));
        templates.add(new PromptTemplate("Coding", "Refactoring", "Please refactor the following code to follow SOLID principles:\n\n[Insert Code Here]"));
        templates.add(new PromptTemplate("Coding", "DSA Practice", "Give me a medium-level LeetCode style question on Graphs. Provide only the prompt first."));
        templates.add(new PromptTemplate("Coding", "System Design", "Design a scalable backend architecture for a URL shortening service like Bitly. What databases would you use?"));

        // Study
        templates.add(new PromptTemplate("Study", "Explain Topic", "Explain [Topic] to me like I am a 5-year-old."));
        templates.add(new PromptTemplate("Study", "Generate Notes", "Create concise bullet-point study notes for the following text:\n\n[Insert Text Here]"));
        templates.add(new PromptTemplate("Study", "Quiz Me", "Generate 5 multiple-choice questions on [Subject] to test my knowledge. Don't give me the answers yet."));
        templates.add(new PromptTemplate("Study", "Revision Sheet", "Create a one-page revision sheet summarizing the key concepts of [Subject]."));

        // Career
        templates.add(new PromptTemplate("Career", "Resume Review", "Please review my resume bullet points and rewrite them to be more action-oriented and metric-driven:\n\n[Insert Bullets Here]"));
        templates.add(new PromptTemplate("Career", "LinkedIn Optimization", "What should I include in my LinkedIn 'About' section if I am looking for junior Java developer roles?"));
        templates.add(new PromptTemplate("Career", "Internship Preparation", "What are the core Java and Spring Boot concepts I must know for a backend engineering internship?"));
        templates.add(new PromptTemplate("Career", "Interview Practice", "Act as a hiring manager. Ask me standard behavioral questions for a software engineering role, one by one."));

        // Research
        templates.add(new PromptTemplate("Research", "Research Topic", "Provide an overview of the current state-of-the-art in [Topic]."));
        templates.add(new PromptTemplate("Research", "Compare Technologies", "Compare [Tech A] and [Tech B] across performance, ecosystem, and learning curve."));
        templates.add(new PromptTemplate("Research", "Summarize Research", "Summarize the key findings and methodology of this research paper abstract:\n\n[Insert Abstract Here]"));

        // Productivity
        templates.add(new PromptTemplate("Productivity", "Daily Planner", "Help me organize my day. I have the following tasks: [List Tasks]. Create a time-blocked schedule."));
        templates.add(new PromptTemplate("Productivity", "Weekly Planner", "Help me create a weekly study schedule to learn React in 2 hours a day."));
        templates.add(new PromptTemplate("Productivity", "Goal Breakdown", "I want to achieve [Goal]. Break this down into small, actionable milestones."));

        // AI
        templates.add(new PromptTemplate("AI", "Prompt Optimizer", "Please analyze my prompt and rewrite it to be clearer and get better results from an LLM:\n\n[Insert Prompt Here]"));
        templates.add(new PromptTemplate("AI", "RAG Architect", "How should I structure a Retrieval-Augmented Generation pipeline using LangChain and a vector database?"));
        templates.add(new PromptTemplate("AI", "AI Project Planner", "I want to build an AI app that [Functionality]. What architecture and models should I use?"));
    }

    public List<PromptTemplate> getTemplatesByCategory(String category) {
        return templates.stream()
                .filter(t -> t.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }
    
    public List<String> getAllCategories() {
        return templates.stream()
                .map(PromptTemplate::getCategory)
                .distinct()
                .collect(Collectors.toList());
    }
}
