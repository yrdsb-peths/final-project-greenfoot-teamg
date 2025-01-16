import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Represents a smaller, invisible hitbox for the character.
 */
public class TransparentBox extends Actor {
    private Actor actor; // Reference to the associated Actor object
    
    /**
     * Constructor to link the TransparentBox with the Actor.
     */
    public TransparentBox(Actor actor) {
        this.actor = actor;
        // Ensure you have the "TransparentBox.png" image and it is scaled appropriately
        GreenfootImage image = new GreenfootImage("TransparentBox.png");
        if(actor.getClass() == Enemy.class)
        {
            image.scale(50, 50);
        }
        else
        {
            image.scale(10, 10); // Example scaling to make it smaller
        }
        setImage(image);
    }

    /**
     * If the actor is no longer in the world, the transparent box will remove itself, 
     * also follows the actor's position and checks for collision if
     * the actor is of the class Character.
     */
    public void act() {
        if (actor.getWorld() == null) {
            getWorld().removeObject(this); // Automatically remove the box if the character is gone
            return;
        }

        // Follow the actor's position
        setLocation(actor.getX(), actor.getY());

        // Check for collisions with bullets
        if(actor.getClass() == Character.class)
        {
            checkForBulletCollision();
        }
    }

    /**
     * Handle collision with bullets, lasers, and enemies and trigger character death.
     */
    private void checkForBulletCollision() {
        // Detect collision with any Bullet and Enemy subclass (e.g., EnemyBullet0, SimpleEnemy, etc.)
        if (isTouching(Bullet.class) || isTouching(TransparentBox.class)){
            ((Character)actor).die(); // Trigger the character's death
            return;
        }
        
        if(isTouching(LaserBeam.class))
        {
            boolean isSameSide = false;
            for(LaserBeam laser: getIntersectingObjects(LaserBeam.class))
            {
                int laserRotation = laser.getRotation();
                if(isSameSide == false)
                {
                    isSameSide = getY() >= laser.getY() && laserRotation <= 180 || getY() <= laser.getY() && laserRotation <= 360 && laserRotation >= 180;
                }
            }
            if(isSameSide)
            {
                ((Character)actor).die();
            }
        }
    }
}
