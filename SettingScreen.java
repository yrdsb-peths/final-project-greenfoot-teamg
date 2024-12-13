import greenfoot.*;
public class SettingScreen extends World{
  private MenuScreen menuScreen;

  public SettingScreen() {
    super(500, 700, 1);
  }


   /**
     * Transitions to the menu screen and handles music cleanup.
     */
    public void goMenuScreen() {
      Greenfoot.setWorld(menuScreen);
    }
}