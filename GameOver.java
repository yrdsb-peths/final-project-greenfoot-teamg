import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * GameOver class represents the game over screen that appears when the player
 * dies.
 * It handles displaying the final score, getting player name input, and
 * managing high scores.
 */
public class GameOver extends World {
    // References to other game worlds
    private MenuScreen menuScreen;

    // UI and game state variables
    private String ending = "GameOver"; // Text displayed at game over

    private GreenfootSound GameOverMusic;
    private GreenfootSound YouLose;

    private AudioManager audioManager;

    public GameOver() {
        super(600, 750, 1);
        menuScreen = new MenuScreen();
        setBackground(new GreenfootImage("Background.jpg"));

        audioManager = AudioManager.getInstance();

        DisplayGameOver();

        // Initialize and play sound effects
        GameOverMusic = new GreenfootSound("GameOverMusic.mp3");
        YouLose = new GreenfootSound("YouLose.mp3");
        GameOverMusic.playLoop();
        YouLose.play();
    }

    public void act() {
        runEnter();
        updateMusic();
    }

    /**
     * Sets up the visual elements of the game over screen.
     * Displays final score and prompts for name input.
     */
    public void DisplayGameOver() {
        Label end = new Label(ending, 60);
        addObject(end, 300, 70);

        Label enter = new Label("Press Enter to Continue", 35);
        addObject(enter, 300, 600);

    }

    /**
     * Handles player name input.
     * Processes keyboard input for name entry and manages input validation.
     * When enter is pressed, saves the score and returns to menu.
     */
    public void runEnter() {
        String key = Greenfoot.getKey();
        if (key != null) {
            if (key.equals("enter")) {
                goMenuScreen();
            }
        }
    }


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

    /**
     * Updates the music based on the current volume settings from AudioManager.
     */
    private void updateMusic() {
        int effectiveVolume = audioManager.getEffectiveVolume();
        GameOverMusic.setVolume(effectiveVolume);
    }
}
