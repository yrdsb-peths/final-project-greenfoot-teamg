import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Enemy extends Actor
{
    // Health variable
    protected int health = 5;
    TransparentBox hitbox = new TransparentBox(this);
    GreenfootSound explosionSound = new GreenfootSound("Explosion.mp3");
    
    // This method decreases the health of the enemy
    public void decreaseHealth(int damage) {
        health -= damage;
        if (health <= 0) {
            die();
        }
    }

    // Method for handling the enemy's death (removal from the world)
    public void die() {
        getWorld().addObject(new Explosion(), getX(), getY());
        explosionSound.setVolume(AudioManager.getInstance().getEffectiveVolume());
        explosionSound.play();
        getWorld().removeObject(this);  // Remove the enemy from the world
    }

    // Method to move the enemy down
    public void moveDown() {
        setLocation(getX(), getY() + 1);  // Moves the enemy down every frame
    }

    // Overridden act method for basic movement
    public void act() {
        moveDown();  // Ensure every enemy moves down
        if(getY() >= getWorld().getHeight() - 1)
        {
           getWorld().removeObject(this); 
        }
    }
}
