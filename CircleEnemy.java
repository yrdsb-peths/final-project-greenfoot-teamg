import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CircleEnemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CircleEnemy extends Actor
{
    SimpleTimer timer = new SimpleTimer();
    int rotate = 0;

    public CircleEnemy() {
        GreenfootImage image = new GreenfootImage("EnemySpaceship1.png");
        image.scale(75, 75);
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
            bullet.setRotation(rotate);

            rotate += 2;
            timer.mark();
        }
    }
}
