import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

public class Boss2 extends Boss 
{
    SimpleTimer curveCooldown = new SimpleTimer();
    SimpleTimer test1 = new SimpleTimer();
    
    /**
     * Constructor for the boss, sets up all the variables
     */
    public Boss2()
    {
        super(175);  // Initial health
        GreenfootImage image = new GreenfootImage("BossSpaceShip1.png");
        image.scale(100,105);
        setImage(image);
        curveCooldown.mark();
    }
    
    /**
     * Calls the super class act if game is not frozen, and if cooldown is over, shoots a random curve bullet
     */
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
    
    /**
     * Calls the super class method freeze() & freezes curveCooldown
     */
    public void freeze()
    {
        super.freeze();
        curveCooldown.freeze();
    }
    
    /**
     * Calls the super class method unfreeze() & unfreezes curveCooldown
     */
    public void unfreeze()
    {
        super.unfreeze();
        curveCooldown.unfreeze();
    }
    
    /**
     * 1/10 chance to shoot a random curve bullet
     */
    public void randomCurveBall()
    {
        if(Util.randomInt(9) == 0){
            shootCurveBall();
        }
    }
    
    /**
     * Creates a curve bullet and adds it on top of the boss
     */
    public void shootCurveBall(){
        Bullet bullet = new EnemyBullet4();
        getWorld().addObject(bullet, getX(), getY());
    }
    
    /**
     * Shoots a bunch of curve bullets downwards
     */
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
    
    /**
     * Shoots a bunch of normal bullets in random directions
     */
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
    
    /**
     * Shoots a bunch of seeking bullets, after a set amount of time, these bullets will explode into curve bullets.
     */
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
