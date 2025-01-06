import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class EnemyBullet5 extends Bullet
{
    SimpleTimer timer = new SimpleTimer();
    Boss boss;
    boolean isReturning = false;
    
    public EnemyBullet5(Boss boss) {
        GreenfootImage image = new GreenfootImage("EnemyBullet0.png");
        image.scale(10, 10);
        this.setImage(image);
        this.boss = boss;
        timer.mark();
    }


    public void act()
    {
        moveBullet();
        checkBounds();
        if(isReturning)
        {
            turnTowards(boss.getX(), boss.getY());
        }
    }

    public void checkBounds() {
        Game game = (Game) getWorld();
        if(getX() <= 0 || getY() <= 0 || getX() >= game.getWidth() - 1 || getY() >= game.getHeight() - 1) {
            GreenfootImage image = new GreenfootImage("EnemyBullet5.png");
            image.scale(10, 10);
            this.setImage(image);
            isReturning = true;
        }
    }
    
    // Linear movement
    public void moveBullet() {
        if(timer.millisElapsed() > 20) {
            move(10);
            timer.mark();
        }
    }
}
