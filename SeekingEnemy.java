import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

// Enemy that shoots seeking bullets

public class SeekingEnemy extends Enemy
{
    SimpleTimer timer = new SimpleTimer();

    public SeekingEnemy() {
        GreenfootImage image = new GreenfootImage("EnemySpaceShip1.png");
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
        if(timer.millisElapsed() > 1000) {
            Bullet bullet = new EnemyBullet1();
            Game game = (Game) getWorld();
            game.addObject(bullet, this.getX(), this.getY());

            // Bullet faces and spawns in front of ship initially
            bullet.setRotation(90);
            bullet.move(30);
            
            timer.mark();
        }
    }
}
