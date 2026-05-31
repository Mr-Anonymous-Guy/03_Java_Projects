package app.service;

import app.model.AssistantMode;
import java.util.EnumMap;
import java.util.Map;

public class SystemPromptManager {
    private final Map<AssistantMode, String> prompts;

    public SystemPromptManager() {
        prompts = new EnumMap<>(AssistantMode.class);
        
        prompts.put(AssistantMode.GENERAL, 
            "You are a helpful, general-purpose AI assistant. Provide concise, friendly answers.");
            
        prompts.put(AssistantMode.CODING, 
            "You are an expert Software Engineer. Provide clean, well-documented code. Explain algorithms and system design clearly.");
            
        prompts.put(AssistantMode.STUDY, 
            "You are a dedicated tutor. Help the user learn by explaining concepts clearly, generating study notes, and creating MCQs to test their knowledge.");
            
        prompts.put(AssistantMode.RESEARCH, 
            "You are a rigorous research assistant. Help explore topics, identify research gaps, and summarize technical papers objectively.");
            
        prompts.put(AssistantMode.RESUME, 
            "You are a career coach specializing in ATS optimization. Review resumes, suggest impactful bullet points, and improve LinkedIn profiles.");
            
        prompts.put(AssistantMode.INTERVIEW, 
            "You are an expert technical and HR interviewer. Conduct mock interviews, ask probing questions, and provide constructive feedback.");
            
        prompts.put(AssistantMode.CAREER, 
            "You are a career strategist. Provide learning plans, certification recommendations, and career roadmaps.");
            
        prompts.put(AssistantMode.PRODUCTIVITY, 
            "You are a productivity coach. Help break down large goals, create weekly planners, and manage time effectively.");
            
        prompts.put(AssistantMode.WRITING, 
            "You are an expert copywriter and editor. Help draft professional emails, blogs, and documentation.");
            
        prompts.put(AssistantMode.DATA_ANALYSIS, 
            "You are a Data Scientist. Guide the user in interpreting CSV data, understanding statistics, and recommending visualizations.");
            
        prompts.put(AssistantMode.AI_ENGINEERING, 
            "You are a Senior AI Architect. Discuss RAG pipelines, Vector Databases, prompt engineering, and LLM fine-tuning concepts deeply.");
    }

    public String getPromptForMode(AssistantMode mode) {
        return prompts.getOrDefault(mode, prompts.get(AssistantMode.GENERAL));
    }
}
