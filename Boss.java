import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public abstract class Boss extends Enemy implements Freezable
{
    SimpleTimer moveCooldown = new SimpleTimer();
    SimpleTimer attackCooldown = new SimpleTimer();
    SimpleTimer attackTimer = new SimpleTimer();
    SimpleTimer attackSlower = new SimpleTimer();
    public boolean isMoving;
    public boolean isAttacking;
    public boolean isAtSpot = true;
    boolean isSetup = false;
    boolean forceMove = false;
    boolean isBypassBoundries = false;
    int x = 300;
    int y = 100;
    int attackNumber;
    
    public Boss(int health)
    {
        moveCooldown.mark();
        attackCooldown.mark();
        this.health = health;
        turn(90);
    }
    
    public void act()
    {

        if(moveCooldown.millisElapsed() > 10000 && !isAttacking)
        {
            changePosition();
        }
        moveToSpot();
        if(attackCooldown.millisElapsed() > 4000 && isAttacking == false)
        {
            isAttacking = true;
            attackTimer.mark();
            randomAttack();
        }
        if(isAttacking)
        {
            resumeAttack();
        }
    }
    
    /**
     * Handles the death of the boss.
     */
    public void die() {
        getWorld().addObject(new Explosion(), getX(), getY());
        explosionSound.play();
        ((Game)getWorld()).resetWaveTimer();
        getWorld().removeObject(this);  // Remove the enemy from the world
    }
    
    public void freeze()
    {
        moveCooldown.freeze();
        attackCooldown.freeze();
        attackTimer.freeze();
        attackSlower.freeze();
    }
    
    public void unfreeze()
    {
        moveCooldown.unfreeze();
        attackCooldown.unfreeze();
        attackTimer.unfreeze();
        attackSlower.unfreeze();
    }
    
    public void resumeAttack()
    {
        if(attackNumber == 1)
        {
            attack1();
        }
        else if(attackNumber == 2)
        {
            attack2();
        }
        else
        {
            attack3();
        }
    }
    
    public void randomAttack()
    {
        int chooseAttack = Util.randomInt(2);
        if(chooseAttack == 0)
        {
            attackNumber = 1;
            attack1();
        }
        else if(chooseAttack == 1)
        {
            attackNumber = 2;
            attack2();
        }
        else
        {
            attackNumber = 3;
            attack3();
        }
    }
    
    public void changePosition(){
        if(!isBypassBoundries)
        {
            x = Util.randomInt(440) + 80;
            y = Util.randomInt(240) + 80;
        }
        else
        {
            x = Util.randomInt(440) + 80;
            y = Util.randomInt(640) + 80;
        }
        moveCooldown.mark();
    }
    
    public void moveToSpot(){
        if(!isAttacking || forceMove){
            int xDist = x - getX();
            int yDist = y - getY();
            if(!isBypassBoundries)
            {
                this.setLocation(getX() + xDist/10, getY() + yDist/10);
            }
            else
            {
                this.setLocation(getX() + xDist/30, getY() + yDist/30);
            }
        }
    }
    
    public void endAttack()
    {
        isAttacking = false;
        isSetup = false;
        forceMove = false;
        isBypassBoundries = false;
        attackCooldown.mark();
        if(getY() > getWorld().getHeight()/2)
        {
            changePosition();
        }
    }
    
    public abstract void attack1();

    public abstract void attack2();
    
    public abstract void attack3();
}