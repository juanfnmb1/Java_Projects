
import java.util.Scanner;

public class RandomStoryGenerator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StoryGenerator storyGenerator = new StoryGenerator();

        while (true) {
            // Ask the user what type of story they want to hear
            System.out.println("Would you like to hear a story? (scary, funny, romantic, or none to exit)");
            String choice = scanner.nextLine().toLowerCase();

            if (choice.equals("none")) {
                System.out.println("Goodbye!");
                break; // Exit the program if the user selects 'none'
            }

            // Call the appropriate method based on the user's choice
            if (choice.equals("scary")) {
                System.out.println("Here is your scary story: ");
                System.out.println(storyGenerator.getRandomScaryStory());
            } else if (choice.equals("funny")) {
                System.out.println("Here is your funny story: ");
                System.out.println(storyGenerator.getRandomFunnyStory());
            } else if (choice.equals("romantic")) {
                System.out.println("Here is your romantic story: ");
                System.out.println(storyGenerator.getRandomRomanticStory());
            } else {
                System.out.println("Invalid choice. Please enter 'scary', 'funny', 'romantic', or 'none'.");
                continue; // Skip to the next iteration if invalid input
            }

            // Ask if they want another story
            System.out.println("Do you want to hear another story? (yes/no)");
            String anotherStory = scanner.nextLine().toLowerCase();

            if (anotherStory.equals("no")) {
                System.out.println("Goodbye!");
                break; // Exit if the user says 'no'
            }
        }

        scanner.close(); // Close the scanner
    }
}
