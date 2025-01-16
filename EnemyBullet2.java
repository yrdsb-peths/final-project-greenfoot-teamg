import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

// Ricochet bullet, bounces off the world boundaries once

public class EnemyBullet2 extends Bullet
{
    SimpleTimer bounceTimer = new SimpleTimer();
    int maxX;
    int minX = 0;

    /**
     * Constructor for the richochet bullet
     */
    public EnemyBullet2() {
        GreenfootImage image = new GreenfootImage("EnemyBullet2.png");
        image.scale(10, 10);
        this.setImage(image);
        moveTimer.mark();
        bounceTimer.mark();
    }
    
    /**
     * moves bullet and checks if object is out of bounds if game is not frozen
     */
    public void act()
    {
        if(((Game)getWorld()).isFreeze == false)
        {
            moveBullet();
            checkBounds();
        }
    }
    
    /**
     * calls the super class method freeze() and freezes bounceTimer
     */
    public void freeze()
    {
        super.freeze();
        bounceTimer.freeze();
    }
    
    /**
     * calls the super class method unfreeze() and unfreezes bounceTimer
     */
    public void unfreeze()
    {
        super.unfreeze();
        bounceTimer.unfreeze();
    }
    
    /**
     * Moves the bullet
     */
    public void moveBullet() {
        if(moveTimer.millisElapsed() > 20) {
            checkBounce();
            move(10);
            moveTimer.mark();
        }
    }

    /**
     * bounces if on game edges
     */
    public void checkBounce() {
        if(bounceTimer.millisElapsed() <= 5000) {
            Game game = (Game) getWorld(); // Game edges
            maxX = game.getWidth();
            int rotation = getRotation(); // bullet's rotation
    
            if(getX() <= minX + 1 || getX() >= maxX - 1) { // set angle if hits either sides
                int ref = 180 - rotation;
                setRotation(ref);
            }
        }
    }
    
    /**
     * Checks if ball is out of bounds
     */
    public void checkBounds() {
        Game game = (Game) getWorld();
        if(bounceTimer.millisElapsed() > 5000) // If within bounce time, does not delete bullet if touching left/right border
        {
            if(getX() <= 0 || getY() <= 0 || getX() >= game.getWidth() - 1 || getY() >= game.getHeight() - 1) {
                game.removeObject(this);
            }
        }
        else
        {
            if(getY() <= 0 || getY() >= game.getHeight() - 1)
            {
               game.removeObject(this); 
            }
        }
    }
}
