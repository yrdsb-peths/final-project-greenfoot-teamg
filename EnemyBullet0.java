import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

// Regular bullet that travels straight

public class EnemyBullet0 extends Bullet
{
    SimpleTimer timer = new SimpleTimer();

    public EnemyBullet0() {
        GreenfootImage image = new GreenfootImage("EnemyBullet0.png");
        image.scale(10, 10);
        this.setImage(image);
        timer.mark();
    }


    public void act()
    {
        moveBullet();
        checkBounds();
    }

    // Linear movement
    public void moveBullet() {
        if(timer.millisElapsed() > 20) {
            move(5);
            timer.mark();
        }
    }
}