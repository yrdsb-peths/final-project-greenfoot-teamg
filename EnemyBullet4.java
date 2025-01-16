import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Curving Bullet
 */
public class EnemyBullet4 extends Bullet
{
    SimpleTimer curveTime = new SimpleTimer();
    boolean curveRight;
    int turnSpeed;
    
    /**
     * Constructor for the curve bullet
     */
    public EnemyBullet4(){
        GreenfootImage image = new GreenfootImage("EnemyBullet4.png");
        image.scale(10, 10);
        this.setImage(image);
        moveTimer.mark();
        curveTime.mark();
        turnSpeed = Util.randomInt(1) + 1;
        curveRight = Util.randomBoolean();
        if(curveRight)
        {
            turn(Util.randomInt(90));
        }
        else
        {
            turn(Util.randomInt(90) + 90);
        }
    }

    /**
     * Calls the super class act
     */
    public void act()
    {
        super.act();
    }
    
    /**
     * Calls the super class method freeze() and freeze the curve timer
     */
    public void freeze()
    {
        super.freeze();
        curveTime.freeze();
    }
    
    /**
     * Calls the super class method unfreeze() and unfreeze the curve timer
     */
    public void unfreeze()
    {
        super.unfreeze();
        curveTime.unfreeze();
    }
    
    /**
     * Moves and rotates the bullet
     */
    public void moveBullet() {
        if(moveTimer.millisElapsed() > 20) {
            move(10);
            if(curveTime.millisElapsed() < 4000)
                if(curveRight)
                {
                    turn(turnSpeed);
                }else
                {
                    turn(-turnSpeed);
                }
            moveTimer.mark();
        }
    }
}
