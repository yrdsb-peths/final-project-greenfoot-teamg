import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

// Regular bullet that travels straight

public class EnemyBullet0 extends Bullet
{    
    /**
     * Constructor for actor, sets up all the variables.
     */
    public EnemyBullet0() {
        GreenfootImage image = new GreenfootImage("EnemyBullet0.png");
        image.scale(10, 10);
        this.setImage(image);
        moveTimer.mark();
    }

    /**
     * Calls the super class act
     */
    public void act()
    {
        super.act();
    }

    /**
     * Moves the bullet forawrd
     */
    public void moveBullet() {
        if(moveTimer.millisElapsed() > 20) {
            move(10);
            moveTimer.mark();
        }
    }
}