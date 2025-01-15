import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Represents a playable character in the game with movement functionality.
 */
public class Character extends Actor implements Freezable{
    private int speed = 6; // Normal movement speed
    private TransparentBox hitbox; // Reference to the associated TransparentBox
    SimpleTimer shootCooldown = new SimpleTimer();
    SimpleTimer abilityCooldown = new SimpleTimer();
    int whichCharacter;
    boolean isHoming, isDoubleDamage;
    GreenfootSound explosionSound = new GreenfootSound("Explosion.mp3");
    
    /**
     * Constructor to set the character's initial image and create a hitbox.
     */
    public Character(GreenfootImage characterImage, int whichCharacter) {
        setImage(characterImage);
        this.whichCharacter = whichCharacter;
        abilityCooldown.freezeMark = 999999999;
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
        if(((Game)getWorld()).isFreeze == false)
        {
            handleMovement();
            handleShooting();
            handleAbility();
        }
    }

    public void handleAbility()
    {
        if(Greenfoot.isKeyDown("v") && abilityCooldown.millisElapsed() > 40000)
        {
            useAbility();
            abilityCooldown.mark();
        }
        if(abilityCooldown.millisElapsed() > 10000)
        {
            isHoming = false;
            isDoubleDamage = false;
        }
    }
    
    public void useAbility()
    {
        if(whichCharacter == 0)
        {
            isHoming = true;
        }
        else if(whichCharacter == 1)
        {
            isDoubleDamage = true;
        }
        else if(whichCharacter == 2)
        {
            getWorld().addObject(new Forcefield(), getX(), getY());
            abilityCooldown.freezeMark = 10000;
        }
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
            if(getY() < 10)
            {
                setLocation(getX(), 10);
            }
        }
        if (Greenfoot.isKeyDown("s")) {
            setLocation(getX(), getY() + currentSpeed); // Move down
            if(getY() > getWorld().getHeight()-10)
            {
                setLocation(getX(), getWorld().getHeight() - 10);
            }
        }
        if (Greenfoot.isKeyDown("a")) {
            setLocation(getX() - currentSpeed, getY()); // Move left
            if(getX() < 10)
            {
                setLocation(10, getY());
            }
        }
        if (Greenfoot.isKeyDown("d")) {
            setLocation(getX() + currentSpeed, getY()); // Move right
            if(getX() > getWorld().getWidth()-10)
            {
                setLocation(getWorld().getWidth() - 10, getY());
            }
        }
    }

    /**
     * Handle shooting when the space bar is held down.
     */
    private void handleShooting() {
        // Shoot when space is pressed and cooldown is 0
        if (Greenfoot.isKeyDown("space") && shootCooldown.millisElapsed() > 200) {
            shoot();
            shootCooldown.mark();
        }
    }

    /**
     * Shoot a projectile from the character's position.
     */
    private void shoot() {
        // Create a new projectile and add it to the world
        Projectile projectile = new Projectile(isHoming, isDoubleDamage);
        getWorld().addObject(projectile, getX(), getY() - getImage().getHeight() / 2);
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
        getWorld().addObject(new Explosion(), getX(), getY());
        explosionSound.play();
        ((Game)getWorld()).resetWaveTimer();
        getWorld().removeObject(this);
        
        // Transition to a game-over screen or similar
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
    
    public void freeze()
    {
        shootCooldown.freeze();
        abilityCooldown.freeze();
    }
    
    
    public void unfreeze()
    {
        shootCooldown.unfreeze();
        abilityCooldown.unfreeze();
    }
}
