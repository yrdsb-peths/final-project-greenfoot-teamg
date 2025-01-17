import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

// Enemy that shoots split bullets

public class SplitEnemy extends Enemy
{
    SimpleTimer timer = new SimpleTimer();

    /**
     * Constructor for the split enemy, sets up all the variables
     */
    public SplitEnemy() {
        GreenfootImage image = new GreenfootImage("EnemySpaceShip2.png");
        image.scale(75, 75);
        image.rotate(90);
        this.setImage(image);
        timer.mark();
    }

    /**
     * Calls the super class act and crates a bullet.
     */
    public void act()
    {
        makeBullet();
        super.act();  // Ensure the enemy moves down
    }

    /**
     * Shoots a bullet that splits into 5 smaller bullets at the player
     */
    public void makeBullet() {
        if(timer.millisElapsed() > 1500) {
            EnemyBullet3 bullet = new EnemyBullet3(getX(), getY());
            Game game = (Game) getWorld();
            game.addObject(bullet, getX(), getY());
            // Bullet faces and spawns in front of ship initially
            bullet.setRotation(getRotation() + 90);
            bullet.move(30);

            // Rotate bullet to face player
            if(game.player.getWorld() != null)
            {
                bullet.turnTowards(game.player.getX(), game.player.getY());
            }
            
            timer.mark();
        }
    }
}
