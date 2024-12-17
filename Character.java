import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Represents a playable character in the game with movement functionality.
 */
public class Character extends Actor {
    private int speed = 4; // Normal movement speed

    /**
     * Constructor to set the character's initial image.
     */
    public Character(GreenfootImage characterImage) {
        setImage(characterImage);
    }

    public void updateImage(GreenfootImage characterImage) {
        setImage(characterImage);
    }

    /**
     * Called on every frame; handles movement.
     */
    public void act() {
        handleMovement();
    }

    /**
     * Handle WASD movement and Shift-based speed adjustment.
     */
    private void handleMovement() {
        int currentSpeed = speed;

        // Check if Shift is held and adjust speed
        if (Greenfoot.isKeyDown("shift")) {
            currentSpeed /= 2; // Halve the speed
        }

        // WASD movement
        if (Greenfoot.isKeyDown("w")) {
            setLocation(getX(), getY() - currentSpeed); // Move up
        }
        if (Greenfoot.isKeyDown("s")) {
            setLocation(getX(), getY() + currentSpeed); // Move down
        }
        if (Greenfoot.isKeyDown("a")) {
            setLocation(getX() - currentSpeed, getY()); // Move left
        }
        if (Greenfoot.isKeyDown("d")) {
            setLocation(getX() + currentSpeed, getY()); // Move right
        }
    }
}
