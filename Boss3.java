import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Boss3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Boss3 extends Boss
{
    SimpleTimer BounceCooldown = new SimpleTimer();
    
    public Boss3()
    {
        super(600);
        GreenfootImage image = new GreenfootImage("BossSpaceShip2.png");
        image.scale(120,120);
        setImage(image);
    }

    public void act()
    {
        super.act();
        if(BounceCooldown.millisElapsed() > 1000)
        {
            randomBounceAttack();
            BounceCooldown.mark();
        }
    }
    
    public void randomBounceAttack()
    {
        if(Util.randomInt(9) == 0){
            for(int i = 0; i < 3; i++)
            {
                Bullet bullet = new EnemyBullet2();
                getWorld().addObject(bullet, getX(), getY());
                bullet.turn(Util.randomInt(90));
            }
            for(int i = 0; i < 3; i++)
            {
                Bullet bullet = new EnemyBullet2();
                getWorld().addObject(bullet, getX(), getY());
                bullet.turn(Util.randomInt(90) + 90);
            }
        }
    }
    
    public void attack1(){
        if(attackSlower.millisElapsed() > 100 && attackTimer.millisElapsed() < 8000)
        {
            int random = Util.randomInt(1);
            if(random == 0)
            {
                Bullet bullet = new EnemyBullet3();
                getWorld().addObject(bullet, getX(), getY());
                bullet.turn(Util.randomInt(180));
            }
            else
            {
               Bullet bullet = new EnemyBullet2();
               getWorld().addObject(bullet, getX(), getY());
               bullet.turn(Util.randomInt(180));
            }
            attackSlower.mark();
        }
        if(attackTimer.millisElapsed() > 12000)
        {
            endAttack();
        }
    }
    
    public void attack2(){
        endAttack();
    }
    
    public void attack3(){
        endAttack();
    }
}
