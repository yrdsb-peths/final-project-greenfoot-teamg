import greenfoot.*;

public class pauseScreen extends World {
  private static final int BUTTON_X = 250;
  private static final int BUTTON_Y = 300;

  private MenuScreen menuScreen;

  private boolean checker;

  public pauseScreen(MenuScreen menuScreen) {
    super(500, 750, 1);

    // Initialize worlds
    this.menuScreen = menuScreen;

    checker = true;

    setupButtons(menuScreen);
    addLabels();
  }

  private void setupButtons(MenuScreen menuScreen) {
    addObject(new Button(this::goSettingsScreen, "Settings"), BUTTON_X, BUTTON_Y);
    addObject(new Button(this::goHighScoresScreen, "Leaderboard"), BUTTON_X, BUTTON_Y + 50);
  }

  private void addLabels() {
    addObject(new Label("ESC", 30), 40, 700);
    addObject(new Label("Back", 25), 100, 700);
  }

  public void act() {
    Util.handleEscapeKey(this, menuScreen);
  }

  /*private void handleEscapeKey() {
    // Continuously check for "escape" key press to return to the menu screen
    if (Greenfoot.isKeyDown("escape") && checker != true) {
      checker = true;
      goMenuScreen();
    } else if (!Greenfoot.isKeyDown("escape")) {
      checker = false;
    }
  }*/

  private void goSettingsScreen() {
    Greenfoot.setWorld(new Settings(menuScreen));
  }

  private void goHighScoresScreen() {
    Greenfoot.setWorld(new HighScore(menuScreen));
  }

  public void goMenuScreen() {
    menuScreen.checker = true;
    Greenfoot.setWorld(menuScreen);
  }
}
