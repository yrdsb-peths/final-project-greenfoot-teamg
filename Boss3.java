import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Boss3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Boss3 extends Boss
{
    SimpleTimer bounceCooldown = new SimpleTimer();
    boolean isTimeStop = false;
    
    public Boss3()
    {
        super(250);
        GreenfootImage image = new GreenfootImage("BossSpaceShip2.png");
        image.scale(120,120);
        setImage(image);
    }

    public void act()
    {
        if(((Game)getWorld()).isFreeze == false)
        {
            super.act();
            if(bounceCooldown.millisElapsed() > 800)
            {
                randomBounceAttack();
                bounceCooldown.mark();
            }
        }
        else if(isTimeStop == true)
        {
            resumeAttack();
        }
    }
    
    public void freeze()
    {
        super.act();
        bounceCooldown.freeze();
    }
    
    public void unfreeze()
    {
        moveCooldown.unfreeze();
        attackCooldown.unfreeze();
        bounceCooldown.unfreeze();
        if(isTimeStop == false)
        {
            attackTimer.unfreeze();
            attackSlower.unfreeze();
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
                Bullet bullet = new EnemyBullet3(getX(), getY());
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
    
    public void stopTime()
    {
        if(((Game)getWorld()).isFreeze == false)
        {
            ((Game)getWorld()).freezeGame();
            attackCooldown.unfreeze();
            attackTimer.unfreeze();
            TimeStopAnimation timeStop = new TimeStopAnimation(this, false);
            getWorld().addObject(timeStop, getWorld().getWidth()/2, getWorld().getHeight()/2);
            isTimeStop = true;
        }
    }
    
    public void resumeTime()
    {
        if(((Game)getWorld()).isFreeze == true)
        {
            ((Game)getWorld()).resumeGame();
            isTimeStop = false;
        }
    }
    
    public void attack2(){
        if(!isSetup)
        {
            attack2and3Setup();
        }
        if(attackTimer.millisElapsed() > 1000 && attackTimer.millisElapsed() < 5000)
        {
            if(attackSlower.millisElapsed() > 800)
            {
               for(int i = 0; i < 3; i++)
               {
                    Bullet bullet = new EnemyBullet3(getX(),getY());
                    int randomAngle = Util.randomInt(360);
                    int y = ((Game)getWorld()).player.getY() + (int) Math.round(Math.sin(Math.toRadians(randomAngle)) * 200);
                    int x = ((Game)getWorld()).player.getX() + (int) Math.round(Math.cos(Math.toRadians(randomAngle)) * 200);
                    if(x >= 0 && x <= getWorld().getWidth() && y >= 0 && y <= getWorld().getHeight())
                    {
                        getWorld().addObject(bullet, x, y);
                        bullet.turnTowards(((Game)getWorld()).player.getX(), ((Game)getWorld()).player.getY());
                        bullet.freeze();
                    }
               } 
               attackSlower.mark();
            }
        }
        else if(attackTimer.millisElapsed() > 5000 && attackTimer.millisElapsed() < 5200)
        {
            if(attackSlower.millisElapsed() > 200)
            {
                TimeStopAnimation timeResume = new TimeStopAnimation(this, true);
                getWorld().addObject(timeResume, getWorld().getWidth()/2, getWorld().getHeight()/2);
                attackSlower.mark();
            }
        }
        if(attackTimer.millisElapsed() > 8000)
        {
            endAttack();
        }
    }
    
    public void attack2and3Setup()
    {
        stopTime();
        isSetup = true;
        attackSlower.mark();
    }
    
    public void attack3(){
        if(!isSetup)
        {
            attack2and3Setup();
        }
        if(attackTimer.millisElapsed() > 1000 && attackTimer.millisElapsed() < 5800)
        {
            if(attackSlower.millisElapsed() > 800)
            {
               for(int i = 0; i < 5; i++)
               {   
                    int randInt = Util.randomInt(3);
                    Bullet bullet;
                    if(randInt == 0)
                    {
                        bullet = new EnemyBullet0();
                    }
                    else if(randInt == 1)
                    {
                        bullet = new EnemyBullet1();
                    }
                    else if(randInt == 2)
                    {
                        bullet = new EnemyBullet2();
                    }
                    else
                    {
                        bullet = new EnemyBullet3(getX(),getY());
                    }
                    int y = Util.randomInt(getWorld().getHeight());
                    int x = Util.randomInt(getWorld().getWidth());
                    int distX = Math.abs(((Game)getWorld()).player.getX() - x);
                    int distY = Math.abs(((Game)getWorld()).player.getY() - y);
                    if(Math.sqrt(Math.pow(distX, 2) + Math.pow(distY, 2)) > 100)
                    {
                        getWorld().addObject(bullet, x, y);
                        bullet.turnTowards(((Game)getWorld()).player.getX(), ((Game)getWorld()).player.getY());
                        bullet.freeze();
                    }
                    else
                    {
                        i--;
                    }
               } 
               attackSlower.mark();
            }
        }
        else if(attackTimer.millisElapsed() > 5800 && attackTimer.millisElapsed() < 6000)
        {
            if(attackSlower.millisElapsed() > 200)
            {
                TimeStopAnimation timeResume = new TimeStopAnimation(this, true);
                getWorld().addObject(timeResume, getWorld().getWidth()/2, getWorld().getHeight()/2);
                attackSlower.mark();
            }
        }
        if(attackTimer.millisElapsed() > 9000)
        {
            endAttack();
        }
    }
}
