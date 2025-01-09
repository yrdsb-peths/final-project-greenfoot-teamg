import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Represents a projectile fired by the player's character.
 */
public class Projectile extends Actor {
    private int speed = 15; // Speed of the projectile

    /**
     * Called on every frame; moves the projectile.
     */
    public void act() {
        if(((Game)getWorld()).isFreeze == false)
        {
            moveUp();
            checkRemoval();
        }
    }

    /**
     * Move the projectile upward.
     */
    private void moveUp() {
        setLocation(getX(), getY() - speed);
    }

    /**
     * Remove the projectile if it goes out of bounds.
     */
    private void checkRemoval() {
        if (getY() <= 1) {
            getWorld().removeObject(this); 
        }
    }
}
