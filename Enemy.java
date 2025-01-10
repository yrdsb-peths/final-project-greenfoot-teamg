import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Enemy extends Actor {
    protected int health = 2;

    // This method decreases the health of the enemy
    public void decreaseHealth(int damage) {
        health -= damage;
        if (health <= 0) {
            die();
        }
    }

    // Method for handling the enemy's death (removal from the world)
    private void die() {
        getWorld().removeObject(this);  // Remove the enemy from the world
    }

    // Method to move the enemy down
    public void moveDown() {
        setLocation(getX(), getY() + 1);  // Moves the enemy down every frame
    }

    // Overridden act method for basic movement
    public void act() {
        moveDown();  // Ensure every enemy moves down
    }
}
