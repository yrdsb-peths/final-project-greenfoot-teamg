import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class EnemyBullet0 extends Actor
{
    SimpleTimer timer = new SimpleTimer();

    public EnemyBullet0() {
        GreenfootImage image = new GreenfootImage("EnemyBullet0.png");
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
        if(timer.millisElapsed() > 20) {
            move(5);
            timer.mark();
        }
    }

    public void checkBounds() {
        Game game = (Game) getWorld();
        if(getX() <= 0 || getY() <= 0 || getX() >= game.getWidth() - 1 || getY() >= game.getHeight() - 1) {
            game.removeObject(this);
        }
    }
}