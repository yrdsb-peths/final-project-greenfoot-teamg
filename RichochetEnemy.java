import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class RichochetEnemy extends Enemy
{
    SimpleTimer timer = new SimpleTimer();

    public RichochetEnemy() {
        GreenfootImage image = new GreenfootImage("EnemySpaceShip3.png");
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
        if(timer.millisElapsed() > 500) {
            Bullet bullet = new EnemyBullet2();
            Game game = (Game) getWorld();
            game.addObject(bullet, getX(), getY());
            bullet.turn(Util.randomInt(180));    
            timer.mark();   
        }
    }
}
