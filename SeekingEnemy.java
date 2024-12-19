import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class SeekingEnemy extends Enemy
{
    SimpleTimer timer = new SimpleTimer();

    public SeekingEnemy() {
        GreenfootImage image = new GreenfootImage("EnemySpaceship2.png");
        image.scale(75, 75);
        image.rotate(90);
        this.setImage(image);
        timer.mark();
    }

    public void act()
    {
        makeBullet();
    }

    public void makeBullet() {
        if(timer.millisElapsed() > 100) {
            EnemyBullet0 bullet = new EnemyBullet0();
            Game game = (Game) getWorld();
            game.addObject(bullet, this.getX(), this.getY());
            bullet.move(30);

            timer.mark();
        }
    }
}
