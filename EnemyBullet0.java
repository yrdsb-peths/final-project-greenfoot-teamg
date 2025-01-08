import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

// Regular bullet that travels straight

public class EnemyBullet0 extends Bullet
{    
    public EnemyBullet0() {
        GreenfootImage image = new GreenfootImage("EnemyBullet0.png");
        image.scale(10, 10);
        this.setImage(image);
        moveTimer.mark();
    }

    public void act()
    {
        super.act();
    }

    // Linear movement
    public void moveBullet() {
        if(moveTimer.millisElapsed() > 20) {
            move(10);
            moveTimer.mark();
        }
    }
}