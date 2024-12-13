import greenfoot.*;

public class MenuSubscreen extends Actor {
  private MenuScreen menuscreen;
  private SettingScreen settingScreen;
  private int screenWidth;
  private int screenHeight;

  private Button settingButton;

  public MenuSubscreen(MenuScreen menuscreen) {
    screenHeight = menuscreen.getHeight(); 
    screenWidth = menuscreen.getWidth();

    // Create a new image for 1/3 of the screen width
    GreenfootImage settingsImage = new GreenfootImage(screenWidth / 3, screenHeight);
    settingsImage.setColor(Color.BLACK);
    settingsImage.setTransparency(120); // add some transparency
    settingsImage.fill();

    setImage(settingsImage);
    this.menuscreen = menuscreen;
    settingButton = new Button(this::goSettingScreen, "Settings");
  }

  public void act() {
    if (Greenfoot.mouseClicked(this)) {
      menuscreen.toggleSettings();
    }
  }

  /**
   * Transitions to the setting screen and handles music cleanup.
   */
  public void goSettingScreen() {
    Greenfoot.setWorld(settingScreen);
  }
}
