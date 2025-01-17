import greenfoot.*;

public class CircleEnemy extends Enemy {
    SimpleTimer timer = new SimpleTimer();
    int rotate = 0;

    /**
     * Constructor for the circle enemy.
     */
    public CircleEnemy() {
        GreenfootImage image = new GreenfootImage("EnemySpaceShip3.png");
        image.scale(75, 75);
        image.rotate(90);
        this.setImage(image);
        timer.mark();
    }

    /**
     * Class the super class act and creates the bullets.
     */
    public void act() {
        makeBullet();  // Fires bullets specific to CircleEnemy
        super.act();  // Ensure the enemy moves down
    }

    /**
     * Shoots bullets in a circle
     */
    public void makeBullet() {
        if (timer.millisElapsed() > 100) {
            EnemyBullet0 bullet = new EnemyBullet0();
            Game game = (Game) getWorld();
            game.addObject(bullet, this.getX(), this.getY());
            bullet.setRotation(rotate);
            
            rotate += 30; // Rotate the bullet's trajectory each time
            timer.mark(); // Reset the timer
        }
    }
}
