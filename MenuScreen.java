import greenfoot.*;

public class MenuScreen extends World {
    public static boolean enterChecker = true; // Tracks the state of the Enter key
    private Button settingButton; // Button for settings screen
    private Button leaderboardButton; // Button for leaderboard screen
    private Button instructionsButton; // Button for instructions screen
    private CharacterSelection characterSelection; // Reference to the character selection screen
    private boolean checker; // Additional state checker
    GreenfootSound menuMusic; // Background music for the menu
    private AudioManager audioManager; // Audio manager for handling music volume

    /**
     * Constructor for the menu screen, sets up every variable.
     */
    public MenuScreen() {
        super(600, 750, 1); // Set the world size to 600x750 with a cell size of 1x1 pixels.
        GreenfootImage background = new GreenfootImage("CharacterSelection.jpg");
        background.scale(getWidth(), getHeight()); // Scale the background to fit the world size.
        setBackground(background); // Set the background image.

        checker = true; // Initialize checker state
        audioManager = AudioManager.getInstance(); // Get the singleton instance of AudioManager
        setupButtons(); // Setup all the buttons
        addLabels(); // Add labels to the screen

        // Initialize characterSelection screen
        characterSelection = new CharacterSelection(this);

        // Initialize the background music
        menuMusic = new GreenfootSound("Menu.mp3");
        updateMusic(); // Update the music volume based on current settings
    }

    /**
     * Updates the music volume and checks if the Enter key is pressed.
     */
    public void act() {
        handleEnterKey(); // Check for Enter key press to start the game
        updateMusic(); // Update the music volume continuously
    }

    /**
     * Sets up the buttons at the top right of the screen.
     */
    private void setupButtons() {
        // Setting button
        settingButton = new Button(this::goSettingScreen, "");
        settingButton.changeButtonImage("setting.png", 70, 70); // Set button image and size
        addObject(settingButton, 550, 40); // Position the button

        // Leaderboard button
        leaderboardButton = new Button(this::goHighScoresScreen, "");
        leaderboardButton.changeButtonImage("leaderboard.png", 90, 90); // Set button image and size
        addObject(leaderboardButton, 550, 115); // Position the button

        // Instructions button
        instructionsButton = new Button(this::goInstructionsScreen, "");
        instructionsButton.changeButtonImage("questionMark.png", 95, 95); // Set button image and size
        addObject(instructionsButton, 550, 190); // Position the button
    }
    
    /**
     * Adds the labels to the menu screen.
     */
    private void addLabels() {
        addObject(new Label("Space Fighters: \n Bullet Hell", 70), getWidth() / 2, 100); // Main title
        addObject(new Label("Press Enter to Start", 50), getWidth() / 2, getHeight() / 2); // Start prompt
        addObject(new Label("(New Players click on question mark before starting)", 30), getWidth() / 2, getHeight() / 2 + 50); // Instruction for new players
    }

    /**
     * Checks if the Enter key is pressed to switch to the CharacterSelection world.
     */
    private void handleEnterKey() {
        if (Greenfoot.isKeyDown("enter") && !enterChecker) {
            enterChecker = true; // Prevent repeated triggering
            gocharacterselection(); // Switch to character selection
        } else if (!Greenfoot.isKeyDown("enter") && enterChecker) {
            enterChecker = false; // Reset the checker when Enter is released
        }
    }

    /**
     * Switches to the InstructionsScreen world.
     */
    private void goInstructionsScreen() {
        Greenfoot.setWorld(new InstructionScreen(this)); // Navigate to instructions
    }

    /**
     * Switches to the SettingScreen world.
     */
    private void goSettingScreen() {
        Greenfoot.setWorld(new Settings(this, null)); // Navigate to settings with current MenuScreen instance
    }

    /**
     * Switches to the CharacterSelection world.
     */
    private void gocharacterselection() {
        Greenfoot.setWorld(characterSelection); // Navigate to character selection
    }

    /**
     * Switches to the HighScoresScreen world.
     */
    private void goHighScoresScreen() {
        Greenfoot.setWorld(new HighScore(this)); // Navigate to high scores
    }
    
    /**
     * Starts the background music when the world is started.
     */
    public void started() {
        menuMusic.playLoop(); // Play music in a loop
    }
    
    /**
     * Stops the background music when the world is stopped.
     */
    public void stopped() {
        menuMusic.pause(); // Pause the music
    }

    /**
     * Updates the volume of the background music based on the current audio settings.
     */
    private void updateMusic() {
        int effectiveVolume = audioManager.getEffectiveVolume(); // Get the current volume setting
        menuMusic.setVolume(effectiveVolume); // Set the music volume
    }
}
