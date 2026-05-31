# 🤖 AI Chat Assistant

A professional Java Swing Desktop Application functioning as a powerful Conversational AI Assistant. It integrates directly with a local **Ollama** server, enabling fast, private, offline LLM inferences. 

This application emphasizes strict separation of concerns and utilizes the **Strategy Pattern** to swap dynamic system prompts, altering the assistant's behavior at runtime without changing the core chat logic.

## 🌟 Key Features
- **Assistant Modes**: Select from 11 specialized modes (`General`, `Coding`, `Study`, `Research`, `Resume`, `Interview`, `Career`, `Productivity`, `Writing`, `Data Analysis`, `AI Engineering`). Selecting a mode dynamically updates the underlying System Prompt fed into Ollama.
- **Natural Conversation**: Acts as a regular chat interface (like ChatGPT) taking normal prompt queries.
- **Prompt Template Library**: A side-panel populated with ready-to-use structural prompts (Code Review, Bug Investigation, Resume Optimization, etc.) grouped by category. Clicking a template auto-fills the chat input.
- **Non-blocking UI**: Uses `SwingWorker` threads to ensure the UI remains fully responsive while Ollama generates text.

## 🏗️ Architecture Design
1. **Model Layer**: `AssistantMode` (Enum) and `PromptTemplate` definition.
2. **Service Layer**: 
   - `SystemPromptManager`: Manages the Strategy pattern injection of system prompts.
   - `PromptTemplateRepository`: Stores the prompt template data.
   - `OllamaClient`: REST API client sending asynchronous HTTP POST requests to Ollama using Java 11's standard `HttpClient` and Google Gson for JSON processing.
3. **UI Layer**: `ChatDashboardFrame` constructed with Java Swing layouts (BorderLayout, GridLayout).

## 🚀 Setup & Execution Instructions

### Step 1: Install and Run Ollama
This application acts as a client. It requires [Ollama](https://ollama.com/) running on your local machine.
1. Install Ollama.
2. Pull the default model. In your terminal run:
   ```bash
   ollama run llama3
   ```
3. Ensure Ollama is running in the background. The app communicates with `http://localhost:11434/api/generate`.

### Step 2: Build and Run
This project is built using Maven.

**Windows:**
```cmd
run.bat
```

**Linux/Mac:**
```bash
chmod +x run.sh
./run.sh
```

### 🧠 Usage Flow
1. Open the application.
2. Choose a persona/mode from the left Sidebar (e.g., *Coding Assistant*).
3. Type a message freely, OR pick a prompt structure from the right-hand Template Library.
4. Hit **Send** and watch the AI process the response!
