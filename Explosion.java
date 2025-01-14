import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

public class Explosion extends Actor implements Freezable{
  private GreenfootImage[] explosionImage; //Holder for the images
  private int totalframe = 10; //Total images for the animation
  private int currentframe = 0; // Index for the explosionImage array
  SimpleTimer animationTimer = new SimpleTimer();

  /**
   * This is a constructor that sets up all the variables needed for the explosion class.
   */
  public Explosion() {
    loadimage();
    setImage(explosionImage[0]);
    animationTimer.mark();
  }

  /**
   * This act method animates the object
   */
  public void act() {
    if(((Game)getWorld()).isFreeze == false)
    {
        animateimage();
    }
  }

  /**
   * Sets all the different explosion images needed for the animations into an array.
   */
  public void loadimage() {
    explosionImage = new GreenfootImage[totalframe];
    for (int i = 0; i < explosionImage.length; i++) {
        explosionImage[i] = new GreenfootImage("images/ExplosionAnimation/tile00" + i + ".png");
        explosionImage[i].scale(150, 150);
    }
  }

  /**
   * Runs through each image of the array until the end, where the object will remove itself from the world.
   */
  public void animateimage() {
    if(animationTimer.millisElapsed() < 40)
    {
        return;
    }
    animationTimer.mark();
    if (currentframe < explosionImage.length) {
        setImage(explosionImage[currentframe]);
        currentframe++;
    }
    else{ // Remove when animation is over
        getWorld().removeObject(this);
    }
  }

  public void freeze()
  {
      animationTimer.freeze();
  }
  
  public void unfreeze()
  {
      animationTimer.unfreeze();
  }
}
