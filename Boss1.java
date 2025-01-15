import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

public class Boss1 extends Boss 
{
    SimpleTimer curveCooldown = new SimpleTimer();
    SimpleTimer test1 = new SimpleTimer();
    
    public Boss1()
    {
        super(150);  // Initial health
        GreenfootImage image = new GreenfootImage("BossSpaceShip0.png");
        image.scale(100,105);
        setImage(image);
        curveCooldown.mark();
    }
    
    public void act()
    {
        if(((Game)getWorld()).isFreeze == false)
        {
            super.act();
            if(curveCooldown.millisElapsed() > 500)
            {
                randomCurveBall();
                curveCooldown.mark();
            }
        }
    }
    

    public void freeze()
    {
        super.freeze();
        curveCooldown.freeze();
    }
    
    public void unfreeze()
    {
        super.unfreeze();
        curveCooldown.unfreeze();
    }
    
    public void randomCurveBall()
    {
        if(Util.randomInt(9) == 0){
            shootCurveBall();
        }
    }
    
    public void shootCurveBall(){
        Bullet bullet = new EnemyBullet4();
        getWorld().addObject(bullet, getX(), getY());
    }
    
    public void attack1(){
        if(attackSlower.millisElapsed() > 50)
        {
            shootCurveBall();
            attackSlower.mark();
        }
        if(attackTimer.millisElapsed() > 5000)
        {
            endAttack();
        }
    }
    
    public void attack2(){
        if(attackSlower.millisElapsed() > 100)
        {
            for(int i = 0; i < 18; i++)
            {
                Bullet bullet = new EnemyBullet0();
                getWorld().addObject(bullet, getX(), getY());
                bullet.turn(i * (360/18) + Util.randomInt(360));
            }
            attackSlower.mark();
        }
        if(attackTimer.millisElapsed() > 8000)
        {
            endAttack();
        }
    }
    
    public void attack3(){
        // Shoot a bunch of normal bullets, at the end turn into curve bullets and circle around
        if(attackSlower.millisElapsed() > 100)
        {
            if(attackTimer.millisElapsed() <= 5000)
            {
                Bullet bullet = new EnemyBullet1();
                getWorld().addObject(bullet, getX(), getY());
                bullet.turn(Util.randomInt(180));
                attackSlower.mark();
            }
            else
            {
                for(EnemyBullet1 bullet : getWorld().getObjects(EnemyBullet1.class)) { // Get all enemies touching 
                    EnemyBullet4 bullet4 = new EnemyBullet4();
                    getWorld().addObject(bullet4, bullet.getX(), bullet.getY());
                    getWorld().removeObject(bullet);
                    bullet4.turn(Util.randomInt(360));
                    bullet4.turnSpeed = Util.randomInt(2) + 3;
                    break;
                }
            }
        }
        if(attackTimer.millisElapsed() > 10000)
        {
            endAttack();
        }
    }
}
