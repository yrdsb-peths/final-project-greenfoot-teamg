import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

public class Boss1 extends Boss
{
    /**
     * Constructor that initializes all the variabels
     */
    public Boss1()
    {
        super(150);
        GreenfootImage image = new GreenfootImage("BossSpaceShip0.png");
        image.scale(120,110);
        setImage(image);
    }
    
    /**
     * Calls the super class act is game is not frozen
     */
    public void act()
    {
        if(((Game)getWorld()).isFreeze == false)
        {
            super.act();
            if(!isAttacking)
            {
                for(Bullet bullet: getIntersectingObjects(Bullet.class)) { // Get all bullets touching 
                    World world = (World) getWorld();
                    world.removeObject(bullet);
                }
            }
        }
    }
    
    /**
     * Shoots a bunch of returning bullets, at the end shoots a giant laser
     */
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
        else if(attackTimer.millisElapsed() > 10000 && attackTimer.millisElapsed() <= 10500)
        {
            for(Bullet bullet: getIntersectingObjects(Bullet.class)) { // Get all bullets touching 
                World world = (World) getWorld();
                world.removeObject(bullet);
            }
        }
        else if(attackTimer.millisElapsed() > 10500 && attackTimer.millisElapsed() <= 15000)
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
    
    /**
     * Sends lasers in 4 directions, these lasers rotate.
     */
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
    
    /**
     * Sets up the second attack, creates 4 lasers that have a random turning direction.
     */
    public void attack2Setup()
    {
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
                LaserBeam laser = new LaserBeam(1, i*90, 361, 10);
                getWorld().addObject(laser, getX(), getY());
            }
        }
        isSetup = true;
    }
    
    /**
     * Boss starts moving all over the place while firing return bullets.
     */
    public void attack3(){
        if(!isSetup)
        {
            forceMove = true;
            isBypassBoundries = true;
            isSetup = true;
        }
        if(attackSlower.millisElapsed() > 1000 && attackTimer.millisElapsed() <= 10000)
        {
            changePosition();
            for(int i = 0; i < 8; i++)
            {
                Bullet bullet = new EnemyBullet5(this);
                getWorld().addObject(bullet, getX(), getY());
                bullet.turn(Util.randomInt(360));
            }
            attackSlower.mark();
        }
        if(attackTimer.millisElapsed() > 12000)
        {
            endAttack();
        }
    }
}
