import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * VictScreen class represents the victory screen that appears when the player
 * wins the game.
 * It handles displaying the final score, getting player name input, and
 * managing high scores.
 */
public class VictScreen extends World {
    // Maximum number of high scores to keep
    private static final int MAX_SCORES = 5;

    // Static ArrayList to store player names and scores across game sessions
    public static ArrayList<NameScore> UserNames = new ArrayList<>();

    // References to other game worlds
    private MenuScreen menuScreen;

    // UI and game state variables
    private String ending = "You Win!"; // Text displayed when player wins
    private String userName = ""; // Stores user's input name
    private Label input; // Label to display user's input
    private boolean inputAccepted = true; // Flag to control input acceptance
    private int finalTime; // Player's final time
    private SimpleTimer finalScore; //Players's final time as a  

    public VictScreen(SimpleTimer levelTimer, MenuScreen menuScreen) {
        super(600, 750, 1);
        finalScore = levelTimer;
        this.menuScreen = menuScreen;
        if(finalScore != null)
        {
            finalTime = finalScore.millisElapsed();
        }
        setBackground(new GreenfootImage("Background.jpg"));

        // Display victory screen elements
        displayVictoryScreen();
    }

    public void act() {
        // Request for player name input after victory
        if(finalScore != null)
        {
            requestName();
        }
        else
        {
            if(Greenfoot.isKeyDown("enter"))
            {
                goMenuScreen();
            }
        }
    }

    /**
     * Sets up the visual elements of the victory screen.
     * Displays final score and prompts for name input.
     */
    private void displayVictoryScreen() {
        Label end = new Label(ending, 60);  // Display "You Win!" message
        addObject(end, 300, 70);
        if(finalScore != null)
        {
            // Display the final score based on the elapsed time (in seconds)
            String scoreMessage = "Final Time: " + finalTime / 1000 + "s";
            Label scoreDisplay = new Label(scoreMessage, 35);  // Display final score
            addObject(scoreDisplay, 300, 120);
    
            // Add label prompting user for name input
            Label name = new Label("Enter your name: ", 40);  
            addObject(name, 300, 200);
        }

        // Instruction for continuing after entering the name
        Label enter = new Label("Press Enter to Continue", 35);  
        addObject(enter, 300, 600);

        input = new Label("", 40);  // Label to show the entered name
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

        String key = Greenfoot.getKey();  // Get the key pressed
        if (key != null) {
            if (key.equals("enter") && !userName.trim().isEmpty()) {
                // Create new score entry with player name and final score
                int scoreInSeconds = finalTime / 1000;  // Convert millis to seconds
                NameScore playerInfo = new NameScore(userName, scoreInSeconds);
                addHighScore(playerInfo);
                goMenuScreen();  // Transition to menu screen

                inputAccepted = false;  // Stop further input once the name is accepted
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
            input.setValue(userName);  // Update input label with the current name
        }
    }

    /**
     * Adds a new high score to the list and maintains the maximum number of scores.
     * Sorts scores in descending order and keeps only the top MAX_SCORES entries.
     */
    private void addHighScore(NameScore newScore) {
        UserNames.add(newScore);  // Add new score to the list
        Collections.sort(UserNames);  // Sort the leaderboard in acsending order

        // Keep only the top scores (up to MAX_SCORES)
        if (UserNames.size() > MAX_SCORES) {
            UserNames = new ArrayList<>(UserNames.subList(0, MAX_SCORES));
        }
    }

    /**
     * Transitions to the menu screen and handles cleanup.
     */
    public void goMenuScreen() {
        Greenfoot.setWorld(menuScreen);  // Transition to the menu screen
    }
}
