import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

// Enemy that shoots split bullets

public class SplitEnemy extends Enemy
{
    SimpleTimer timer = new SimpleTimer();

    public SplitEnemy() {
        GreenfootImage image = new GreenfootImage("EnemySpaceShip4.png");
        image.scale(75, 75);
        image.rotate(90);
        this.setImage(image);
        timer.mark();
    }

    public void act()
    {
        super.act();  // Ensure the enemy moves down
        makeBullet();
    }

    
    public void makeBullet() {
        if(timer.millisElapsed() > 1500) {
            EnemyBullet3 bullet = new EnemyBullet3(getX(), getY());
            Game game = (Game) getWorld();
            game.addObject(bullet, getX(), getY());
            // Bullet faces and spawns in front of ship initially
            bullet.setRotation(getRotation() + 90);
            bullet.move(30);

            // Rotate bullet to face player
            bullet.turnTowards(game.player.getX(), game.player.getY());
            
            timer.mark();
        }
    }
}
