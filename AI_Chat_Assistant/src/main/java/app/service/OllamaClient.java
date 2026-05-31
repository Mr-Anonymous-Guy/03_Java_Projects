package app.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class OllamaClient {
    private static final String OLLAMA_URL = "http://localhost:11434/api/generate";
    private static final String DEFAULT_MODEL = "llama3";
    private final HttpClient httpClient;
    private final Gson gson;

    public OllamaClient() {
        this.httpClient = HttpClient.newHttpClient();
        this.gson = new Gson();
    }

    public String generateResponse(String systemPrompt, String userPrompt) {
        try {
            JsonObject jsonRequest = new JsonObject();
            jsonRequest.addProperty("model", DEFAULT_MODEL);
            jsonRequest.addProperty("system", systemPrompt);
            jsonRequest.addProperty("prompt", userPrompt);
            jsonRequest.addProperty("stream", false);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(OLLAMA_URL))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonRequest.toString()))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                JsonObject jsonResponse = gson.fromJson(response.body(), JsonObject.class);
                return jsonResponse.get("response").getAsString();
            } else {
                return "Error: Ollama returned status code " + response.statusCode();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Connection Error: Ensure Ollama is running on localhost:11434.\nDetails: " + e.getMessage();
        }
    }
}
