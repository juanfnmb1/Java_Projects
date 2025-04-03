import java.util.Random;

public class StoryGenerator {

    // Scary stories
    private String[] scaryStories = {
            "A dark figure appeared in the window, staring at me with empty eyes. I tried to scream, but no sound came out.",
            "I woke up in the middle of the night to the sound of whispers. I thought it was a dream, but the voices were too real.",
            "My phone rang, but no one was on the other end. Then, a voice whispered my name from the dark corner of the room."
    };

    // Funny stories
    private String[] funnyStories = {
            "I went to the grocery store and accidentally bought 10 bags of flour. Now I’m set for the apocalypse.",
            "I tried to make pancakes but ended up with a burnt mess that looked like a rock. The dog refused to even sniff it.",
            "I got a haircut that made me look like I stuck my finger in a socket. Even the mirror was shocked."
    };

    // Romantic stories
    private String[] romanticStories = {
            "Under the stars, they held hands, and for the first time, they knew everything would be okay.",
            "She looked into his eyes and realized that she had found her home, her peace, and her heart’s true desire.",
            "He wrote her a letter every day for a year, each one filled with promises of forever and love that never fades."
    };

    // Randomly selects a scary story
    public String getRandomScaryStory() {
        Random random = new Random();
        int index = random.nextInt(scaryStories.length);
        return scaryStories[index];
    }

    // Randomly selects a funny story
    public String getRandomFunnyStory() {
        Random random = new Random();
        int index = random.nextInt(funnyStories.length);
        return funnyStories[index];
    }

    // Randomly selects a romantic story
    public String getRandomRomanticStory() {
        Random random = new Random();
        int index = random.nextInt(romanticStories.length);
        return romanticStories[index];
    }
}