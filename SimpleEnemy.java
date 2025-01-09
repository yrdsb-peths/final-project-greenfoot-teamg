import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

// Enemy that shoots straight forwards

public class SimpleEnemy extends Enemy
{
    SimpleTimer timer = new SimpleTimer();

    public SimpleEnemy() {
        GreenfootImage image = new GreenfootImage("EnemySpaceship0.png");
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
        if(timer.millisElapsed() > 300) {
            EnemyBullet0 bullet = new EnemyBullet0();
            Game game = (Game) getWorld();
            game.addObject(bullet, this.getX(), this.getY());
            // Bullet speed
            bullet.setRotation(90);
            bullet.move(30);
            timer.mark();
        }
    }
}
