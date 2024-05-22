import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class OpenAITextGenerator {
    private static final String OPENAI_API_KEY = "YOUR_OPENAI_API_KEY";
    private static final String OPENAI_API_URL = "https://api.openai.com/v1/completions";

    private List<String> chatHistory;

    public OpenAITextGenerator() {
        this.chatHistory = new ArrayList<>();
    }

    public String generateResponse(String humanInput) {
        chatHistory.add("Human: " + humanInput);
        String prompt = createPrompt(humanInput);

        HttpURLConnection connection = null;
        try {
            URL url = new URL(OPENAI_API_URL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Bearer " + OPENAI_API_KEY);
            connection.setDoOutput(true);

            JSONObject requestBody = new JSONObject();
            requestBody.put("model", "text-davinci-003");
            requestBody.put("prompt", prompt);
            requestBody.put("max_tokens", 150);
            requestBody.put("temperature", 0.7);

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = requestBody.toString().getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int status = connection.getResponseCode();
            if (status != 200) {
                try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getErrorStream(), "utf-8"))) {
                    StringBuilder response = new StringBuilder();
                    String responseLine;
                    while ((responseLine = br.readLine()) != null) {
                        response.append(responseLine.trim());
                    }
                    System.err.println("Error response from API: " + response.toString());
                }
                return "An error occurred while generating the AI response.";
            }

            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }

                String aiResponse = parseResponse(response.toString());
                chatHistory.add("AI: " + aiResponse);
                return aiResponse;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "An error occurred while generating the AI response.";
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    private String createPrompt(String humanInput) {
        StringBuilder historyBuilder = new StringBuilder();
        for (String entry : chatHistory) {
            historyBuilder.append(entry).append("\n");
        }

        return """
                You are now the guide of a mystical journey in the Whispering Woods.
                A traveler named Elara seeks the lost Gem of Serenity.
                You must navigate her through challenges, choices, and consequences,
                dynamically adapting the tale based on the traveler's decisions.
                Your goal is to create a branching narrative experience where each choice
                leads to a new path, ultimately determining Elara's fate.

                Here are some rules to follow:
                1. Start by asking the player to choose some kind of weapons that will be used later in the game
                2. Have a few paths that lead to success
                3. Have some paths that lead to death. If the user dies generate a response that explains the death and ends in the text: "The End.", I will search for this text to end the game

                Here is the chat history, use this to understand what to say next:
                """ + historyBuilder.toString() + "Human: " + humanInput + "\nAI:";
    }

    private static String parseResponse(String jsonResponse) {
        JSONObject jsonObject = new JSONObject(jsonResponse);
        JSONArray choices = jsonObject.getJSONArray("choices");
        return choices.getJSONObject(0).getString("text").trim();
    }
}
