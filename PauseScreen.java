import greenfoot.*;

public class PauseScreen extends World {
    private static final int BUTTON_X = 300; // X coordinate for the buttons
    private static final int BUTTON_Y = 300; // Y coordinate for the buttons

    private Game gameWorld; // Reference to the game world to resume the game
    private MenuScreen menuScreen; // Reference to the menu screen to navigate back

    private boolean fromSettings; // Flag to check if we came from the settings screen

    /**
     * Constructor for the class, sets up all the variables.
     */
    public PauseScreen(Game gameWorld, MenuScreen menuScreen) {
        super(600, 750, 1); // Set world size to 600x750 pixels with a cell size of 1x1.

        this.gameWorld = gameWorld; // Initialize game world reference
        this.menuScreen = menuScreen; // Initialize menu screen reference
        
        fromSettings = false; // Initialize the fromSettings flag (false by default)

        setupButtons(); // Setup the buttons on the pause screen
        addLabels(); // Add labels to the screen
    }

    /**
     * Checks if the escape key is pressed and handles the pause logic.
     */
    public void act() {
        Util.PausehandleEscapeKey(this, gameWorld); // Handle escape key press to return to the previous world
    }
    
    /**
     * Adds the labels to the pause screen.
     */
    private void addLabels() {
        addObject(new Label("ESC", 30), 40, 700); // Label to indicate ESC key action
        addObject(new Label("Back", 25), 100, 700); // Label to indicate back option
    }

    /**
     * Adds the buttons to the pause screen.
     */
    private void setupButtons() {
        addObject(new Button(this::goSettingsScreen, "Settings"), BUTTON_X, BUTTON_Y); // Settings button
        addObject(new Button(this::goMenuScreen, "Quit Game"), BUTTON_X, BUTTON_Y + 50); // Quit Game button
    }

    /**
     * Switches the world to the settings screen.
     */
    private void goSettingsScreen() {
        fromSettings = true; // Set the flag indicating we're coming from the settings button
        Greenfoot.setWorld(new Settings(menuScreen, this)); // Switch to the settings screen, passing current menu and pause screen
    }

    /**
     * Switches the world to the menu screen (to quit the game).
     */
    private void goMenuScreen() {
        fromSettings = false; // Set the flag indicating we're coming from the quit button
        CharacterSelection characterSelection = new CharacterSelection(menuScreen); // Create character selection instance
        characterSelection.resetScales(); // Reset scales in character selection
        menuScreen.menuMusic.playLoop(); // Start playing menu music again
        Greenfoot.setWorld(menuScreen); // Switch to the menu screen
    }

    /**
     * Checks if the previous world was the settings screen.
     */
    public boolean isFromSettings() {
        return fromSettings; // Getter method to check if we came from the settings button
    }
    
    /**
     * Resumes the game and switches back to the game world.
     */
    public void resumeGame() {
        gameWorld.resumeGame(); // Resume the game logic
        Greenfoot.setWorld(gameWorld); // Switch back to the game world
    }
}
