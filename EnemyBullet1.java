import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

// Seeking bullet, follows target

public class EnemyBullet1 extends Bullet
{
    SimpleTimer timer = new SimpleTimer();
    boolean far = true;

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

    // Make location of character update here
    double x = 100.0;
    double y = 550.0;


    public void moveBullet() {
        if(timer.millisElapsed() > 20) {

            if(far == true) {

                double toX = x - getX();
                double toY = getY() - y;

                double angle = Math.atan2(toY, toX);

                double a = Math.toRadians(-1 * getRotation());
                if(a < 0) {
                    a += Math.PI * 2;
                }
                
                double distance = Math.sqrt(toX * toX + toY * toY);
                if(distance < 50) {
                    far = false;
                }

                double diff = angle - a;
                if(diff > Math.PI) {
                    diff -= 2 * Math.PI;
                }
                else if(diff < -Math.PI) {
                    diff += 2 * Math.PI;
                }

                if(Math.abs(diff) > Math.PI / 12) {
                    if(diff > 0) {
                        turn(-2);
                    }
                    else {
                        turn(2);
                    }
                }
            }

            move(5);
            timer.mark();
        }
    }
}