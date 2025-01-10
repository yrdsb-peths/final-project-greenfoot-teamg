import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot, and MouseInfo)

public class Projectile extends Actor {
    private int speed = 15; // Speed of the projectile
    
    /**
     * This method is called every frame to handle the projectile's behavior.
     */
    public void act() {
        if (getWorld() == null) {
            return; // Ensure that the projectile is still in the world before doing anything
        }
        if(((Game)getWorld()).isFreeze == false)
        {
            moveProjectile();
            checkForCollision();
        }
    }

    /**
     * Move the projectile in the direction it is facing.
     */
    private void moveProjectile() {
        move(speed); // Move in the direction the projectile is facing (which was set in the Character class)
        
        // If the projectile goes off-screen, remove it
        if (!isOnScreen()) {
            getWorld().removeObject(this);
        }
    }

    /**
     * Check for collision with enemies or other objects.
     */
    private void checkForCollision() {
        if (getWorld() == null) {
            return; // Ensure that the projectile is still in the world before doing anything
        }

        Actor hit = getOneIntersectingObject(Enemy.class);
        if (hit != null) {
            // If the projectile hits an enemy, deal damage or remove the projectile
            ((Enemy) hit).decreaseHealth(1); // Assuming the enemy has a decreaseHealth method
            getWorld().removeObject(this); // Remove the projectile after hitting the enemy
        }
    }
    
    /**
     * Check if the projectile is on-screen.
     */
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
