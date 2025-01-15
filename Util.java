import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;

public class Util {
  private static boolean checker = true;

  public static void handleEscapeKey(World currentScreen, World targetScreen) {
    // Continuously check for "escape" key press to return to the menu screen
    if (Greenfoot.isKeyDown("escape") && checker != true) {
      checker = true;
      if (currentScreen instanceof Game) {
            ((Game) currentScreen).freezeGame();
            ((Game) currentScreen).stopped();
      }
      
      Greenfoot.setWorld(targetScreen);
    } else if (!Greenfoot.isKeyDown("escape")) {
      checker = false;
    }
  }

  public static void PausehandleEscapeKey(World currentScreen, World targetScreen) {
    // Continuously check for "escape" key press to return to the target screen
    if (Greenfoot.isKeyDown("escape") && !checker) {
        checker = true;
        
        if (targetScreen instanceof Game) {
            ((Game) targetScreen).resumeGame();
            ((Game) targetScreen).started();
        }
        
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
  
  public static int randomInt(int max){
        Random rand = new Random();
        return rand.nextInt(max+1);
  }
  
  public static boolean randomBoolean(){
        Random rand = new Random();
        if(rand.nextInt(2) == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
  }
}
