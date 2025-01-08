import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class CircleEnemy extends Enemy
{
    SimpleTimer timer = new SimpleTimer();
    int rotate = 0;

    public CircleEnemy() {
        GreenfootImage image = new GreenfootImage("EnemySpaceship1.png");
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
            EnemyBullet0 bullet = new EnemyBullet0();
            Game game = (Game) getWorld();
            game.addObject(bullet, this.getX(), this.getY());

            // Rotates each bullet so they shoot out in a circlular pattern
            bullet.setRotation(rotate);
            bullet.move(30);

            rotate += 30;
            timer.mark();
        }
    }
}
