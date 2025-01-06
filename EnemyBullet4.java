import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Curving Bullet
 */
public class EnemyBullet4 extends Bullet
{
    SimpleTimer timer = new SimpleTimer();
    SimpleTimer aliveTime = new SimpleTimer();
    boolean curveRight;
    int turnSpeed;
    public EnemyBullet4(){
        GreenfootImage image = new GreenfootImage("EnemyBullet4.png");
        image.scale(10, 10);
        this.setImage(image);
        this.curveRight = curveRight;
        timer.mark();
        aliveTime.mark();
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

    public void act()
    {
        moveBullet();
        checkBounds();
    }
    
    public void moveBullet() {
        if(timer.millisElapsed() > 20) {
            move(10);
            if(aliveTime.millisElapsed() < 4000)
                if(curveRight)
                {
                    turn(turnSpeed);
                }else
                {
                    turn(-turnSpeed);
                }
            timer.mark();
        }
    }
}
