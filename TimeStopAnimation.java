import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class TimeStopAnimation extends Actor
{
    SimpleTimer timer = new SimpleTimer();
    Boss3 boss;
    
    public TimeStopAnimation()
    {
        GreenfootImage image = new GreenfootImage("TranslucentBox.png");
        image.scale(600,800);
        setImage(image);
        timer.mark();
    }
    
    public TimeStopAnimation(Boss3 boss)
    {
        GreenfootImage image = new GreenfootImage("TranslucentBox.png");
        image.scale(600,800);
        setImage(image);
        timer.mark();
        this.boss = boss;
    }
    
    public void act()
    {
        if(timer.millisElapsed() > 200)
        {
            getWorld().removeObject(this);
            if(boss != null)
            {
                boss.resumeTime();
            }
        }
    }
}
