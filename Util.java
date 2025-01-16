import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;

/**
* Utility class providing common functionality used across the game.
* Includes key handling, screen transitions, and random number generation.
*/
public class Util {
   // State flag for key press debouncing
   private static boolean checker = true;

   /**
    * Handles escape key press to return to a target screen.
    * Includes special handling for Game screen transitions.
    *
    * @param currentScreen The currently active screen
    * @param targetScreen The screen to transition to
    */
   public static void handleEscapeKey(World currentScreen, World targetScreen) {
       // Check for escape key press with debouncing
       if (Greenfoot.isKeyDown("escape") && checker != true) {
           checker = true;
           // Special handling for Game screen
           if (currentScreen instanceof Game) {
               ((Game) currentScreen).freezeGame();
               ((Game) currentScreen).stopped();
           }
           
           Greenfoot.setWorld(targetScreen);
       } else if (!Greenfoot.isKeyDown("escape")) {
           checker = false;
       }
   }

   /**
    * Handles escape key press specifically for pause screen transitions.
    * Includes special handling for resuming Game screen.
    *
    * @param currentScreen The currently active screen (usually pause screen)
    * @param targetScreen The screen to return to (usually game screen)
    */
   public static void PausehandleEscapeKey(World currentScreen, World targetScreen) {
       if (Greenfoot.isKeyDown("escape") && !checker) {
           checker = true;
           
           // Resume game if transitioning back to Game screen
           if (targetScreen instanceof Game) {
               ((Game) targetScreen).resumeGame();
               ((Game) targetScreen).started();
           }
           
           Greenfoot.setWorld(targetScreen);
       } else if (!Greenfoot.isKeyDown("escape")) {
           checker = false;
       }
   }

   /**
    * Handles enter key press for screen transitions.
    * Includes debouncing to prevent multiple transitions.
    *
    * @param currentScreen The currently active screen
    * @param targetScreen The screen to transition to
    */
   public static void handleEnterKey(World currentScreen, World targetScreen) {
       checker = true;

       if (Greenfoot.isKeyDown("enter") && checker && targetScreen != null) {
           Greenfoot.setWorld(targetScreen);
           checker = false;
       } else if (!Greenfoot.isKeyDown("enter")) {
           checker = true;
       }
   }
   
   /**
    * Generates a random integer from 0 to max inclusive.
    *
    * @param max The upper bound for random number generation
    * @return A random integer between 0 and max
    */
   public static int randomInt(int max) {
       Random rand = new Random();
       return rand.nextInt(max + 1);
   }
   
   /**
    * Generates a random boolean value with 50/50 probability.
    *
    * @return A random boolean value
    */
   public static boolean randomBoolean() {
       Random rand = new Random();
       if(rand.nextInt(2) == 0) {
           return true;
       } else {
           return false;
       }
   }
}