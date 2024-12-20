import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class CircleEnemy extends Enemy
{
    SimpleTimer timer = new SimpleTimer();
    int rotate = 0;

    public CircleEnemy() {
        GreenfootImage image = new GreenfootImage("EnemySpaceShip1.png");
        image.scale(75, 75);
        image.rotate(90);
        this.setImage(image);
        timer.mark();
    }

    public void act()
    {
        makeBullet();
    }

    public void makeBullet() {
        if(timer.millisElapsed() > 100) {
            Bullet bullet = new EnemyBullet0();
            Game game = (Game) getWorld();
            game.addObject(bullet, this.getX(), this.getY());
            bullet.setRotation(rotate);
            bullet.move(30);

            rotate += 30;
            timer.mark();
        }
    }
}
