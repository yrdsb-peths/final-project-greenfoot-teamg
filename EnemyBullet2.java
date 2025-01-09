import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

// Richochet bullet, bounces off the world boundaries once

public class EnemyBullet2 extends Bullet
{
    SimpleTimer bounceTimer = new SimpleTimer();
    int maxX;
    int minX = 0;

    public EnemyBullet2() {
        GreenfootImage image = new GreenfootImage("EnemyBullet2.png");
        image.scale(10, 10);
        this.setImage(image);
        moveTimer.mark();
        bounceTimer.mark();
    }

    public void act()
    {
        if(((Game)getWorld()).isFreeze == false)
        {
            moveBullet();
            checkBounds();
        }
    }
    
    public void freeze()
    {
        super.freeze();
        bounceTimer.freeze();
    }
    
    public void unfreeze()
    {
        super.unfreeze();
        bounceTimer.unfreeze();
    }
    
    public void moveBullet() {
        if(moveTimer.millisElapsed() > 20) {
            checkBounce();
            move(10);
            moveTimer.mark();
        }
    }

    public void checkBounce() {
        if(bounceTimer.millisElapsed() <= 5000) {
            Game game = (Game) getWorld(); // Game edges
            maxX = game.getWidth();
            int rotation = getRotation();
    
            if(getX() <= minX + 1 || getX() >= maxX - 1) { // set angle if hits either sides
                int ref = 180 - rotation;
                setRotation(ref);
            }
        }
    }
    
    public void checkBounds() {
        Game game = (Game) getWorld();
        if(bounceTimer.millisElapsed() > 5000)
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
