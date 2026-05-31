package app.model;

public enum AssistantMode {
    GENERAL("General Chat"),
    CODING("Coding Assistant"),
    STUDY("Study Assistant"),
    RESEARCH("Research Assistant"),
    RESUME("Resume Assistant"),
    INTERVIEW("Interview Coach"),
    CAREER("Career Assistant"),
    PRODUCTIVITY("Productivity Assistant"),
    WRITING("Writing Assistant"),
    DATA_ANALYSIS("Data Analysis Assistant"),
    AI_ENGINEERING("AI Engineering Assistant");

    private final String displayName;

    AssistantMode(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
