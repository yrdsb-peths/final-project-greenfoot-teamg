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
        image.scale(30, 30); // Example scaling to make it smaller
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
        //checkForBulletCollision();
    }

    /**
     * Handle collision with bullets and trigger character death.
     */
    private void checkForBulletCollision() {
        // Detect collision with any Bullet subclass (e.g., EnemyBullet0, EnemyBullet1, etc.)
        Bullet bullet = (Bullet) getOneIntersectingObject(Bullet.class); 
        if (bullet != null) {
            getWorld().removeObject(bullet); // Remove the bullet
            character.die(); // Trigger the character's death
        }
    }
}
