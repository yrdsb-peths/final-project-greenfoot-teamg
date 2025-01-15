import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class BossAlertAnimation extends Actor implements Freezable
{
    SimpleTimer existTimer = new SimpleTimer();
    
    public BossAlertAnimation()
    {
        GreenfootImage image = new GreenfootImage("BossIncoming.png");
        this.setImage(image);
        existTimer.mark();
    }
    
    public void act()
    {
        if(((Game)getWorld()).isFreeze == false)
        {
            if(existTimer.millisElapsed() > 3000)
            {
                getWorld().removeObject(this);
            }
        }
    }
    
    public void freeze()
    {
        existTimer.freeze();
    }
    
    public void unfreeze()
    {
        existTimer.unfreeze();
    }
}
