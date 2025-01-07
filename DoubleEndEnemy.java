import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class DoubleEndEnemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DoubleEndEnemy extends Enemy
{
    SimpleTimer timer = new SimpleTimer();
    int rotate = 0;


    public DoubleEndEnemy() {
        GreenfootImage image = new GreenfootImage("EnemySpaceShip5.png");
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
        if(timer.millisElapsed() > 500) {
            Game game = (Game) getWorld();

            EnemyBullet0 bullet = new EnemyBullet0();
            Bullet backBullet = new EnemyBullet3();

            game.addObject(bullet, this.getX(), this.getY());
            game.addObject(backBullet, this.getX(), this.getY());


            bullet.setRotation(rotate);
            backBullet.setRotation(180 + rotate);

            bullet.move(30);
            backBullet.move(30);

            rotate += 30;
            timer.mark();
        }
    }
}
