import greenfoot.*;

public class PauseScreen extends World {
    private static final int BUTTON_X = 250;
    private static final int BUTTON_Y = 300;

    private Game gameWorld;
    private MenuScreen menuScreen;

    private boolean checker;
    private boolean enterPressed;
    private boolean fromSettings;

    public PauseScreen(Game gameWorld, MenuScreen menuScreen) {
        super(500, 750, 1);

        this.gameWorld = gameWorld;
        this.menuScreen = menuScreen;

        checker = true;
        enterPressed = false;
        fromSettings = false; // Initialize the fromSettings flag

        setupButtons();
        addLabels();
    }

    public void act() {
        Util.PausehandleEscapeKey(this, gameWorld);
    }

    private void addLabels() {
        addObject(new Label("ESC", 30), 40, 700);
        addObject(new Label("Back", 25), 100, 700);
    }

    private void setupButtons() {
        addObject(new Button(this::goSettingsScreen, "Settings"), BUTTON_X, BUTTON_Y);
        addObject(new Button(this::goMenuScreen, "Quit Game"), BUTTON_X, BUTTON_Y + 50);
    }

    private void goSettingsScreen() {
        fromSettings = true; // Set the flag indicating we're coming from the settings button
        Greenfoot.setWorld(new Settings(menuScreen, this)); // Pass the PauseScreen instance
    }

    private void goMenuScreen() {
        fromSettings = false; // Set the flag indicating we're coming from the quit button
        Greenfoot.setWorld(menuScreen);
    }

    public boolean isFromSettings() {
        return fromSettings; // Getter method to check if we came from the settings button
    }
    
    public void resumeGame() {
        gameWorld.resumeGame(); // Resume the game when returning from the PauseScreen
        Greenfoot.setWorld(gameWorld); // Return to the game world
    }
}
