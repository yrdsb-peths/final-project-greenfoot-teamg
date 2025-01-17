import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class BigEnemy extends Enemy {
    SimpleTimer timer = new SimpleTimer();
    boolean ricochet = true;

    /**
     * Constructor for the big enemy, sets up all the variables
     */
    public BigEnemy() {
        GreenfootImage image = new GreenfootImage("EnemySpaceShip7.png");
        image.scale(75, 75);
        image.rotate(90);
        this.setImage(image);
        timer.mark();
    }

    /**
     * Calls the super class act and creates the bullets.
     */
    public void act() {
        makeBullet();  // Fires bullets specific to BigEnemy
        super.act();  // Ensure the enemy moves down
    }

    /**
     * Every 1000 seconds, shoots two seeking bullets. Every 2000 seconds, shoots two richochet bullets
     */
    public void makeBullet() {
        if (timer.millisElapsed() > 1000) {
            Game game = (Game) getWorld();

            // Right seeking bullet
            Bullet seek1 = new EnemyBullet1();
            game.addObject(seek1, this.getX() + 25, this.getY());
            seek1.move(30);

            // Left seeking bullet
            Bullet seek2 = new EnemyBullet1();
            game.addObject(seek2, this.getX() - 25, this.getY());
            seek2.setRotation(180);
            seek2.move(30);

            // Middle ricochet bullet
            if(ricochet == true) {
                for(int i = 0; i < 2; i++)
                {
                    Bullet ricochetBullet = new EnemyBullet2();
                    game.addObject(ricochetBullet, getX(), getY());
                    ricochetBullet.turn(Util.randomInt(90) + 90 * i);
                }
    
                ricochet = false;
            } else {
                ricochet = true;
            }

            timer.mark();  // Reset the timer for next shot
        }
    }
}
