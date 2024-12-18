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
    double rotate = 0;

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
            //EnemyBullet0 bullet = new EnemyBullet0();
            //Game game = (Game) getWorld();
            //game.addObject(bullet, this.getX(), this.getY());
            rotation();
            rotate += (Math.PI * 5) / (180.0);
            timer.mark();
        }
    }

    public void rotation() {
        double r = 500.0;
        int x = (int) (r * Math.cos(rotate));
        int y = (int) (r * Math.sin(rotate));
        this.turnTowards(x, y);
        //bullet.turnTowards(x, y);
    }
}
