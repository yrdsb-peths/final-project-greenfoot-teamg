import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Represents a playable character in the game with movement functionality.
 */
public class Character extends Actor {
    private int speed = 6; // Normal movement speed
    private int shootDelay = 10; // Delay between consecutive shots (in frames)
    private int shootCooldown = 0; // Countdown for the next allowed shot
    private TransparentBox hitbox; // Reference to the associated TransparentBox

    /**
     * Constructor to set the character's initial image and create a hitbox.
     */
    public Character(GreenfootImage characterImage) {
        setImage(characterImage);
    }

    public void addedToWorld(World world) {
        // Create and add the hitbox when the character is added to the world
        hitbox = new TransparentBox(this);
        getWorld().addObject(hitbox, getX(), getY());
    }

    public void updateImage(GreenfootImage characterImage) {
        setImage(characterImage);
    }

    /**
     * Called on every frame; handles movement and shooting.
     */
    public void act() {
        handleMovement();
        handleShooting();
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

    /**
     * Handle shooting when the space bar is held down.
     */
    private void handleShooting() {
        if (shootCooldown > 0) {
            shootCooldown--; // Decrease the cooldown timer
        }

        // Shoot when space is pressed and cooldown is 0
        if (Greenfoot.isKeyDown("space") && shootCooldown == 0) {
            shoot();
            shootCooldown = shootDelay; // Reset cooldown
        }
    }

    /**
     * Shoot a projectile from the character's position.
     */
    private void shoot() {
        // Create a new projectile and add it to the world
        Projectile projectile = new Projectile();
        getWorld().addObject(projectile, getX(), getY() - getImage().getHeight() / 2);
    
        // Set the bullet to move upwards (270 degrees)
        projectile.setRotation(270);
    
        // Optional: Add a sound effect
        Greenfoot.playSound("StarWarsBlaster.mp3");
    }

    /**
     * Handle character death by removing the character, the hitbox, and transitioning to a game-over screen.
     */
    public void die() {
        // Create an explosion effect
        //Explosion explosion = new Explosion();
        //getWorld().addObject(explosion, getX(), getY());

        // Remove the hitbox and character
        if (hitbox != null) {
            getWorld().removeObject(hitbox);
        }
        getWorld().removeObject(this);

        // Transition to a game-over screen or similar
        Greenfoot.setWorld(new GameOver());
    }
    
    public boolean isOnScreen() {
        World world = getWorld();
        if (world != null) {
            int x = getX();
            int y = getY();
            return x > 0 && x < world.getWidth() && y > 0 && y < world.getHeight();
        }
        return false;
    }
}
