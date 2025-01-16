import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

// 3 spaced out splitting bullets in same direction

public class TripleSplitEnemy extends Enemy
{
    SimpleTimer timer = new SimpleTimer();

    /**
     * Constructor fo the triple split enemy
     */
    public TripleSplitEnemy() {
        GreenfootImage image = new GreenfootImage("EnemySpaceShip5.png");
        image.scale(75, 75);
        image.rotate(90);
        this.setImage(image);
        timer.mark();
    }

    /**
     * Calls the super class act and creates the bullets.
     */
    public void act()
    {
        super.act();  // Ensure the enemy moves down
        makeBullet();
    }

    /**
     * Shoots 3 split bullets 3 times in 3 different directions.
     */
    public void makeBullet() {
        if(timer.millisElapsed() > 3000) {
            Game game = (Game) getWorld();

            // Middle bullet
            EnemyBullet3 bullet = new EnemyBullet3(getX(),getY());
            game.addObject(bullet, getX(), getY());
            bullet.setRotation(getRotation() + 90);
            bullet.move(30);

            // Right bullet
            EnemyBullet3 bullet2 = new EnemyBullet3(getX() + 25, getY());
            game.addObject(bullet2, getX() + 25, getY());
            bullet2.setRotation(getRotation() + 90);
            bullet2.move(30);

            // Left bullet
            EnemyBullet3 bullet3 = new EnemyBullet3(getX() - 25, getY());
            game.addObject(bullet3, getX() - 25, getY());
            bullet3.setRotation(getRotation() + 90);
            bullet3.move(30);

            timer.mark();
        }
    }
}
