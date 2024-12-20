import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class SeekingEnemy extends Enemy
{
    SimpleTimer timer = new SimpleTimer();

    public SeekingEnemy() {
        GreenfootImage image = new GreenfootImage("EnemySpaceShip2.png");
        image.scale(75, 75);
        image.rotate(90);
        this.setImage(image);
        timer.mark();
    }

    public void act()
    {
        makeBullet();
    }

    boolean spawn = true;

    public void makeBullet() {
        if(timer.millisElapsed() > 1000 && spawn == true) {
            Bullet bullet = new EnemyBullet1();
            Game game = (Game) getWorld();
            game.addObject(bullet, this.getX(), this.getY());
            bullet.move(30);
            
            spawn = false;
            timer.mark();
        }
    }
}