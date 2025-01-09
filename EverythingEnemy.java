import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

// Enemy that changes bullet type each time it fires

public class EverythingEnemy extends Enemy
{
    SimpleTimer timer = new SimpleTimer();
    int cycle = 0;

    public EverythingEnemy() {
        GreenfootImage image = new GreenfootImage("EnemySpaceShip6.png");
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
        if(timer.millisElapsed() > 1000) {
            Game game = (Game) getWorld();
            Bullet bullet = null;
            EnemyBullet3 split = new EnemyBullet3(getX(), getY());

            if(cycle == 0) {
                // Linear bullet
                bullet = new EnemyBullet0();
            }
            if(cycle == 1) {
                // Seeking bullet
                bullet = new EnemyBullet1();
            }
            if(cycle == 2) {
                // Ricochet bullet
                bullet = new EnemyBullet2();
            }

            System.out.println(cycle);
            cycle += 1;
            cycle %= 4;

            if(bullet == null) {
                game.addObject(split, getX(), getY());
                split.setRotation(90);
                split.move(30);
            }
            else {
                game.addObject(bullet, getX(), getY());
                bullet.setRotation(90);
                bullet.move(30);
            }

            timer.mark();
        }
    }
}
