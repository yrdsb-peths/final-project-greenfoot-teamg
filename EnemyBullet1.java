import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class EnemyBullet1 extends Bullet
{
    SimpleTimer timer = new SimpleTimer();

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

    double x = 35.0;
    double y = 100.0;


    public void moveBullet() {
        if(timer.millisElapsed() > 20) {

            double toX = x - getX();
            double toY = y - getY();

            int angle = (int) Math.toDegrees(Math.atan(toY / toX));
            if(toX < getX() && toY < getY()) {
                angle += 90;
            }
            if(toX < getX() && toY > getY()) {
                angle += 180;
                System.out.println("what");
            }
            if(toX > getX() && toY > getY()) {
                angle += 270;
            }



            int a = (int) -1 * getRotation();
            if(a < 0) {
                a += 360;
            }

            System.out.println("Target: " + angle + ", " + a);

            
            if(a >= angle + 5 || a <= angle - 5) {
                if(getX() > x) {
                    turn(-2);
                }
                else if(getX() < x){
                    turn(2);
                }
            }


            move(5);
            timer.mark();
        }
    }
}