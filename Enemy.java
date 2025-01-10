import greenfoot.*;  // Importing the Greenfoot library

public class Enemy extends Actor
{
    // Health variable
    private int health = 15;

    // This method decreases the health of the enemy
    public void decreaseHealth(int damage)
    {
        health -= damage;  // Subtract the damage value from the health
        if (health <= 0) {
            die();  // Call the die method when health reaches 0
        }
    }

    // Method for handling the enemy's death (removal from the world)
    private void die()
    {
        getWorld().removeObject(this);  // Remove the enemy from the world
    }
}
