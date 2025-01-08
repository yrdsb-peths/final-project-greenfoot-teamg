import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class Boss2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Boss2 extends Boss
{
    public Boss2()
    {
        super(400);
        GreenfootImage image = new GreenfootImage("BossSpaceShip1.png");
        image.scale(120,110);
        setImage(image);
    }
    
    public void act()
    {
        super.act();
        if(!isAttacking)
        {
            for(Bullet bullet: getWorld().getObjects(Bullet.class)) { // Get all bullets touching 
                World world = (World) getWorld();
                world.removeObject(bullet);
            }
        }
    }
    
    public void attack1(){
        if(attackTimer.millisElapsed() <= 7000)
        {
            if(attackSlower.millisElapsed() > 10)
            {
                Bullet bullet = new EnemyBullet5(this);
                getWorld().addObject(bullet, getX(), getY());
                bullet.turn(Util.randomInt(360));
                attackSlower.mark();
            }
        }
        else if(attackTimer.millisElapsed() > 7000 && attackTimer.millisElapsed() <= 10000)
        {
            for(EnemyBullet5 bullet: getWorld().getObjects(EnemyBullet5.class)) { // Get all bullets in range 
                World world = (World) getWorld();
                GreenfootImage image = new GreenfootImage("EnemyBullet5.png");
                image.scale(10, 10);
                bullet.setImage(image);
                bullet.isReturning = true;
            }
        }
        else if(attackTimer.millisElapsed() > 10000 && attackTimer.millisElapsed() < 10500)
        {
            for(Bullet bullet: getWorld().getObjects(Bullet.class)) { // Get all bullets touching 
                World world = (World) getWorld();
                world.removeObject(bullet);
            }
        }
        else if(attackTimer.millisElapsed() >10500 && attackTimer.millisElapsed() < 15000)
        {
            if(!isSetup)
            {
                int random = Util.randomInt(1);
                if(random == 0)
                {
                    LaserBeam laser = new LaserBeam(-2,160, 20, 50);
                    getWorld().addObject(laser, getX(), getY());
                }
                else
                {
                    LaserBeam laser = new LaserBeam(2, 20, 160, 50);
                    getWorld().addObject(laser, getX(), getY());
                }
                isSetup = true;
            }
        }
        else if(attackTimer.millisElapsed() > 15000)
        {
            endAttack();
        }
    }
    
    public void attack2(){
        if(!isSetup)
        {
            attack2Setup();
        }
        if(attackSlower.millisElapsed() > 100 && attackTimer.millisElapsed() > 1000)
        {
            int random = Util.randomInt(19);
            if(random == 0)
            {
                random = Util.randomInt(1);
                for(LaserBeam laser: getWorld().getObjects(LaserBeam.class)) { // Get all lasers touching 
                     if(random == 0)
                    {
                        laser.turnSpeed = 1;
                        laser.endAngle = 361;
                    }
                    else
                    {
                        laser.turnSpeed = -1;
                        laser.endAngle = -1;
                    }
                }
            }
            attackSlower.mark();
        }
        if(attackTimer.millisElapsed() > 10000)
        {
            for(LaserBeam laser: getWorld().getObjects(LaserBeam.class)) { // Get all lasers touching 
                World world = (World) getWorld();
                world.removeObject(laser);
            }
            endAttack();
        }
    }
    
    public void attack2Setup()
    {
        x = 300;
        y = 150;
        forceMove = true;
        if(getX() < x + 10 && getX() > x - 10 && getY() < y + 10 && getY() > y - 10)
        {
            forceMove = false;
            int random = Util.randomInt(1);
            for(int i = 0; i < 4; i++)
            {
                if(random == 0)
                {
                    LaserBeam laser = new LaserBeam(-1, i*90, -1, 10);
                    getWorld().addObject(laser, getX(), getY());
                }
                else
                {
                    LaserBeam laser = new LaserBeam(-1, i*90, -1, 10);
                    getWorld().addObject(laser, getX(), getY());
                }
            }
            isSetup = true;
        }
    }
    
    public void attack3(){
        if(!isSetup)
        {
            forceMove = true;
            isBypassBoundries = true;
            isSetup = true;
        }
        if(attackSlower.millisElapsed() > 500)
        {
            changePosition();
            for(int i = 0; i < 4; i++)
            {
                Bullet bullet = new EnemyBullet5(this);
                getWorld().addObject(bullet, getX(), getY());
                bullet.turn(Util.randomInt(360));
            }
            attackSlower.mark();
        }
        if(attackTimer.millisElapsed() > 10000)
        {
            forceMove = false;
            isBypassBoundries = false;
            endAttack();
        }
    }
}
