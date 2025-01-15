import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

// Enemy that shoots ricochet bullets

public class RichochetEnemy extends Enemy
{
    SimpleTimer timer = new SimpleTimer();

    public RichochetEnemy() {
        GreenfootImage image = new GreenfootImage("EnemySpaceShip4.png");
        image.scale(75, 75);
        image.rotate(90);
        this.setImage(image);
        timer.mark();
    }
    public void act()
    {
        super.act();  // Ensure the enemy moves down
        makeBullet();
    }

    public void makeBullet() {
        if(timer.millisElapsed() > 500) {
            Bullet bullet = new EnemyBullet2();
            Game game = (Game) getWorld();
            game.addObject(bullet, getX(), getY());
            bullet.turn(Util.randomInt(180));    
            timer.mark();   
        }
    }
}
