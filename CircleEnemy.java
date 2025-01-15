import greenfoot.*;

public class CircleEnemy extends Enemy {
    SimpleTimer timer = new SimpleTimer();
    int rotate = 0;

    public CircleEnemy() {
        GreenfootImage image = new GreenfootImage("EnemySpaceship3.png");
        image.scale(75, 75);
        image.rotate(90);
        this.setImage(image);
        timer.mark();
    }

    public void act() {
        super.act();  // Ensure the enemy moves down
        makeBullet();  // Fires bullets specific to CircleEnemy
    }

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
