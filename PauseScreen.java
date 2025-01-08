import greenfoot.*;

public class PauseScreen extends World {
  private static final int BUTTON_X = 250;
  private static final int BUTTON_Y = 300;

  private Game gameWorld; // Change to Game instead of MenuScreen
  private MenuScreen menuScreen;

  private boolean checker;

  public PauseScreen(Game gameWorld, MenuScreen menuScreen) {
    super(500, 750, 1);

    // Initialize worlds
    this.gameWorld = gameWorld;
    this.menuScreen = menuScreen;
    
    checker = true;

    setupButtons();
    addLabels();
  }

  public void act() {
    Util.handleEscapeKey(this, gameWorld);
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
    Greenfoot.setWorld(new Settings(menuScreen));
  }

  private void goMenuScreen() {
    Greenfoot.setWorld(menuScreen);
  }
}
