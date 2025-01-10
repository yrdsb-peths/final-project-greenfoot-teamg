import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

// 2 seeking bullets shoot from sides, 1 ricochet bullet from middle

public class BigEnemy extends Enemy
{
    SimpleTimer timer = new SimpleTimer();
    boolean ricochet = true;

    public BigEnemy() {
        GreenfootImage image = new GreenfootImage("EnemySpaceShip7.png");
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

            // Right seeking bullet
            Bullet seek1 = new EnemyBullet1();
            game.addObject(seek1, this.getX() + 25, this.getY());
            seek1.move(30);

            // Left seeking bullet
            Bullet seek2 = new EnemyBullet1();
            game.addObject(seek2, this.getX() - 25, this.getY());
            // Bullet faces and spawns in front of ship initially
            seek2.setRotation(180);
            seek2.move(30);


            // Middle ricochet bullet
            if(ricochet == true) {
                for(int i = 0; i < 2; i++)
                {
                    Bullet ricochetBullet = new EnemyBullet2();
                    game.addObject(ricochetBullet, getX(), getY());
                    // Bullet faces and spawns in front of ship initially
                    ricochetBullet.turn(Util.randomInt(90) + 90 * i);
                }
    
                ricochet = false;
            }
            else {
                ricochet = true;
            }
            
            
            timer.mark();
        }
    }
}
