import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

// Enemy shoots normal bullet forwards, split bullet backwards, rotates

public class DoubleEndEnemy extends Enemy
{
    SimpleTimer timer = new SimpleTimer();
    int rotate = 0;


    public DoubleEndEnemy() {
        GreenfootImage image = new GreenfootImage("EnemySpaceShip6.png");
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
            Game game = (Game) getWorld();

            EnemyBullet1 bullet = new EnemyBullet1();
            EnemyBullet3 backBullet = new EnemyBullet3(getX(),getY());


            game.addObject(bullet, this.getX(), this.getY());
            game.addObject(backBullet, this.getX(), this.getY());


            bullet.setRotation(rotate + 90);
            bullet.move(20);

            backBullet.setRotation(270 + rotate);
            backBullet.move(20);

            rotate += 30;
            timer.mark();
        }
    }
}
