import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

public class HighScore extends World {
    private MenuScreen menuScreen;
    private static final int MAX_SCORES = 5; // Maximum number of scores that are displayed

    /**
     * Constructor for the HighScore class. Initializes the world and displays the high scores.
     * @param menuScreen The MenuScreen object to return to when needed.
     */
    public HighScore(MenuScreen menuScreen) {
        super(600, 750, 1); // Set the world size to 600x750 with a cell size of 1x1 pixels.
        GreenfootImage background = new GreenfootImage("CharacterSelection.jpg");
        background.scale(getWidth(), getHeight()); // Scale the background to fit the world size.
        setBackground(background); // Set the background image.

        this.menuScreen = menuScreen; // Store the reference to the MenuScreen.

        displayHighScores(); // Display the high scores on the screen.

        addLabels(); // Add ESC and Back labels for user navigation.
    }

    /**
     * Handles actions performed in the world. Specifically checks for the ESC key press.
     */
    public void act() {
        // Util.handleEscapeKey checks for ESC key press and switches to the menu screen if pressed.
        Util.handleEscapeKey(this, menuScreen);
    }

    /**
     * Adds the ESC and Back labels to the screen for user navigation.
     */
    private void addLabels() {
        addObject(new Label("ESC", 30), 40, 725); // Add ESC label at the bottom left.
        addObject(new Label("Back", 25), 100, 725); // Add Back label next to the ESC label.
    }

    /**
     * Displays the high scores on the screen by sorting and listing them.
     */
    public void displayHighScores() {
        int yint = 150; // Initial y-coordinate for displaying scores.
        int Header_FontSize = 30; // Font size for the headers (Rank, Name, Score).
        int Label_FontSize = 30; // Font size for the score labels.
        int rank = 1; // Initial rank number.

        // Get and sort the top scores from VictScreen.UserNames.
        List<NameScore> sortedScores = getSortTopScore();

        // Create and display the leaderboard title.
        Label title = new Label("LEADERBOARD", 50);
        addObject(title, getWidth() / 2, 70); // Center the title at the top.

        // Create and display headers for Rank, Name, and Score.
        Label headerRank = new Label("Rank", Header_FontSize);
        Label headerName = new Label("Name", Header_FontSize);
        Label headerScore = new Label("Score", Header_FontSize);

        addObject(headerRank, 150, yint); // Position the Rank header.
        addObject(headerName, 300, yint); // Position the Name header.
        addObject(headerScore, 450, yint); // Position the Score header.

        yint += 50; // Move down for the first entry.

        // Loop through the sorted scores and display each entry.
        for (NameScore name : sortedScores) {
            Label rankLabel = new Label(rank, Label_FontSize);
            Label nameLabel = new Label(name.getName(), Label_FontSize);
            Label scoreLabel = new Label(String.valueOf(name.getScores()), Label_FontSize);

            addObject(rankLabel, 150, yint); // Position the rank label.
            addObject(nameLabel, 300, yint); // Position the name label.
            addObject(scoreLabel, 450, yint); // Position the score label.

            rank++; // Increment the rank.
            yint += 50; // Move down for the next entry.
        }
    }

    /**
     * Retrieves and sorts the top scores from VictScreen.UserNames.
     * @return A list of the top sorted NameScore entries.
     */
    private List<NameScore> getSortTopScore() {
        // Copy the scores from VictScreen.UserNames to a new list.
        List<NameScore> score = new ArrayList<>(VictScreen.UserNames);

        // Sort the list based on scores in descending order.
        score.sort((a, b) -> Integer.compare(b.getScores(), a.getScores()));

        // Limit the list to the top `MAX_SCORES` entries.
        if (score.size() > MAX_SCORES) {
            score = score.subList(0, MAX_SCORES);
        }

        return score; // Return the sorted list.
    }

    /**
     * Transitions to the menu screen and handles any necessary cleanup.
     */
    public void goMenuScreen() {
        Greenfoot.setWorld(menuScreen); // Switch to the menu screen.
    }
}
