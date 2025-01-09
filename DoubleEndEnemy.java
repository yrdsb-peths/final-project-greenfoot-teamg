import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

// Enemy shoots normal bullet forwards, split bullet backwards, rotates

public class DoubleEndEnemy extends Enemy
{
    SimpleTimer timer = new SimpleTimer();
    int rotate = 0;


    public DoubleEndEnemy() {
        GreenfootImage image = new GreenfootImage("EnemySpaceShip5.png");
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

            EnemyBullet0 bullet = new EnemyBullet0();
            EnemyBullet3 backBullet = new EnemyBullet3(getX(),getY());


            game.addObject(bullet, this.getX(), this.getY());
            game.addObject(backBullet, this.getX(), this.getY());


            bullet.setRotation(rotate + 90);
            backBullet.setRotation(270 + rotate);

            bullet.move(30);
            backBullet.move(30);

            rotate += 30;
            timer.mark();
        }
    }
}
