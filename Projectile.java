import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot, and MouseInfo)
import java.util.List;

public class Projectile extends Actor {
    private int speed = 15; // Speed of the projectile
    boolean isHoming, isTripleDamage;
    
    
    /**
     * Constructor for the projectile, sets up all the variables.
     */
    public Projectile(boolean isHoming, boolean isTripleDamage)
    {
        GreenfootImage image;
        if(isHoming)
        {
            image = new GreenfootImage("LaserShot3.png");
            image.scale(40,15);
        }
        else if(isTripleDamage)
        {
            image = new GreenfootImage("LaserShot4.png");
            image.scale(40,15);
        }
        else
        {
            image = new GreenfootImage("LaserShot" + Util.randomInt(2) + ".png");
            image.scale(30,10);
        }
        setImage(image);
        this.isHoming = isHoming;
        this.isTripleDamage = isTripleDamage;
        turn(-90);
    }
    
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
        if(isHoming)
        {
            List<Boss> boss = getObjectsInRange(1000, Boss.class);
            if(!boss.isEmpty())
            {
                turnTowards(boss.get(0).getX(), boss.get(0).getY());
            }
            for(int i = 0; i < 1000; i+=10)
            {
                List<Enemy> e = getObjectsInRange(i, Enemy.class);
                if(!e.isEmpty())
                {
                    turnTowards(e.get(0).getX(), e.get(0).getY());
                    break;
                }
            }
        }
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
            if(isTripleDamage)
            {
                 ((Enemy) hit).decreaseHealth(3);
            }
            else
            {
                 ((Enemy) hit).decreaseHealth(1); 
            }
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
            return x > 0 && x < world.getWidth() - 1 && y > 0 && y < world.getHeight();
        }
        return false;
    }
}
