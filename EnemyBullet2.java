import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

// Richochet bullet, bounces off the world boundaries once

public class EnemyBullet2 extends Bullet
{
    SimpleTimer timer = new SimpleTimer();
    int maxBounces = 2; // Times bullet can bounce
    int bounce = 0;

    int maxX, maxY;
    int minX = 0;
    int minY = 0;

    public EnemyBullet2() {
        GreenfootImage image = new GreenfootImage("EnemyBullet2.png");
        image.scale(10, 10);
        this.setImage(image);
        timer.mark();
    }

    public void act()
    {
        moveBullet();
        if(bounce >= maxBounces) { // Delete if touching edge and cannot bounce
            checkBounds();
        }
    }

    public void moveBullet() {
        if(timer.millisElapsed() > 20) {
            checkBounce();
            move(10);
            timer.mark();
        }
    }

    public void checkBounce() {
        if(bounce < maxBounces) {
            Game game = (Game) getWorld(); // Game edges
            maxX = game.getWidth();
            maxY = game.getHeight();

            int rotation = getRotation();
    
            if(getX() <= minX + 1 || getX() >= maxX - 1) { // set angle if hits either sides
                int ref = 180 - rotation;
                setRotation(ref);

                bounce += 1;
            }
            else if(getY() <= minY + 1|| getY() >= maxY - 1) { // set angle if hits top or bottom
                int ref = 360 - rotation;
                setRotation(ref);

                bounce += 1;
            }
        }
    }
}
