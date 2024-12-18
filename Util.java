import greenfoot.Greenfoot;
import greenfoot.World;

public class Util {
  private static boolean checker = true;

  public static void handleEscapeKey(World currentScreen, World targetScreen) {
    // Continuously check for "escape" key press to return to the menu screen
    if (Greenfoot.isKeyDown("escape") && checker != true) {
      checker = true;
      Greenfoot.setWorld(targetScreen);
    } else if (!Greenfoot.isKeyDown("escape")) {
      checker = false;
    }
  }

  public static void handleEnterKey(World currentScreen, World targetScreen) {
    checker = true;

    if (Greenfoot.isKeyDown("enter") && checker && targetScreen != null) {
      Greenfoot.setWorld(targetScreen);
      checker = false;
    } else if (!Greenfoot.isKeyDown("enter")) {
      checker = true;
    }
  }
}
