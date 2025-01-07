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
            bullet.turnTowards(game.player.getX(), game.player.getY());

            // Right bullet
            EnemyBullet3 bullet2 = new EnemyBullet3();
            game.addObject(bullet2, getX() + 25, getY());
            bullet.setInitial(getX() + 25, getY());
            bullet.setRotation(getRotation() + 90);
            bullet.move(30);
            bullet.turnTowards(game.player.getX(), game.player.getY());

            // Left bullet
            EnemyBullet3 bullet3 = new EnemyBullet3();
            game.addObject(bullet3, getX() - 25, getY());
            bullet.setInitial(getX() - 25, getY());
            bullet.setRotation(getRotation() + 90);
            bullet.move(30);
            bullet.turnTowards(game.player.getX(), game.player.getY());

            timer.mark();
        }
    }
}
