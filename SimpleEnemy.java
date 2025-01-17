import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

// Enemy that shoots straight forwards

public class SimpleEnemy extends Enemy
{
    SimpleTimer timer = new SimpleTimer();

    /**
     * Constructor for the simple enemy, sets up all the variables.
     */
    public SimpleEnemy() {
        GreenfootImage image = new GreenfootImage("EnemySpaceShip0.png");
        image.scale(75, 75);
        image.rotate(90);
        this.setImage(image);
        timer.mark();
    }

    /**
     * Calls the super class act and creates a bullet.
     */
    public void act()
    {
        makeBullet();
        super.act();  // Ensure the enemy moves down
    }

    /**
     * Repeatedly shoots normal bullets in front of the ship.
     */
    public void makeBullet() {
        if(timer.millisElapsed() > 700) {
            EnemyBullet0 bullet = new EnemyBullet0();
            Game game = (Game) getWorld();
            game.addObject(bullet, this.getX(), this.getY());
            // Bullet speed
            bullet.setRotation(90);
            bullet.move(30);
            timer.mark();
        }
    }
}
