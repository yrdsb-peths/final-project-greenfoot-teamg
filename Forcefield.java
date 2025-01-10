import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

public class Forcefield extends Actor
{
    SimpleTimer animationTimer = new SimpleTimer();
    GreenfootImage forcefieldImage;
    private GreenfootSound ShieldBlast;
    private List<Enemy> oldEnemyList;
    private boolean hitBoss = false;
    int width = 20;
    int height = 20;
    /**
     * Constructor that sets up all the variables for the class.
     */
    public Forcefield()
    {
        forcefieldImage = new GreenfootImage("Forcefield.png");
        animationTimer.mark();
        forcefieldImage.scale(width,height);
        setImage(forcefieldImage);
    }
    
    /**
     * Will continously delete any enemy it is touching and will get larger.
     */
    public void act()
    {
        checkTouching();
        animate();
    }
    
    /**
     * Every 5 milliseconds, the object will constantly get bigger, until it is twice the size of the height of the world. Then it will remove itself
     * from the world. Also plays a sound when triggered.
     */
    public void animate()
    {
        if(animationTimer.millisElapsed() < 5)
        {
            return;
        }
        ShieldBlast = new GreenfootSound("ShieldBlast.mp3");
        animationTimer.mark();
        /*
         * Need to create a new image each time it grows bigger because if you start small and scale image bigger, it will lose pixels and the image
         * will look werid.
         */
        forcefieldImage = new GreenfootImage("Forcefield.png");
        width += 20;
        height += 20;
        forcefieldImage.scale(width,height);
        setImage(forcefieldImage);
        ShieldBlast.setVolume(20);
        ShieldBlast.play();
        if(forcefieldImage.getHeight()/2 > getWorld().getHeight())
        {
            getWorld().removeObject(this);
        }
    }
    
    public void checkTouching()
    {
        removeBullets();
        checkEnemy();
        checkBoss();
    }
    
    public void removeBullets()
    {
        List<Bullet> bulletList = getIntersectingObjects(Bullet.class);
        for(Bullet bullet: bulletList)
            getWorld().removeObject(bullet);
    }
    
    public void checkEnemy()
    {
        List<Enemy> e = getIntersectingObjects(Enemy.class);
        for(Enemy enemy: e)
        {
            if(oldEnemyList == null)
            {
                enemy.decreaseHealth(10);
            }
            else if(!oldEnemyList.contains(enemy))
            {
                enemy.decreaseHealth(10);
            }
        }
        oldEnemyList = e;
    }
    
    public void checkBoss()
    {
        if(isTouching(Boss.class) && hitBoss == false)
        {
            ((Boss)getOneIntersectingObject(Boss.class)).decreaseHealth(10);
            hitBoss = true;
        }
    }
    
    /**
     * Gets a list of every enemy or boss that the forcefield is touching and damages them.
     */
    public void damageEnemy()
    {
        
    }
}
