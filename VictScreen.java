import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

public class VictScreen extends World {
    private static final int MIN_SCORES = 5;  // Limit of top scores
    // Static ArrayList to store player names and scores across game sessions
    public static ArrayList<NameScore> UserNames = new ArrayList<>();

    private MenuScreen menuScreen;
    private String userName = "";  // Store user's input name
    private int finalTime;  // Player's final time
    private SimpleTimer finalScore;
    private Label input;
    private boolean inputAccepted = true;  // Flag to control input acceptance
    private GreenfootSound victoryMusic;
    private AudioManager audioManager;

    /**
     * Constructor for victory screen, sets up all the variables.
     */
    public VictScreen(SimpleTimer levelTimer, MenuScreen menuScreen) {
        super(600, 750, 1);
        finalScore = levelTimer;
        this.menuScreen = menuScreen;
        if (finalScore != null) {
            finalTime = finalScore.millisElapsed();
        }
        setBackground(new GreenfootImage("Background.jpg"));
        displayVictoryScreen();
        audioManager = AudioManager.getInstance();
        victoryMusic = new GreenfootSound("victorySong.mp3");
        victoryMusic.playLoop();
        updateMusic();
    }

    /**
     * check if final score exists, otherwise checks if enter is pressed then goes to menu screen.
     */
    public void act() {
        if (finalScore != null) {
            requestName();
        } else {
            if (Greenfoot.isKeyDown("enter")) {
                stopped();
                goMenuScreen();
            }
        }
    }

    /**
     * displaying the victory screen and asking for name
     */
    private void displayVictoryScreen() {
        Label end = new Label("You Win!", 60);
        addObject(end, 300, 70);
        if (finalScore != null) {
            String scoreMessage = "Final Time: " + finalTime / 1000 + "s";
            Label scoreDisplay = new Label(scoreMessage, 35);
            addObject(scoreDisplay, 300, 120);

            Label name = new Label("Enter your name: ", 40);
            addObject(name, 300, 200);
        }

        Label enter = new Label("Press Enter to Continue", 35);
        addObject(enter, 300, 600);

        input = new Label("", 40);
        addObject(input, 300, 250);
    }
    
    /**
     * name request checks for name and give to high score to calculate leaderboard 
     */
    public void requestName() {
        if (!inputAccepted)
            return;

        String key = Greenfoot.getKey();  // Get the key pressed
        if (key != null) {
            if (key.equals("enter") && !userName.trim().isEmpty()) {
                // Create a new score entry with player name and final score
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
     * Sorts scores in ascending order (least time is ranked higher).
     */
    private void addHighScore(NameScore newScore) {
        UserNames.add(newScore);  // Add new score to the list
        
        // Sort the list by score in ascending order (least time first)
        Collections.sort(UserNames, (a, b) -> Integer.compare(a.getScores(), b.getScores()));

        // Limit the list to only the top `MIN_SCORES` entries
        if (UserNames.size() > MIN_SCORES) {
            UserNames = new ArrayList<>(UserNames.subList(0, MIN_SCORES));
        }
    }
    

    /**
     * Switches to menu screen
     */
    public void goMenuScreen() {
        Greenfoot.setWorld(menuScreen);  // Transition to the menu screen
    }
    
    /**
     * If pause is pressed, stop all music.
     */
    public void stopped()
    {
        victoryMusic.pause();
    }
    
    /**
     * If start is pressed, continue playing all music.
     */
    public void started()
    {
        victoryMusic.playLoop();
    }
    
    /**
     * Updates the music based on the current volume settings from AudioManager.
     */
    private void updateMusic() {
        int effectiveVolume = audioManager.getEffectiveVolume();
        victoryMusic.setVolume(effectiveVolume);
    }
}
