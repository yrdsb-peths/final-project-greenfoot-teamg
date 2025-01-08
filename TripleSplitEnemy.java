import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

// 3 splitting bullets

public class TripleSplitEnemy extends Enemy
{
    SimpleTimer timer = new SimpleTimer();

    public TripleSplitEnemy() {
        GreenfootImage image = new GreenfootImage("EnemySpaceShip8.png");
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
        if(timer.millisElapsed() > 3000) {
            Game game = (Game) getWorld();

            // Middle bullet
            EnemyBullet3 bullet = new EnemyBullet3();
            game.addObject(bullet, getX(), getY());
            bullet.setInitial(getX(), getY());
            bullet.setRotation(getRotation() + 90);
            bullet.move(30);

            // Right bullet
            EnemyBullet3 bullet2 = new EnemyBullet3();
            game.addObject(bullet2, getX() + 25, getY());
            bullet2.setInitial(getX() + 25, getY());
            bullet2.setRotation(getRotation() + 90);
            bullet2.move(30);

            // Left bullet
            EnemyBullet3 bullet3 = new EnemyBullet3();
            game.addObject(bullet3, getX() - 25, getY());
            bullet3.setInitial(getX() - 25, getY());
            bullet3.setRotation(getRotation() + 90);
            bullet3.move(30);

            timer.mark();
        }
    }
}
