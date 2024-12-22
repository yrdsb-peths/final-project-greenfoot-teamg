import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

// Seeking bullet, follows target

public class EnemyBullet1 extends Bullet
{
    SimpleTimer timer = new SimpleTimer();
    boolean far = true;
    int turnAmount = 2; // Amount bullet turns

    public EnemyBullet1() {
        GreenfootImage image = new GreenfootImage("EnemyBullet1.png");
        image.scale(10, 10);
        this.setImage(image);
        timer.mark();
    }

    public void act()
    {
        moveBullet();
        checkBounds();
    }
    

    public void moveBullet() {
        Game game = (Game) getWorld();

        if(timer.millisElapsed() > 20) { // Rate of movement

            // Updates location of player's character
            double x = game.player.getX();
            double y = game.player.getY();

            if(far == true) { // Only rotates if not close to target

                // x and y distances to target
                double toX = x - getX();
                double toY = getY() - y;

                // required angle between bullet and target
                double angle = Math.atan2(toY, toX);

                // angle of bullet
                double a = Math.toRadians(-1 * getRotation());
                if(a < 0) {
                    a += Math.PI * 2;
                }
                
                // distance between target and bullet
                double distance = Math.sqrt(toX * toX + toY * toY);
                // If closer than this amount, bullet no longer turns
                if(distance < 150) {
                    far = false;
                }

                // Difference between required angle and bullet's angle
                double diff = angle - a;

                // Only allows angles between -pi and pi by subtracting 2pi if greater, and depending on that value, it will turn a direction
                if(diff > Math.PI) {
                    diff -= 2 * Math.PI;
                }
                else if(diff < -Math.PI) {
                    diff += 2 * Math.PI;
                }

                // Only turns if the difference in the angles is greater than an amount
                if(Math.abs(diff) > Math.PI / 12) {
                    if(diff > 0) {
                        turn(-turnAmount);
                    }
                    else {
                        turn(turnAmount);
                    }
                }
            }

            move(5);
            timer.mark();
        }
    }
}