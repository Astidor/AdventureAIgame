import java.util.Scanner;

public class aitext {
    public static void main(String[] args) {
        // Initialize game components
        Player player = new Player();
        Room currentRoom = createStartingRoom();
        Scanner scanner = new Scanner(System.in);
        OpenAITextGenerator aiGenerator = new OpenAITextGenerator();

        // Game loop
        while (!player.isDead()) {
            // Display current room description
            System.out.println(currentRoom.getDescription());

            // Display available exits
            System.out.println("Exits: " + currentRoom.getExits().keySet());

            // Prompt for user input
            System.out.print("What will you do? ");
            String userInput = scanner.nextLine().toLowerCase();

            // Generate AI response based on user input
            String aiResponse = aiGenerator.generateResponse("You are in a room. " + userInput);

            // Print AI response
            System.out.println(aiResponse);

            // Check for game over condition
            if (aiResponse.contains("The End.")) {
                player.die();
            }

            // Move to another room if applicable
            if (currentRoom.getExits().containsKey(userInput)) {
                currentRoom = currentRoom.getExits().get(userInput);
            }
        }

        // Game over
        System.out.println("Game over. Thanks for playing!");
    }

    private static Room createStartingRoom() {
        Room startingRoom = new Room("You are in a small, dimly lit room. There is a door to the north.");
        Room northRoom = new Room("You are in a large, brightly lit hall. There are doors to the south and east.");
        startingRoom.setExit("north", northRoom);
        northRoom.setExit("south", startingRoom);
        return startingRoom;
    }
}
