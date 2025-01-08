import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

// Seeking bullet, follows target

public class EnemyBullet1 extends Bullet
{
    boolean far = true;
    int lockDistance = 200; // Distance from target to stop tracking
    double reqAngle = Math.PI / 6; // Minimum angle to target, smaller angle is more accurate
    int turnAmount = 2; // Amount the bullet can turn each time


    public EnemyBullet1() {
        GreenfootImage image = new GreenfootImage("EnemyBullet1.png");
        image.scale(10, 10);
        this.setImage(image);
        moveTimer.mark();
    }

    public void act()
    {
        super.act();
    }
    

    public void moveBullet() {
        Game game = (Game) getWorld();

        if(moveTimer.millisElapsed() > 20) { // Rate of movement
            if(game.player.isOnScreen == true)
            {
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
                    if(distance < lockDistance) {
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
                    if(Math.abs(diff) > reqAngle) {
                        if(diff > 0) {
                            turn(-turnAmount);
                        }
                        else {
                            turn(turnAmount);
                        }
                    }
                }
            }
            move(5);
            moveTimer.mark();
        }
    }
}