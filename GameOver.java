import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * GameOver class represents the game over screen that appears when the player
 * dies.
 * It handles displaying the final score, getting player name input, and
 * managing high scores.
 */
public class GameOver extends World {
    // Maximum number of high scores to keep
    private static final int MAX_SCORES = 5;

    // Static ArrayList to store player names and scores across game sessions
    public static ArrayList<NameScore> UserNames = new ArrayList<>();

    // References to other game worlds
    private MenuScreen menuScreen;

    // UI and game state variables
    private String ending = "GameOver"; // Text displayed at game over
    private String userName = ""; // Stores user's input name
    private Label input; // Label to display user's input
    private boolean inputAccepted = true; // Flag to control input acceptance
    private int finalScore; // Player's final score

    // Sound effects for game over screen
    GreenfootSound GameOverMusic; // Background music
    GreenfootSound YouLose; // Sound played when entering game over screen

    public GameOver() {
        super(600, 750, 1);
        this.finalScore = finalScore;
        menuScreen = new MenuScreen();
        setBackground(new GreenfootImage("Background.jpg"));

        DisplayGameOver();

        // Initialize and play sound effects
        GameOverMusic = new GreenfootSound("GameOverMusic.mp3");
        YouLose = new GreenfootSound("YouLose.mp3");
        GameOverMusic.playLoop();
        YouLose.play();
    }

    public void act() {
        requestName();
    }

    /**
     * Sets up the visual elements of the game over screen.
     * Displays final score and prompts for name input.
     */
    public void DisplayGameOver() {
        Label end = new Label(ending, 60);
        addObject(end, 300, 70);

        Label scoreDisplay = new Label("Final Score: " + finalScore, 35);
        addObject(scoreDisplay, 300, 120);

        Label name = new Label("Enter your name: ", 40);
        addObject(name, 300, 200);

        Label enter = new Label("Press Enter to Continue", 35);
        addObject(enter, 300, 600);

        input = new Label("", 40);
        addObject(input, 300, 250);
    }

    /**
     * Handles player name input.
     * Processes keyboard input for name entry and manages input validation.
     * When enter is pressed, saves the score and returns to menu.
     */
    public void requestName() {
        if (!inputAccepted)
            return;

        String key = Greenfoot.getKey();
        if (key != null) {
            if (key.equals("enter") && !userName.trim().isEmpty()) {
                // Create new score entry and add to high scores
                NameScore playerInfo = new NameScore(userName, finalScore);
                addHighScore(playerInfo);
                goMenuScreen();

                inputAccepted = false;
            } else if (key.equals("backspace")) {
                // Handle backspace for name input
                if (userName.length() > 0) {
                    userName = userName.substring(0, userName.length() - 1);
                }
            } else if (key.length() == 1 && userName.length() < 20) { // 20 character limit
                // Handle regular character input, with shift key support
                if (Greenfoot.isKeyDown("shift")) {
                    key = key.toUpperCase();
                }
                userName += key;
            }
            input.setValue(userName);
        }
    }

    /**
     * Adds a new high score to the list and maintains the maximum number of scores.
     * Sorts scores in descending order and keeps only the top MAX_SCORES entries.
     */
    private void addHighScore(NameScore newScore) {
        UserNames.add(newScore);
        Collections.sort(UserNames); // Sort in descending order

        // Keep only top scores based on MAX_SCORES
        if (UserNames.size() > MAX_SCORES) {
            UserNames = new ArrayList<>(UserNames.subList(0, MAX_SCORES));
        }
    }

    /**
     * Transitions to the menu screen and handles music cleanup.
     */
    public void goMenuScreen() {
        menuScreen.started();
        GameOverMusic.stop();
        YouLose.stop();
        Greenfoot.setWorld(menuScreen);
    }

    /**
     * Called when the world is started/resumed.
     * Ensures background music continues playing.
     */
    public void started() {
        GameOverMusic.playLoop();
    }

    /**
     * Called when the world is stopped/paused.
     * Pauses all sound effects.
     */
    public void stopped() {
        YouLose.pause();
        GameOverMusic.pause();
    }
}