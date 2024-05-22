---

## Text-Based Adventure Game with AI-Powered Storytelling

This Java-based text adventure game leverages OpenAI's GPT-3.5 API to dynamically generate narrative content, providing an engaging and adaptive storytelling experience. Players navigate through a series of choices that impact the direction and outcome of the story, with the AI guiding the narrative based on player decisions.

### Features
- **Dynamic Storytelling**: The game uses OpenAI's GPT-3.5 to generate narrative responses based on player input, creating a unique story each time.
- **Branching Narratives**: Player choices lead to different paths, including potential successes and deadly outcomes.
- **Interactive Gameplay**: Players interact with the game by typing commands and making choices that influence the storyline.
- **Persistent Chat History**: The game maintains a chat history to provide context for the AI, ensuring coherent and contextually relevant responses.

### How It Works
1. **Initialization**: The game sets up a starting room and initializes the player and AI components.
2. **Game Loop**: Players are prompted to make choices, and the AI generates responses based on these inputs.
3. **Dynamic Responses**: The AI uses a predefined template to guide the narrative, incorporating player decisions into the unfolding story.
4. **End Conditions**: The game continues until the player encounters an outcome that ends the story (e.g., death), signaled by the phrase "The End."

### Project Structure
- `OpenAITextGenerator.java`: Handles communication with the OpenAI API, including constructing prompts and parsing responses.
- `Player.java`: Represents the player, tracking their state (alive or dead).
- `Room.java`: Represents rooms in the game, each with a description and potential exits.
- `aitext.java`: The main class that runs the game loop, handles player input, and manages game state.

### Prerequisites
- **Java 8 or higher**
- **Maven**: For dependency management and project building
- **OpenAI API Key**: Required to access OpenAI's GPT-3.5 API

### Setup and Running the Game
1. **Clone the Repository**:
   ```sh
   git clone https://github.com/your-username/AdventureAIgame.git
   cd AdventureAIgame
   ```

2. **Configure the API Key**:
   - Replace `"YOUR_OPENAI_API_KEY"` in `OpenAITextGenerator.java` with your actual OpenAI API key.

3. **Build the Project**:
   ```sh
   mvn compile
   ```

### Example Gameplay
```
You are in a small, dimly lit room. There is a door to the north.
Exits: [north]
What will you do? 

> go north

AI: As Elara steps through the door to the north, she finds herself in a large, brightly lit hall. There are doors to the south and east. She must choose a weapon for the journey ahead. Does she choose a sword, a bow, or a staff?

> choose sword

AI: Elara picks up the sword, feeling its weight in her hands. She knows it will be a reliable companion in the trials to come. She looks around the hall and decides to move east.

Exits: [south, east]
What will you do?

> go east

AI: Elara moves east and enters a shadowy forest glade. The trees are tall and their leaves whisper in the wind. Ahead, she sees a path that splits into two directions: one leading to a dark cave and the other towards a sparkling stream. Which path will she choose?

> go to the cave

AI: Elara bravely steps towards the dark cave. As she enters, she hears the sound of dripping water and the fluttering of bats. Suddenly, she is confronted by a pair of glowing eyes in the darkness. It's a giant spider guarding its nest. Elara must fight for her life!

What will you do?

> fight spider

AI: Elara swings her sword at the spider, but it's quick and agile. The battle is fierce, and Elara manages to land a few good hits. Finally, she delivers a fatal blow to the spider, but not without sustaining some injuries. She takes a moment to catch her breath and bandage her wounds.

She can either explore deeper into the cave or return to the glade. What will she do?

> explore deeper

AI: Gathering her courage, Elara ventures deeper into the cave. The air grows colder and the passage narrower. She finds a small chamber with an ancient chest in the center. As she opens it, she finds the Gem of Serenity, glowing with an ethereal light. Elara has found what she was seeking!

Congratulations! You have successfully guided Elara to find the Gem of Serenity. Your journey doesn't end here, as there are more adventures to explore.

Exits: [glade]
What will you do?

> go back to glade

AI: Elara retraces her steps and exits the cave, returning to the glade. With the Gem of Serenity in her possession, she feels a sense of accomplishment and readiness for whatever challenges may lie ahead. The Whispering Woods seem less daunting now.

The End.
```

### Contributions
Contributions are welcome! Please open an issue or submit a pull request with any improvements or bug fixes.

---
