import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot, and MouseInfo)
import java.util.List;

public class Projectile extends Actor {
    private int speed = 15; // Speed of the projectile
    boolean isHoming, isDoubleDamage;

    public Projectile(boolean isHoming, boolean isDoubleDamage)
    {
        GreenfootImage image;
        if(isHoming)
        {
            image = new GreenfootImage("LaserShot3.png");
            image.scale(40,15);
        }
        else if(isDoubleDamage)
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
        this.isDoubleDamage = isDoubleDamage;
        turn(-90);
    }

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
        move(speed); // Move in the direction the projectile is facing

        // If the projectile goes off-screen, remove it
        if (!isOnScreen()) {
            getWorld().removeObject(this);
        }
    }

    private void checkForCollision() {
        if (getWorld() == null) {
            return; // Ensure that the projectile is still in the world before doing anything
        }
        
        Actor hit = getOneIntersectingObject(Enemy.class);
        if (hit != null) {
            if(isDoubleDamage)
            {
                 ((Enemy) hit).decreaseHealth(2);
            }
            else
            {
                 ((Enemy) hit).decreaseHealth(1);  
            }
            getWorld().removeObject(this); // Remove the projectile after hitting the enemy
            return;
        }
        
        hit = getOneIntersectingObject(Boss.class);
        if (hit != null) {
            System.out.println("Boss hit by projectile!"); // Debug message
            if(isDoubleDamage)
            {
                 System.out.println("Double damage to boss!"); // Debug message
                 ((Boss) hit).decreaseHealth(2);
            }
            else
            {
                 System.out.println("Normal damage to boss!"); // Debug message
                 ((Boss) hit).decreaseHealth(1);  
            }
            getWorld().removeObject(this); // Remove the projectile after hitting the boss
        }
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
