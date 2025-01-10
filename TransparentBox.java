import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Represents a smaller, invisible hitbox for the character.
 */
public class TransparentBox extends Actor {
    private Character character; // Reference to the associated Character object

    /**
     * Constructor to link the TransparentBox with the Character.
     */
    public TransparentBox(Character character) {
        this.character = character;
        // Ensure you have the "TransparentBox.png" image and it is scaled appropriately
        GreenfootImage image = new GreenfootImage("TransparentBox.png");
        image.scale(10, 10); // Example scaling to make it smaller
        setImage(image);
    }

    public void act() {
        if (character.getWorld() == null) {
            getWorld().removeObject(this); // Automatically remove the box if the character is gone
            return;
        }

        // Follow the character's position
        setLocation(character.getX(), character.getY());

        // Check for collisions with bullets
        checkForBulletCollision();
    }

    /**
     * Handle collision with bullets and trigger character death.
     */
    private void checkForBulletCollision() {
        // Detect collision with any Bullet subclass (e.g., EnemyBullet0, EnemyBullet1, etc.)
        if (isTouching(Bullet.class)){
            character.die(); // Trigger the character's death
        }
        
        if(isTouching(LaserBeam.class))
        {
            LaserBeam laser = (LaserBeam) getOneIntersectingObject(LaserBeam.class);
            int laserRotation = laser.getRotation();
            boolean isSameSide = getY() >= laser.getY() && laserRotation <= 180 || getY() <= laser.getY() && laserRotation <= 360 && laserRotation >= 180;
            if(isSameSide)
            {
                character.die();
            }
        }
    }
}
