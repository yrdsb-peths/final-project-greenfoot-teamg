import greenfoot.*;

public class PauseScreen extends World {
    private static final int BUTTON_X = 300;
    private static final int BUTTON_Y = 300;

    private Game gameWorld;
    private MenuScreen menuScreen;

    private boolean fromSettings;

    /**
     * Constructor for the class, sets up all the variables.
     */
    public PauseScreen(Game gameWorld, MenuScreen menuScreen) {
        super(600, 750, 1);

        this.gameWorld = gameWorld;
        this.menuScreen = menuScreen;
        
        fromSettings = false; // Initialize the fromSettings flag

        setupButtons();
        addLabels();
    }

    /**
     * Checks if escape key is pressed.
     */
    public void act() {
        Util.PausehandleEscapeKey(this, gameWorld);
    }
    
    /**
     * Adds the labels
     */
    private void addLabels() {
        addObject(new Label("ESC", 30), 40, 700);
        addObject(new Label("Back", 25), 100, 700);
    }

    /**
     * Adds the buttons
     */
    private void setupButtons() {
        addObject(new Button(this::goSettingsScreen, "Settings"), BUTTON_X, BUTTON_Y);
        addObject(new Button(this::goMenuScreen, "Quit Game"), BUTTON_X, BUTTON_Y + 50);
    }

    /**
     * Switches world to setting screen
     */
    private void goSettingsScreen() {
        fromSettings = true; // Set the flag indicating we're coming from the settings button
        Greenfoot.setWorld(new Settings(menuScreen, this)); // Pass the PauseScreen instance
    }

    /**
     * Switches world to menu screen
     */
    private void goMenuScreen() {
        fromSettings = false; // Set the flag indicating we're coming from the quit button
        CharacterSelection characterSelection = new CharacterSelection(menuScreen);
        characterSelection.resetScales();
        menuScreen.menuMusic.playLoop();
        Greenfoot.setWorld(menuScreen);
    }

    /**
     * Checks if previous world was setting screen
     */
    public boolean isFromSettings() {
        return fromSettings; // Getter method to check if we came from the settings button
    }
    
    /**
     * Resumes the game
     */
    public void resumeGame() {
        gameWorld.resumeGame(); // Resume the game when returning from the PauseScreen
        Greenfoot.setWorld(gameWorld); // Return to the game world
    }
}
