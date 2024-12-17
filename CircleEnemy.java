import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CircleEnemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CircleEnemy extends Actor
{
    public CircleEnemy() {
        GreenfootImage image = new GreenfootImage("EnemySpaceship1.png");
        image.scale(75, 75);
        this.setImage(image);
    }

    int rotate = 0;
    SimpleTimer timer = new SimpleTimer();
    timer.mark();

    public void act()
    {
        if(timer.millisElasped() > 100) {
            EnemyBullet0 bullet = new EnemyBullet0();
            Game game = (Game) getWorld();
            game.addObject(bullet, this.getX(), this.getY());

            bullet.turn(rotate);
            rotate += 1;
            timer.mark();
        }
    }
}
